-- Sentencia para modifica el estado de una orden de pedido en la base de datos
-- Los ? son reemplazados por los valores que se desean insertar
UPDATE A_ORDEN_PEDIDO SET estado = 'Recibido', calificacionpedido = ?, fechaentrega = ?  
WHERE idpedido = ?;

-- Setencia para actualizar la cantidad en bodega del prodcuto recibido
UPDATE A_CANTIDAD_EN_BODEGA SET cantidadactual = cantidadactual + ? 
WHERE direccionbodega = ? AND direccionsucursal = ? AND ciudad = ?;