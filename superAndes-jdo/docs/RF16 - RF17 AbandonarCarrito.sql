-- RF16 - RF17
-- Abandonar carrito (Los ? son cambiados por los parámetros deseados)
-- Saca todos los productos del carrito
DELETE FROM A_PRODUCTOS_CARRITO WHERE idcarrito = ?;
-- Elimina el carrito
DELETE FORM A_CARRITO WHERE idcarrito = ?;