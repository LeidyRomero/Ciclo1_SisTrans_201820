-- RF15
-- Pagar compra(Los ? son cambiados por los parámetros deseados)
--Crea la factura
INSERT INTO A_FACTURA (idfactura, costototal, fecha, correocliente, ciudad, direccionsucursal) values (?, ?, ?, ?, ?, ?);
-- Consulta los productos del carrito
SELECT * FROM A_PRODUCTOS_CARRITO WHERE idcarrito = ?;
--Los inserta en comprados
INSERT INTO A_COMPRADOS (codigobarras, cantidad, preciototal, idfactura) values (?,?,?,?);
--Aumenta el costo de la factura
UPDATE A_FACTURA SET costototal = costototal + ? WHERE idfactura = ?;
-- Disminuye la cantidad en estantes
UPDATE A_CANTIDAD_EN_ESTANTES SET cantidadactual = cantidadactual - ? WHERE codigobarras = ? AND idestante = ?;
-- Los saca del carrito
DELETE FROM A_PRODUCTOS_CARRITO WHERE idcarrito = ? AND codbarras = ?;