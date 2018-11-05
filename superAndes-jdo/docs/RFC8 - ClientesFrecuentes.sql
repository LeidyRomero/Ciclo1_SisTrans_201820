-- RFC8
-- Consulta productos disponibles(Los ? son cambiados por los parámetros deseados)
SELECT
    correocliente
FROM ( SELECT
    count(),
    correocliente
FROM
    (
        SELECT
            COUNT(EXTRACT(MONTH FROM fecha) ) AS numerofacturas,
            EXTRACT(MONTH FROM fecha),
            correocliente
        FROM
            a_factura
        WHERE
            direccionsucursal = 'CR 139 139-99'
            AND ciudad = 'Bucaramanga'
        GROUP BY
            EXTRACT(MONTH FROM fecha),
            correocliente
        HAVING
            COUNT(EXTRACT(MONTH FROM fecha) ) >= 2
    )
GROUP BY
    correocliente 
HAVING count() = ( SELECT
    EXTRACT(MONTH FROM(
        SELECT
            SYSDATE
        FROM
            dual
    ) ) - EXTRACT(MONTH FROM(
        SELECT
            MIN(fecha)
        FROM
            a_factura
    ) ) +
12 * ( EXTRACT(YEAR FROM(
    SELECT
        SYSDATE
    FROM
        dual
) ) - EXTRACT(MONTH FROM(
    SELECT
        MIN(fecha)
    FROM
        a_factura
) ) ) AS datediff from dual ) );