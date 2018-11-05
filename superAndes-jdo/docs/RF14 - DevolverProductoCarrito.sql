-- RF14
-- Devolver producto del carrito (Los ? son cambiados por los parámetros deseados)
UPDATE A_PRODUCTOS_CARRITO SET cantidad = cantidad-? WHERE idcarrito = ? AND codbarras = ?;