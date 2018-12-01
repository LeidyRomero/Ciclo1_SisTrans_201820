----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------RFC12:CONSULTAR FUNCIONAMIENTO--------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--SEMANALMENTE: PRODUCTO MAS-MENOS VENDIDO
--              PROVEEDOR MAS-MENOS SOLICITADOS

--RFC12 - CONSULTAR FUNCIONAMIENTO
--PRODUCTO MAS-MENOS VENDIDO:
SELECT
    cb,
    semana,
    suma
FROM
    (
        SELECT
            codigobarras cb,
            TO_CHAR(fecha,'WW') semana,
            SUM(cantidad) suma,
            ROW_NUMBER() OVER(PARTITION BY
                TO_CHAR(fecha,'WW')
                ORDER BY
                    (SUM(cantidad) )
                DESC
            ) AS maximo,
            ROW_NUMBER() OVER(PARTITION BY
                TO_CHAR(fecha,'WW')
                ORDER BY
                    (SUM(cantidad) )
                ASC
            ) AS minimo
        FROM
            a_comprados,
            a_factura
        WHERE
                a_comprados.idfactura = a_factura.idfactura
            AND
                EXTRACT(YEAR FROM fecha) = 2018
        GROUP BY
            TO_CHAR(fecha,'WW'),
            codigobarras
    )
WHERE
        maximo = 1
    OR
        minimo = 1;
--PROVEEDOR MAS-MENOS VENDIDO:
SELECT
    nitproveedor nit,
    COUNT(*) numeroPedidos,
    TO_CHAR(fechaentrega,'WW') semana,
    ROW_NUMBER() OVER (PARTITION BY TO_CHAR(fechaEntrega,'WW') ORDER BY (COUNT(*)) desc) as maximo,
    ROW_NUMBER() OVER (PARTITION BY TO_CHAR(fechaEntrega,'WW') ORDER BY (COUNT(*)) asc) as minimo
FROM
    a_orden_pedido
GROUP BY
    nitproveedor,
    TO_CHAR(fechaentrega,'WW');
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------RFC13:Consultar los buenos clientes---------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--Compran al menos 1 vez al mes : #facturas mensuales>=1 para todos los meses de funcionamiento COSTO: 4 <PROBAR!>
SELECT
  A.CORREO,
  A.numeroFacturas
FROM
  (
    SELECT
      COUNT(DISTINCT EXTRACT(MONTH FROM FECHA)) numeroFacturas,
      CORREO
    FROM
      A_FACTURA,
      A_CLIENTE
    WHERE
      A_CLIENTE.CORREO = A_FACTURA.CORREOCLIENTE
    GROUP BY
      CORREO
  )
  A
WHERE
  A.numeroFacturas = (
  (
    SELECT
      MAX(EXTRACT(MONTH FROM FECHA))
    FROM
      A_FACTURA
  )
  +1-
  (
    SELECT
      MIN(EXTRACT(MONTH FROM FECHA))
    FROM
      A_FACTURA
  )
  );
--CLIENTES QUE SIEMPRE COMPRAN PRODUCTOS COSTOSOS:funciona: COSTO:5
SELECT
  A.numeroFacturas,
  A.CORREO,
  A.NOMBRECONSUMIDOR
FROM
  (
    SELECT
      COUNT(*) numeroFacturas,
      CORREO,
      NOMBRECONSUMIDOR
    FROM
      A_FACTURA,
      A_CLIENTE
    WHERE
      A_FACTURA.CORREOCLIENTE = A_CLIENTE.CORREO
    GROUP BY
      A_CLIENTE.CORREO, A_CLIENTE.NOMBRECONSUMIDOR
  )
  A ,
  (
    SELECT
      COUNT(*),
      CORREOCLIENTE
    FROM
      A_COMPRADOS,
      A_FACTURA
    WHERE
      A_COMPRADOS.IDFACTURA = A_FACTURA.IDFACTURA
    AND CODIGOBARRAS       IN
      (
        SELECT
          CODBARRAS
        FROM
          A_PRODUCTO
        WHERE
          A_PRODUCTO.PRECIOUNITARIO >=100000
      )
    GROUP BY
      A_FACTURA.CORREOCLIENTE
  )
  B
WHERE
  A.CORREO = B.CORREOCLIENTE;
--CLIENTES QUE SIEMPRE COMPRAN TECNOLOGIA O HERRAMIENTAS: funciona: COSTO :5
SELECT
  A.numeroFacturas,
  A.CORREO,
  A.NOMBRECONSUMIDOR
FROM
  (
    SELECT
      COUNT(*) numeroFacturas,
      CORREO,
      NOMBRECONSUMIDOR
    FROM
      A_FACTURA,
      A_CLIENTE
    WHERE
      A_FACTURA.CORREOCLIENTE = A_CLIENTE.CORREO
    GROUP BY
      A_CLIENTE.CORREO, A_CLIENTE.NOMBRECONSUMIDOR
  )
  A ,
  (
    SELECT
      COUNT(*),
      CORREOCLIENTE
    FROM
      A_COMPRADOS,
      A_FACTURA
    WHERE
      A_COMPRADOS.IDFACTURA = A_FACTURA.IDFACTURA
    AND CODIGOBARRAS       IN
      (
        SELECT
          CODBARRAS
        FROM
          A_PRODUCTO
        WHERE
          A_PRODUCTO.NOMBRECATEGORIA  ='Technology'
        OR A_PRODUCTO.NOMBRECATEGORIA = 'Tools'
      )
    GROUP BY
      A_FACTURA.CORREOCLIENTE
  )
  B
WHERE
  A.CORREO = B.CORREOCLIENTE;

