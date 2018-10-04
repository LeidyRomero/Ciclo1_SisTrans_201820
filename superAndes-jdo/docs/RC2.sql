-- Sentencia para consultar las 20 promociones que mas vendieron en menos tiempo.
-- Los ? son reemplazados por los valores que se desean insertar
SELECT
    b.idpromocion,
    b.codigobarras,
    b.fechainicial,
    b.fechafinal,
    b.unidadesdisponibles,
    b.unidadesvendidas,
    b.descripcion
FROM
    (
        SELECT
            descripcion,
            idpromocion,
            codigobarras,
            fechafinal - fechainicial AS duracion,
            unidadesvendidas
        FROM
            a_promocion
        WHERE
            estado = 'FINALIZADA'
    ) a,
    a_promocion b
WHERE
    ROWNUM < 21
    AND a.idpromocion = b.idpromocion
ORDER BY
    unidadesvendidas / duracion DESC;