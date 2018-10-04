-- Sentencia para registrar una orden de pedido en la base de datos
-- Los ? son reemplazados por los valores que se desean insertar
INSERT INTO A_ORDEN_PEDIDO (fechaesperadaentrega, estado, fechaentrega, calificacionpedido, nitproveedor, ciudad, direccionsucursal, direccionbodega, idpedido) 
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);

-- Sentencia para relacionar el producto a la orden de pedido creada en la base de datos
INSERT INTO A_PEDIDO_PRODUCTO (codigobarras, idpedido,cantidadproducto, precioproducto) 
VALUES (?,?,?,?);