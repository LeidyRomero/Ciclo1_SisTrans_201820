SELECT to_number(to_char(to_date(fecha,'yyyy-MM-dd'),'WW'))
FROM A_FACTURA;

-- Meses (M�xima Demanda)
SELECT b.max, b.ciudad, b.direccionsucursal, d.fecha
FROM (
SELECT
    MAX(a.numvendidos) as max,
    a.ciudad,
    a.direccionsucursal
FROM
    (
        SELECT
            f.ciudad,
            f.direccionsucursal,
            SUBSTR(to_char(fecha),4,5) AS fecha,
            SUM(c.cantidad) AS numvendidos
        FROM
            a_comprados c,
            a_factura f,
            a_producto p
        WHERE
            c.idfactura = f.idfactura AND
            p.codbarras = c.codigobarras AND
            p.nombrecategoria = 'Dulces'
        GROUP BY
            f.ciudad,
            f.direccionsucursal,
            SUBSTR(to_char(fecha),4,5)
    ) a
GROUP BY
    a.ciudad,
    a.direccionsucursal) b,(
        SELECT
            f.ciudad,
            f.direccionsucursal,
            SUBSTR(to_char(fecha),4,5) AS fecha,
            SUM(c.cantidad) AS numvendidos
        FROM
            a_comprados c,
            a_factura f,
            a_producto p
        WHERE
            c.idfactura = f.idfactura AND            
            p.codbarras = c.codigobarras AND
            p.nombrecategoria = 'Dulces'
        GROUP BY
            f.ciudad,
            f.direccionsucursal,
            SUBSTR(to_char(fecha),4,5)
    )d
WHERE b.ciudad = d.ciudad AND b.direccionsucursal = d.direccionsucursal AND b.max = d.numvendidos;


-- A�o (M�xima demanda)
SELECT b.max, b.ciudad, b.direccionsucursal, d.a�o
FROM (
SELECT
    MAX(a.numvendidos) as max,
    a.ciudad,
    a.direccionsucursal
FROM
    (
        SELECT
            f.ciudad,
            f.direccionsucursal,
            EXTRACT(YEAR FROM f.fecha) AS a�o,
            SUM(c.cantidad) AS numvendidos
        FROM
            a_comprados c,
            a_factura f,
            a_producto p
        WHERE
            c.idfactura = f.idfactura AND
            p.codbarras = c.codigobarras AND
            p.nombrecategoria = 'Dulces'
        GROUP BY
            f.ciudad,
            f.direccionsucursal,
            EXTRACT(YEAR FROM f.fecha)
    ) a
GROUP BY
    a.ciudad,
    a.direccionsucursal) b,(
        SELECT
            f.ciudad,
            f.direccionsucursal,
            EXTRACT(YEAR FROM f.fecha) AS a�o,
            SUM(c.cantidad) AS numvendidos
        FROM
            a_comprados c,
            a_factura f,
            a_producto p
        WHERE
            c.idfactura = f.idfactura AND
            p.codbarras = c.codigobarras AND
            p.nombrecategoria = 'Dulces'
        GROUP BY
            f.ciudad,
            f.direccionsucursal,
            EXTRACT(YEAR FROM f.fecha)
    )d
WHERE b.ciudad = d.ciudad AND b.direccionsucursal = d.direccionsucursal AND b.max = d.numvendidos;

-- Meses (M�nima demanda)
SELECT b.min, b.ciudad, b.direccionsucursal, d.mes
FROM (
SELECT
    MIN(a.numvendidos) as min,
    a.ciudad,
    a.direccionsucursal
FROM
    (
        SELECT
            f.ciudad,
            f.direccionsucursal,
            SUBSTR(to_char(fecha),4,5) AS mes,
            SUM(c.cantidad) AS numvendidos
        FROM
            a_comprados c,
            a_factura f,
            a_producto p
        WHERE
            c.idfactura = f.idfactura AND
            p.codbarras = c.codigobarras AND
            p.nombrecategoria = 'Dulces'
        GROUP BY
            f.ciudad,
            f.direccionsucursal,
            SUBSTR(to_char(fecha),4,5)
    ) a
GROUP BY
    a.ciudad,
    a.direccionsucursal) b,(
        SELECT
            f.ciudad,
            f.direccionsucursal,
            SUBSTR(to_char(fecha),4,5) AS mes,
            SUM(c.cantidad) AS numvendidos
        FROM
            a_comprados c,
            a_factura f,
            a_producto p
        WHERE
            c.idfactura = f.idfactura AND
            p.codbarras = c.codigobarras AND
            p.nombrecategoria = 'Dulces'
        GROUP BY
            f.ciudad,
            f.direccionsucursal,
            SUBSTR(to_char(fecha),4,5)
    )d
WHERE b.ciudad = d.ciudad AND b.direccionsucursal = d.direccionsucursal AND b.min = d.numvendidos;

-- A�o (M�nima demanda)
SELECT b.min, b.ciudad, b.direccionsucursal, d.a�o
FROM (
SELECT
    MIN(a.numvendidos) as min,
    a.ciudad,
    a.direccionsucursal
FROM
    (
        SELECT
            f.ciudad,
            f.direccionsucursal,
            EXTRACT(YEAR FROM f.fecha) AS a�o,
            SUM(c.cantidad) AS numvendidos
        FROM
            a_comprados c,
            a_factura f,
            a_producto p
        WHERE
            c.idfactura = f.idfactura AND
            p.codbarras = c.codigobarras AND
            p.nombrecategoria = 'Dulces'
        GROUP BY
            f.ciudad,
            f.direccionsucursal,
            EXTRACT(YEAR FROM f.fecha)
    ) a
GROUP BY
    a.ciudad,
    a.direccionsucursal) b,(
        SELECT
            f.ciudad,
            f.direccionsucursal,
            EXTRACT(YEAR FROM f.fecha) AS a�o,
            SUM(c.cantidad) AS numvendidos
        FROM
            a_comprados c,
            a_factura f,
            a_producto p
        WHERE
            c.idfactura = f.idfactura AND
            p.codbarras = c.codigobarras AND
            p.nombrecategoria = 'Dulces'
        GROUP BY
            f.ciudad,
            f.direccionsucursal,
            EXTRACT(YEAR FROM f.fecha)
    )d
WHERE b.ciudad = d.ciudad AND b.direccionsucursal = d.direccionsucursal AND b.min = d.numvendidos;

-- A�o (M�ximo dinero)
SELECT b.max, b.ciudad, b.direccionsucursal, d.a�o
FROM (
SELECT
    MAX(a.costo) as max,
    a.ciudad,
    a.direccionsucursal
FROM
    (
        SELECT
            f.ciudad,
            f.direccionsucursal,
            EXTRACT(YEAR FROM f.fecha) AS a�o,
            SUM(f.costototal) AS costo
        FROM
            a_comprados c,
            a_factura f,
            a_producto p
        WHERE
            c.idfactura = f.idfactura AND
            p.codbarras = c.codigobarras AND
            p.nombrecategoria = 'Dulces'
        GROUP BY
            f.ciudad,
            f.direccionsucursal,
            EXTRACT(YEAR FROM f.fecha)
    ) a
GROUP BY
    a.ciudad,
    a.direccionsucursal) b,(
        SELECT
            f.ciudad,
            f.direccionsucursal,
            EXTRACT(YEAR FROM f.fecha) AS a�o,
            SUM(f.costototal) AS costo
        FROM
            a_comprados c,
            a_factura f,
            a_producto p
        WHERE
            c.idfactura = f.idfactura AND
            p.codbarras = c.codigobarras AND
            p.nombrecategoria = 'Dulces'
        GROUP BY
            f.ciudad,
            f.direccionsucursal,
            EXTRACT(YEAR FROM f.fecha)
    )d
WHERE b.ciudad = d.ciudad AND b.direccionsucursal = d.direccionsucursal AND b.max = d.costo;


-- Meses (M�ximo dinero)
SELECT b.max, b.ciudad, b.direccionsucursal, d.mes
FROM (
SELECT
    MAX(a.costo) as max,
    a.ciudad,
    a.direccionsucursal
FROM
    (
        SELECT
            f.ciudad,
            f.direccionsucursal,
            SUBSTR(to_char(fecha),4,5) AS mes,
            SUM(f.costototal) AS costo
        FROM
            a_comprados c,
            a_factura f,
            a_producto p
        WHERE
            c.idfactura = f.idfactura AND
            p.codbarras = c.codigobarras AND
            p.nombrecategoria = 'Dulces'
        GROUP BY
            f.ciudad,
            f.direccionsucursal,
            SUBSTR(to_char(fecha),4,5)
    ) a
GROUP BY
    a.ciudad,
    a.direccionsucursal) b,(
        SELECT
            f.ciudad,
            f.direccionsucursal,
            SUBSTR(to_char(fecha),4,5) AS mes,
            SUM(f.costototal) AS costo
        FROM
            a_comprados c,
            a_factura f,
            a_producto p
        WHERE
            c.idfactura = f.idfactura AND
            p.codbarras = c.codigobarras AND
            p.nombrecategoria = 'Dulces'
        GROUP BY
            f.ciudad,
            f.direccionsucursal,
            SUBSTR(to_char(fecha),4,5)
    )d
WHERE b.ciudad = d.ciudad AND b.direccionsucursal = d.direccionsucursal AND b.max = d.costo;

-- A�o (M�nimo dinero)
SELECT b.min, b.ciudad, b.direccionsucursal, d.a�o
FROM (
SELECT
    MIN(a.costo) as min,
    a.ciudad,
    a.direccionsucursal
FROM
    (
        SELECT
            f.ciudad,
            f.direccionsucursal,
            EXTRACT(YEAR FROM f.fecha) AS a�o,
            SUM(f.costototal) AS costo
        FROM
            a_comprados c,
            a_factura f,
            a_producto p
        WHERE
            c.idfactura = f.idfactura AND
            p.codbarras = c.codigobarras AND
            p.nombrecategoria = 'Dulces'
        GROUP BY
            f.ciudad,
            f.direccionsucursal,
            EXTRACT(YEAR FROM f.fecha)
    ) a
GROUP BY
    a.ciudad,
    a.direccionsucursal) b,(
        SELECT
            f.ciudad,
            f.direccionsucursal,
            EXTRACT(YEAR FROM f.fecha) AS a�o,
            SUM(f.costototal) AS costo
        FROM
            a_comprados c,
            a_factura f,
            a_producto p
        WHERE
            c.idfactura = f.idfactura AND
            p.codbarras = c.codigobarras AND
            p.nombrecategoria = 'Dulces'
        GROUP BY
            f.ciudad,
            f.direccionsucursal,
            EXTRACT(YEAR FROM f.fecha)
    )d
WHERE b.ciudad = d.ciudad AND b.direccionsucursal = d.direccionsucursal AND b.min = d.costo;

-- Meses (M�nimo dinero)
SELECT b.min, b.ciudad, b.direccionsucursal, d.mes
FROM (
SELECT
    MIN(a.costo) as min,
    a.ciudad,
    a.direccionsucursal
FROM
    (
        SELECT
            f.ciudad,
            f.direccionsucursal,
            SUBSTR(to_char(fecha),4,5) AS mes,
            SUM(f.costototal) AS costo
        FROM
            a_comprados c,
            a_factura f,
            a_producto p
        WHERE
            c.idfactura = f.idfactura AND
            p.codbarras = c.codigobarras AND
            p.nombrecategoria = 'Dulces'
        GROUP BY
            f.ciudad,
            f.direccionsucursal,
            SUBSTR(to_char(fecha),4,5)
    ) a
GROUP BY
    a.ciudad,
    a.direccionsucursal) b,(
        SELECT
            f.ciudad,
            f.direccionsucursal,
            SUBSTR(to_char(fecha),4,5) AS mes,
            SUM(f.costototal) AS costo
        FROM
            a_comprados c,
            a_factura f,
            a_producto p
        WHERE
            c.idfactura = f.idfactura AND
            p.codbarras = c.codigobarras AND
            p.nombrecategoria = 'Dulces'
        GROUP BY
            f.ciudad,
            f.direccionsucursal,
            SUBSTR(to_char(fecha),4,5)
    )d
WHERE b.ciudad = d.ciudad AND b.direccionsucursal = d.direccionsucursal AND b.min = d.costo;

-- Diario (M�xima Demanda)
SELECT b.max, b.ciudad, b.direccionsucursal, d.fecha
FROM (
SELECT
    MAX(a.numvendidos) as max,
    a.ciudad,
    a.direccionsucursal
FROM
    (
        SELECT
            f.ciudad,
            f.direccionsucursal,
            f.fecha,
            SUM(c.cantidad) AS numvendidos
        FROM
            a_comprados c,
            a_factura f,
            a_producto p
        WHERE
            c.idfactura = f.idfactura AND
            p.codbarras = c.codigobarras AND
            p.nombrecategoria = 'Dulces'
        GROUP BY
            f.ciudad,
            f.direccionsucursal,
            f.fecha
    ) a
GROUP BY
    a.ciudad,
    a.direccionsucursal) b,(
        SELECT
            f.ciudad,
            f.direccionsucursal,
            f.fecha,
            SUM(c.cantidad) AS numvendidos
        FROM
            a_comprados c,
            a_factura f,
            a_producto p
        WHERE
            c.idfactura = f.idfactura AND            
            p.codbarras = c.codigobarras AND
            p.nombrecategoria = 'Dulces'
        GROUP BY
            f.ciudad,
            f.direccionsucursal,
            f.fecha
    )d
WHERE b.ciudad = d.ciudad AND b.direccionsucursal = d.direccionsucursal AND b.max = d.numvendidos;

-- Diario (M�nimo dinero)
SELECT b.min, b.ciudad, b.direccionsucursal, d.fecha
FROM (
SELECT
    MIN(a.costo) as min,
    a.ciudad,
    a.direccionsucursal
FROM
    (
        SELECT
            f.ciudad,
            f.direccionsucursal,
            f.fecha,
            SUM(f.costototal) AS costo
        FROM
            a_comprados c,
            a_factura f,
            a_producto p
        WHERE
            c.idfactura = f.idfactura AND
            p.codbarras = c.codigobarras AND
            p.nombrecategoria = 'Dulces'
        GROUP BY
            f.ciudad,
            f.direccionsucursal,
            f.fecha
    ) a
GROUP BY
    a.ciudad,
    a.direccionsucursal) b,(
        SELECT
            f.ciudad,
            f.direccionsucursal,
            f.fecha,
            SUM(f.costototal) AS costo
        FROM
            a_comprados c,
            a_factura f,
            a_producto p
        WHERE
            c.idfactura = f.idfactura AND
            p.codbarras = c.codigobarras AND
            p.nombrecategoria = 'Dulces'
        GROUP BY
            f.ciudad,
            f.direccionsucursal,
            f.fecha
    )d
WHERE b.ciudad = d.ciudad AND b.direccionsucursal = d.direccionsucursal AND b.min = d.costo;


-- Semana (M�nimo dinero)
SELECT b.min, b.ciudad, b.direccionsucursal, d.semana, d.a�o
FROM (
SELECT
    MIN(a.costo) as min,
    a.ciudad,
    a.direccionsucursal
FROM
    (
        SELECT
            f.ciudad,
            f.direccionsucursal,
            to_number(to_char(to_date(fecha,'yyyy-MM-dd'),'WW')) as semana,
            EXTRACT(YEAR FROM f.fecha) AS a�o,
            SUM(f.costototal) AS costo
        FROM
            a_comprados c,
            a_factura f,
            a_producto p
        WHERE
            c.idfactura = f.idfactura AND
            p.codbarras = c.codigobarras AND
            p.nombrecategoria = 'Dulces'
        GROUP BY
            f.ciudad,
            f.direccionsucursal,
            to_number(to_char(to_date(fecha,'yyyy-MM-dd'),'WW')),
            EXTRACT(YEAR FROM f.fecha)
    ) a
GROUP BY
    a.ciudad,
    a.direccionsucursal) b,(
        SELECT
            f.ciudad,
            f.direccionsucursal,
            to_number(to_char(to_date(fecha,'yyyy-MM-dd'),'WW')) as semana,
            EXTRACT(YEAR FROM f.fecha) AS a�o,
            SUM(f.costototal) AS costo
        FROM
            a_comprados c,
            a_factura f,
            a_producto p
        WHERE
            c.idfactura = f.idfactura AND
            p.codbarras = c.codigobarras AND
            p.nombrecategoria = 'Dulces'
        GROUP BY
            f.ciudad,
            f.direccionsucursal,
            to_number(to_char(to_date(fecha,'yyyy-MM-dd'),'WW')),
            EXTRACT(YEAR FROM f.fecha)
    )d
WHERE b.ciudad = d.ciudad AND b.direccionsucursal = d.direccionsucursal AND b.min = d.costo;

-- Semana (M�nima demanda)
SELECT b.min, b.ciudad, b.direccionsucursal, d.semana, d.a�o
FROM (
SELECT
    MIN(a.cantidad) as min,
    a.ciudad,
    a.direccionsucursal
FROM
    (
        SELECT
            f.ciudad,
            f.direccionsucursal,
            to_number(to_char(to_date(fecha,'yyyy-MM-dd'),'WW')) as semana,
            EXTRACT(YEAR FROM f.fecha) AS a�o,
            SUM(c.cantidad) AS cantidad
        FROM
            a_comprados c,
            a_factura f,
            a_producto p
        WHERE
            c.idfactura = f.idfactura AND
            p.codbarras = c.codigobarras AND
            p.nombrecategoria = 'Dulces'
        GROUP BY
            f.ciudad,
            f.direccionsucursal,
            to_number(to_char(to_date(fecha,'yyyy-MM-dd'),'WW')),
            EXTRACT(YEAR FROM f.fecha)
    ) a
GROUP BY
    a.ciudad,
    a.direccionsucursal) b,(
        SELECT
            f.ciudad,
            f.direccionsucursal,
            to_number(to_char(to_date(fecha,'yyyy-MM-dd'),'WW')) as semana,
            EXTRACT(YEAR FROM f.fecha) AS a�o,
            SUM(c.cantidad) AS cantidad
        FROM
            a_comprados c,
            a_factura f,
            a_producto p
        WHERE
            c.idfactura = f.idfactura AND
            p.codbarras = c.codigobarras AND
            p.nombrecategoria = 'Dulces'
        GROUP BY
            f.ciudad,
            f.direccionsucursal,
            to_number(to_char(to_date(fecha,'yyyy-MM-dd'),'WW')),
            EXTRACT(YEAR FROM f.fecha)
    )d
WHERE b.ciudad = d.ciudad AND b.direccionsucursal = d.direccionsucursal AND b.min = d.cantidad;
