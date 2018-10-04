-- Sentencia para registrar una promocion en la base de datos
-- Los ? son reemplazados por los valores que se desean insertar
INSERT INTO A_PROMOCION (FECHAINICIAL, FECHAFINAL, DESCRIPCION, CODIGOBARRAS, IDPROMOCION, UNIDADESVENDIDAS, UNIDADESDISPONIBLES, ESTADO) 
VALUES (?, ?, ?, ?, ?, ?, ?, ?);

-- Sentencia para asociar la promocion creada a la sucursal dada en la base de datos
INSERT INTO A_PROMOCIONES (IDPROMOCION, DIRECCIONSUCURSAL, CIUDAD)
VALUES(?, ?, ?);