--RFC4
SELECT
    *
FROM
    a_producto
WHERE
    preciounitario BETWEEN 1000 AND 10000;
    
SELECT
    *
FROM
    a_producto
WHERE
    fechavencimiento IS NOT NULL
    AND fechavencimiento >= TO_DATE('2017-10-15','yyyy-MM-dd');
    
    
SELECT
    *
FROM
    a_producto
WHERE
    pesoproducto BETWEEN 10 AND 100;
    
    
    
SELECT
    *
FROM
    a_producto
WHERE
    volumenproducto BETWEEN 200 AND 300;
    
    
SELECT
    *
FROM
    a_producto,
    a_proveen
WHERE
    nitproveedor = 1
    AND aproducto.codbarras = aproveen.codbarras;
    
SELECT
    *
FROM
    a_cantidad_en_bodega,
    a_cantidad_en_estantes,
    a_producto,
    a_estante
WHERE
    ( a_cantidad_en_bodega.ciudad = 'Bucaramanga'
      OR a_estante.ciudad = 'Bucaramanga' )
    AND ( a_estante.id = a_cantidad_en_estantes.idestante )
    AND ( a_cantidad_en_bodega.cantidadactual + a_cantidad_en_estantes.cantidadactual > 0 );




SELECT
    *
FROM
    a_cantidad_en_bodega,
    a_cantidad_en_estantes,
    a_estante
WHERE
    ( a_estante.ciudad = 'Bucaramanga'
      AND a_estante.direccionsucursal = 'CR 139 139-99' )
    AND ( a_cantidad_en_bodega.ciudad = 'Bucaramanga'
          AND a_cantidad_en_bodega.direccionsucursal = 'CR 139 139-99' )
    AND ( a_estante.id = a_cantidad_en_estantes.idestante )
    AND ( a_cantidad_en_bodega.cantidadactual + a_cantidad_en_estantes.cantidadactual > 0 );




SELECT
    *
FROM
    a_producto,
    a_tipo_producto,
    a_categoria
WHERE
    nombretipo = 'Carnes'
    AND a_tipo_producto.nombrecategoria = a_producto_categoria.categoria
    AND a_producto_categoria.codigobarras = a_producto.codbarras;



SELECT
    *
FROM
    a_producto,
    a_producto_categoria
WHERE
    a_producto_categoria.categoria = 'Congelados'
    AND a_producto_categoria.codigobarras = a_producto.codbarras;

SELECT
    *
FROM
    (
        SELECT
            COUNT(cantidad) numerounidades,
            codigobarras
        FROM
            (
                SELECT
                    *
                FROM
                    a_comprados,
                    a_factura
                WHERE
                    a_factura.idfactura = a_comprados.idfactura
                    AND a_factura.fecha BETWEEN TO_DATE('2017-01-01','yyyy-MM-dd') AND TO_DATE('2017-12-31','yyyy-MM-dd')
            ) "+"
        GROUP BY
            codigobarras
        HAVING
            COUNT(cantidad) > 20
    ) comprados,
    a_producto
WHERE
    a_producto.codbarras = comprados.codigobarras