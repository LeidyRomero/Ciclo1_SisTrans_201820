-- Sentencia para consultar el dinero recolectado por las sucursales en un periodo
-- de tiempo dado.
-- Los ? son reemplazados por los valores que se desean insertar
SELECT SUM(fac.costototal) AS dinerorecolectado, direccionsucursal, ciudad
FROM A_FACTURA fac, A_FACTURAS fa
WHERE fac.idfactura = fa.idfactura AND fac.fecha BETWEEN ? AND ?
GROUP BY direccionsucursal, ciudad;