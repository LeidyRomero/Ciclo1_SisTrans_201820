-- RF
-- Consulta productos disponibles(Los ? son cambiados por los parámetros deseados)
SELECT a.nombreproducto, a.codigobarras, a.cantidadminima, a.idestante, NVL((a.cantidadactual - b.total),a.cantidadactual)AS cantidadactual
FROM (
(SELECT e.ciudad, e.direccionsucursal, ce.codigobarras, ce.cantidadactual, ce.cantidadminima, ce.idestante, p.nombreproducto
FROM A_CANTIDAD_EN_ESTANTES ce, A_ESTANTE e, A_PRODUCTO p
WHERE ce.idestante = e.idestante AND ce.codigobarras = p.codbarras AND e.ciudad = ? AND e.direccionsucursal = ?) a
    LEFT OUTER JOIN 
(SELECT  c.ciudad, c.direccionsucursal, pc.codbarras, SUM(pc.cantidad) AS total
FROM A_PRODUCTOS_CARRITO pc, A_CARRITO c
WHERE pc.idcarrito = c.idcarrito
GROUP BY c.ciudad, c.direccionsucursal, pc.codbarras) b 
    ON (b.ciudad = a.ciudad AND b.direccionsucursal = a.direccionsucursal AND a.codigobarras = b.codbarras));