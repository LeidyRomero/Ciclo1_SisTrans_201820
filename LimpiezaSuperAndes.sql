--- Sentencias SQL para la creaci�n del esquema de parranderos
--- Las tablas tienen prefijo A_ para facilitar su acceso desde SQL Developer

-- USO
-- Copie el contenido deseado de este archivo en una pesta�a SQL de SQL Developer
-- Ejec�telo como un script - Utilice el bot�n correspondiente de la pesta�a utilizada
    
-- Eliminar todas las tablas de la base de datos
DROP TABLE "A_BODEGA" CASCADE CONSTRAINTS;
DROP TABLE "A_CANTIDAD_EN_BODEGA" CASCADE CONSTRAINTS;
DROP TABLE "A_CANTIDAD_EN_ESTANTES" CASCADE CONSTRAINTS;
DROP TABLE "A_CATEGORIA" CASCADE CONSTRAINTS;
DROP TABLE "A_CLIENTE" CASCADE CONSTRAINTS;
DROP TABLE "A_COMPRADOS" CASCADE CONSTRAINTS;
DROP TABLE "A_EMPRESA" CASCADE CONSTRAINTS;
DROP TABLE "A_ESTANTE" CASCADE CONSTRAINTS;
DROP TABLE "A_FACTURA" CASCADE CONSTRAINTS;
DROP TABLE "A_ORDEN_PEDIDO" CASCADE CONSTRAINTS;
DROP TABLE "A_PEDIDO_PRODUCTO" CASCADE CONSTRAINTS;
DROP TABLE "A_PERSONA_NATURAL" CASCADE CONSTRAINTS;
DROP TABLE "A_PRODUCTO" CASCADE CONSTRAINTS;
DROP TABLE "A_PRODUCTOS_OFRECIDOS" CASCADE CONSTRAINTS;
DROP TABLE "A_PROMOCION" CASCADE CONSTRAINTS;
DROP TABLE "A_PROMO_UNIDADES" CASCADE CONSTRAINTS;
DROP TABLE "A_PROMO_CANTIDADES" CASCADE CONSTRAINTS;
DROP TABLE "A_PROMO_DESCUENTO" CASCADE CONSTRAINTS;
DROP TABLE "A_PROMO_UNIDADES_DESCUENTO" CASCADE CONSTRAINTS;
DROP TABLE "A_PROMOCIONES" CASCADE CONSTRAINTS;
DROP TABLE "A_PROVEEDOR" CASCADE CONSTRAINTS;
DROP TABLE "A_PROVEEN" CASCADE CONSTRAINTS;
DROP TABLE "A_SUCURSAL" CASCADE CONSTRAINTS;
DROP TABLE "A_TIPO_PRODUCTO" CASCADE CONSTRAINTS;
DROP TABLE "A_CARRITO" CASCADE CONSTRAINTS;
DROP TABLE "A_PRODUCTOS_CARRITO" CASCADE CONSTRAINTS;
DROP SEQUENCE "pedidos_sequence";
DROP SEQUENCE "promocion_sequence";
DROP SEQUENCE "factura_sequence";
DROP SEQUENCE "carrito_sequence"
COMMIT;

-- Eliminar el contenido de todas las tablas de la base de datos
-- El orden es importante.
DELETE FROM A_PRODUCTOS_CARRITO;
DELETE FROM A_CARRITO;
DELETE FROM A_PRODUCTOS_OFRECIDOS;
DELETE FROM A_CANTIDAD_EN_ESTANTES;
DELETE FROM A_CANTIDAD_EN_BODEGA;
DELETE FROM A_COMPRADOS;
DELETE FROM A_PEDIDO_PRODUCTO;
DELETE FROM A_ORDEN_PEDIDO;
DELETE FROM A_BODEGA;
DELETE FROM A_PROMO_UNIDADES;
DELETE FROM A_PROMO_CANTIDADES;
DELETE FROM A_PROMO_UNIDADES_DESCUENTO;
DELETE FROM A_PROMO_DESCUENTO;
DELETE FROM A_PROMOCIONES;
DELETE FROM A_PROMOCION;
DELETE FROM A_ESTANTE;
DELETE FROM A_PROVEEN;
DELETE FROM A_FACTURA;
DELETE FROM A_TIPO_PRODUCTO;
DELETE FROM A_PROVEEDOR;
DELETE FROM A_EMPRESA;
DELETE FROM A_PERSONA_NATURAL;
DELETE FROM A_CLIENTE;
DELETE FROM A_SUCURSAL;
DELETE FROM A_PRODUCTO;
DELETE FROM A_CATEGORIA;
COMMIT;

