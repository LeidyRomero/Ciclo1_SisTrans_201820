-- RF14
-- Devolver producto del carrito (Los ? son cambiados por los par�metros deseados)
UPDATE A_PRODUCTOS_CARRITO SET cantidad = cantidad-? WHERE idcarrito = ? AND codbarras = ?;