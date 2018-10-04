-- Sentencia para cambiar el estado de una promocion en la base de datos
-- Los ? son reemplazados por los valores que se desean insertar
UPDATE A_PROMOCION SET ESTADO = ?, FECHAFINAL = ? WHERE IDPROMCION = ?;

-- Setencia para consultar todas las promociones que deben finalizar 
SELECT * 
FROM A_PROMOCION 
WHERE ESTADO = 'VIGENTE' AND (UNIDADESDISPONIBLES = 0 OR FECHAFINAL <= SYSDATE);