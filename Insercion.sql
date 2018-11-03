------------------------------------------------------------------------------------
--SUCURSAL
INSERT INTO a_sucursal (tamanio, direccionsucursal, ciudad, nombresucursal) VALUES (10, 'CR 139 139-99', 'Bucaramanga', 'A13');
INSERT INTO a_sucursal (tamanio, direccionsucursal, ciudad, nombresucursal) VALUES (1, 'CR 13 13-9', 'Arauca', 'F3');
INSERT INTO a_sucursal (tamanio, direccionsucursal, ciudad, nombresucursal) VALUES (60, 'CL 50 26A-8 SUR', 'Cartagena', 'N5');
INSERT INTO a_sucursal (tamanio, direccionsucursal, ciudad, nombresucursal) VALUES (30, 'CL 7 5-74', 'Bogotá', 'B1');
INSERT INTO a_sucursal (tamanio, direccionsucursal, ciudad, nombresucursal) VALUES (25, 'CLL 34 43-65', 'Medellín', 'M1');
INSERT INTO a_sucursal (tamanio, direccionsucursal, ciudad, nombresucursal) VALUES (100, 'CL 50 49-33', 'Medellín', 'M2');
INSERT INTO a_sucursal (tamanio, direccionsucursal, ciudad, nombresucursal) VALUES (65, 'CR 8 13-68', 'Cali', 'C1');
INSERT INTO a_sucursal (tamanio, direccionsucursal, ciudad, nombresucursal) VALUES (68, 'AV 6A NORTE 35-47', 'Cali', 'C2');
INSERT INTO a_sucursal (tamanio, direccionsucursal, ciudad, nombresucursal) VALUES (43, 'CR 41 36-81', 'Barranquilla', 'Q1');
INSERT INTO a_sucursal (tamanio, direccionsucursal, ciudad, nombresucursal) VALUES (100, 'CR 10 14-71', 'Pereira', 'P1');
------------------------------------------------------------------------------------
--CLIENTE
INSERT INTO A_CLIENTE (correo, nombreconsumidor,puntos) VALUES('empresa1@hotmail.com','empresa1',10);
INSERT INTO A_CLIENTE (correo, nombreconsumidor,puntos) VALUES('empresa2@hotmail.com','empresa2',20);
INSERT INTO A_CLIENTE (correo, nombreconsumidor,puntos) VALUES('empresa3@hotmail.com','empresa3',30);
INSERT INTO A_CLIENTE (correo, nombreconsumidor,puntos) VALUES('empresa4@hotmail.com','empresa4',40);
INSERT INTO A_CLIENTE (correo, nombreconsumidor,puntos) VALUES('empresa5@hotmail.com','empresa5',50);
INSERT INTO A_CLIENTE (correo, nombreconsumidor,puntos) VALUES('empresa6@hotmail.com','empresa6',60);
INSERT INTO A_CLIENTE (correo, nombreconsumidor,puntos) VALUES('empresa7@hotmail.com','empresa7',70);
INSERT INTO A_CLIENTE (correo, nombreconsumidor,puntos) VALUES('empresa8@hotmail.com','empresa8',80);
INSERT INTO A_CLIENTE (correo, nombreconsumidor,puntos) VALUES('empresa9@hotmail.com','empresa9',90);
INSERT INTO A_CLIENTE (correo, nombreconsumidor,puntos) VALUES('empresa10@hotmail.com','empresa10',100);
INSERT INTO A_CLIENTE (correo, nombreconsumidor,puntos) VALUES('mocampo@gmail.com','Maria Ocampo',17);
INSERT INTO A_CLIENTE (correo, nombreconsumidor,puntos) VALUES('lromero@gmail.com','Leidy Romero',6);
INSERT INTO A_CLIENTE (correo, nombreconsumidor,puntos) VALUES('amalvarez@gmail.com','Ana Maria Alvarez',4);
INSERT INTO A_CLIENTE (correo, nombreconsumidor,puntos) VALUES('nvargas@gmail.com','Nhora Vargas',18);
INSERT INTO A_CLIENTE (correo, nombreconsumidor,puntos) VALUES('dsavedra@gmail.com','David Savedra',18);
INSERT INTO A_CLIENTE (correo, nombreconsumidor,puntos) VALUES('drivera@gmail.com','Dora Rivera',13);
INSERT INTO A_CLIENTE (correo, nombreconsumidor,puntos) VALUES('jlrosales@gmail.com','Jose Luis Rosales',27);
INSERT INTO A_CLIENTE (correo, nombreconsumidor,puntos) VALUES('ahernandez@gmail.com','Andres Hernandez',30);
INSERT INTO A_CLIENTE (correo, nombreconsumidor,puntos) VALUES('fmaya@gmail.com','Felipe Maya',30);
INSERT INTO A_CLIENTE (correo, nombreconsumidor,puntos) VALUES('socampo@gmail.com','Sebastian Ocampo',5);
------------------------------------------------------------------------------------
--PROVEEDOR
INSERT INTO A_PROVEEDOR (nitproveedor, nombreproveedor, calificacionproveedor) VALUES(8600203089,'Super Ricas','');
INSERT INTO A_PROVEEDOR (nitproveedor, nombreproveedor, calificacionproveedor) VALUES(8903041304,'Zenu','');
INSERT INTO A_PROVEEDOR (nitproveedor, nombreproveedor, calificacionproveedor) VALUES(8000009464,'Procter y Gamble','');
INSERT INTO A_PROVEEDOR (nitproveedor, nombreproveedor, calificacionproveedor) VALUES(8600049224,'Alqueria','');
INSERT INTO A_PROVEEDOR (nitproveedor, nombreproveedor, calificacionproveedor) VALUES(9010859970,'Cromatic','');
INSERT INTO A_PROVEEDOR (nitproveedor, nombreproveedor, calificacionproveedor) VALUES(8903018845,'Colombina','');
INSERT INTO A_PROVEEDOR (nitproveedor, nombreproveedor, calificacionproveedor) VALUES(9007360036,'Ramo','');
INSERT INTO A_PROVEEDOR (nitproveedor, nombreproveedor, calificacionproveedor) VALUES(8901018159,'Johnson','');
INSERT INTO A_PROVEEDOR (nitproveedor, nombreproveedor, calificacionproveedor) VALUES(9001785632,'Frubana','');
INSERT INTO A_PROVEEDOR (nitproveedor, nombreproveedor, calificacionproveedor) VALUES(8870253974,'El Bosque','');
------------------------------------------------------------------------------------
--BODEGA
-- Estas son las sucursales que ofrecen productos, todas ofrecen todos los productos
--'CR 139 139-99', 'Bucaramanga'
--'CR 13 13-9', 'Arauca'
--'CL 7 5-74', 'Bogotá'
--'CLL 34 43-65', 'Medellín'
--'CR 8 13-68', 'Cali'
--'CL 50 26A-8 SUR', 'Cartagena'
INSERT INTO a_bodega (tipobodega, volumenbodega, pesobodega, direccionbodega, direccionsucursal, ciudad) VALUES ('Perecederos', 4000, 2600, 'CL 26 #68-10', 'CR 139 139-99', 'Bucaramanga');
INSERT INTO a_bodega (tipobodega, volumenbodega, pesobodega, direccionbodega, direccionsucursal, ciudad) VALUES ('Congelados', 3500, 3000, 'DIAG 73 48-22', 'CR 139 139-99', 'Bucaramanga');
INSERT INTO a_bodega (tipobodega, volumenbodega, pesobodega, direccionbodega, direccionsucursal, ciudad) VALUES ('No perecederos', 10, 15, 'TRANS. 95C 21-03', 'CL 50 26A-8 SUR', 'Cartagena');
INSERT INTO a_bodega (tipobodega, volumenbodega, pesobodega, direccionbodega, direccionsucursal, ciudad) VALUES ('Aseo', 10000, 6578, 'CR 65 6-07', 'CR 139 139-99', 'Bucaramanga');
INSERT INTO a_bodega (tipobodega, volumenbodega, pesobodega, direccionbodega, direccionsucursal, ciudad) VALUES ('Frutas', 40000, 2000000, 'CL 260 #67-15', 'CR 139 139-99', 'Bucaramanga');
INSERT INTO a_bodega (tipobodega, volumenbodega, pesobodega, direccionbodega, direccionsucursal, ciudad) VALUES ('Verduras', 18700, 605000, 'DIAG 7 8-2', 'CR 139 139-99', 'Bucaramanga');
INSERT INTO a_bodega (tipobodega, volumenbodega, pesobodega, direccionbodega, direccionsucursal, ciudad) VALUES ('Lacteos', 84526, 987654, 'TRANS. 9A 1-4', 'CL 50 26A-8 SUR', 'Cartagena');
INSERT INTO a_bodega (tipobodega, volumenbodega, pesobodega, direccionbodega, direccionsucursal, ciudad) VALUES ('Dulces', 50000, 8987878, 'CR 87 41-66', 'CR 139 139-99', 'Bucaramanga');
INSERT INTO a_bodega (tipobodega, volumenbodega, pesobodega, direccionbodega, direccionsucursal, ciudad) VALUES ('Cuidado personal', 3567000, 8999900, 'CL 35 #94-73', 'CR 13 13-9', 'Arauca');
INSERT INTO a_bodega (tipobodega, volumenbodega, pesobodega, direccionbodega, direccionsucursal, ciudad) VALUES ('Carnes', 258, 300, 'DIAG 91 4-54', 'CR 13 13-9','Arauca');
------------------------------------------------------------------------------------
--CATEGORIA
INSERT INTO A_CATEGORIA(nombrecategoria) VALUES ('Congelados');
INSERT INTO A_CATEGORIA(nombrecategoria) VALUES ('Perecederos');
INSERT INTO A_CATEGORIA(nombrecategoria) VALUES ('No perecederos');
INSERT INTO A_CATEGORIA(nombrecategoria) VALUES ('Aseo');
INSERT INTO A_CATEGORIA(nombrecategoria) VALUES ('Cuidado personal');
INSERT INTO A_CATEGORIA(nombrecategoria) VALUES ('Lacteos');
INSERT INTO A_CATEGORIA(nombrecategoria) VALUES ('Carnes');
INSERT INTO A_CATEGORIA(nombrecategoria) VALUES ('Frutas');
INSERT INTO A_CATEGORIA(nombrecategoria) VALUES ('Verduras');
INSERT INTO A_CATEGORIA(nombrecategoria) VALUES ('Dulces');
INSERT INTO A_CATEGORIA(nombrecategoria) VALUES ('Especiales');
------------------------------------------------------------------------------------
--PRODUCTO
INSERT INTO a_producto (nombreproducto, marca, preciounitario, presentacion, preciounimedida, cantpresentacion, unidadmedida, codbarras, pesoproducto, volumenproducto, nombrecategoria) VALUES ('Papas de pollo', 'Super ricas', 1500, 'Paquete 30 gr', 10, 25, 'gr', 'FFFF', '32 gr', '1000 mm^3', 'Perecederos');
INSERT INTO a_producto (nombreproducto, marca, preciounitario, presentacion, preciounimedida, cantpresentacion, unidadmedida, codbarras, pesoproducto, volumenproducto, nombrecategoria) VALUES ('Manzana', 'Productos del bosque', 800, 'Individual', 50, 1, 'gr', 'AAAA', '20 gr', '10 cm^3', 'Frutas');
INSERT INTO a_producto (nombreproducto, marca, preciounitario, presentacion, preciounimedida, cantpresentacion, unidadmedida, codbarras, calidad, fechavencimiento, pesoproducto, volumenproducto, nombrecategoria) VALUES ('Salchichas', 'Zenu', 2700, 'Paquete doble', 100, 2, 'gr', '0000', 'bueno', TO_DATE('2017-10-22','yyyy-MM-dd'), '300 gr', '50 mm^3', 'Carnes');
INSERT INTO a_producto (nombreproducto, marca, preciounitario, presentacion, preciounimedida, cantpresentacion, unidadmedida, codbarras, calidad, fechavencimiento, pesoproducto, volumenproducto, nombrecategoria) VALUES ('Detergente', 'PyG', 3000, 'Paquete 300 gr', 358, 1, 'gr', '0ABC', 'bueno', TO_DATE('2017-10-22','yyyy-MM-dd'), '1200 gr', '1000 mm^3', 'Aseo');
INSERT INTO a_producto (nombreproducto, marca, preciounitario, presentacion, preciounimedida, cantpresentacion, unidadmedida, codbarras, calidad, fechavencimiento, pesoproducto, volumenproducto, nombrecategoria) VALUES ('Jabon', 'PyG', 2900, 'Paquete 30 gr', 20, 2, 'gr', 'EEEC', 'bueno', TO_DATE('2017-10-22','yyyy-MM-dd'), '78 gr', '12 mm^3', 'Aseo');
INSERT INTO a_producto (nombreproducto, marca, preciounitario, presentacion, preciounimedida, cantpresentacion, unidadmedida, codbarras, pesoproducto, volumenproducto, nombrecategoria) VALUES ('Leche deslactosada', 'Alqueria', 3000, '1200 ml', 30, 20, 'ml', 'BBBB', '1200 ml', '1000 ml^3', 'Lacteos');
INSERT INTO a_producto (nombreproducto, marca, preciounitario, presentacion, preciounimedida, cantpresentacion, unidadmedida, codbarras, pesoproducto, volumenproducto, nombrecategoria) VALUES ('Peinilla', 'Cromantic', 1800, 'Individual', 100, 350, 'gr', 'CCCC', '250 gr', '100 cm^3', 'Cuidado personal');
INSERT INTO a_producto (nombreproducto, marca, preciounitario, presentacion, preciounimedida, cantpresentacion, unidadmedida, codbarras, calidad, fechavencimiento, pesoproducto, volumenproducto, nombrecategoria) VALUES ('Lechuga', 'El bosque', 5000, 'Individual', 500, 20, 'gr', 'DDDD', 'bueno', TO_DATE('2017-12-22','yyyy-MM-dd'), '300 gr', '90 mm^3', 'Verduras');
INSERT INTO a_producto (nombreproducto, marca, preciounitario, presentacion, preciounimedida, cantpresentacion, unidadmedida, codbarras, calidad, fechavencimiento, pesoproducto, volumenproducto, nombrecategoria) VALUES ('Gomitas', 'Colombina', 2000, 'Paquete 200 gr', 38, 65, 'gr', 'EEEE', 'bueno', TO_DATE('2017-10-22','yyyy-MM-dd'), '1200 gr', '1000 mm^3', 'Dulces');
INSERT INTO a_producto (nombreproducto, marca, preciounitario, presentacion, preciounimedida, cantpresentacion, unidadmedida, codbarras, calidad, fechavencimiento, pesoproducto, volumenproducto, nombrecategoria) VALUES ('Galletas', 'Ramo', 900, 'Paquete 30 gr', 10, 50, 'gr', 'GGGG', 'bueno', TO_DATE('2017-10-22','yyyy-MM-dd'), '780 gr', '120 mm^3', 'Dulces');
------------------------------------------------------------------------------------
--PROVEEN
INSERT INTO A_PROVEEN (nitproveedor, codbarras) VALUES (9001785632,'AAAA');
INSERT INTO A_PROVEEN (nitproveedor, codbarras) VALUES (8600049224,'BBBB');
INSERT INTO A_PROVEEN (nitproveedor, codbarras) VALUES (9010859970,'CCCC');
INSERT INTO A_PROVEEN (nitproveedor, codbarras) VALUES (8903018845,'EEEE');
INSERT INTO A_PROVEEN (nitproveedor, codbarras) VALUES (8870253974,'DDDD');
INSERT INTO A_PROVEEN (nitproveedor, codbarras) VALUES (8600203089,'FFFF');
INSERT INTO A_PROVEEN (nitproveedor, codbarras) VALUES (9007360036,'GGGG');
INSERT INTO A_PROVEEN (nitproveedor, codbarras) VALUES (8903041304,'0000');
INSERT INTO A_PROVEEN (nitproveedor, codbarras) VALUES (8000009464,'0ABC');
INSERT INTO A_PROVEEN (nitproveedor, codbarras) VALUES (8000009464,'EEEC');
------------------------------------------------------------------------------------
--PRODUCTOS OFRECIDOS
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 139 139-99', 'Bucaramanga', 'AAAA', 20);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 139 139-99', 'Bucaramanga', 'BBBB', 30);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 139 139-99', 'Bucaramanga', 'CCCC', 40);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 139 139-99', 'Bucaramanga', 'EEEE', 20);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 139 139-99', 'Bucaramanga', 'DDDD', 30);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 13 13-9', 'Arauca', 'FFFF', 40);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 13 13-9', 'Arauca', 'GGGG', 20);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 13 13-9', 'Arauca', '0000', 30);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 13 13-9', 'Arauca', '0ABC', 40);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 13 13-9', 'Arauca', 'EEEC', 20);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 13 13-9', 'Arauca', 'AAAA', 30);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 13 13-9', 'Arauca', 'BBBB', 40);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 13 13-9', 'Arauca', 'CCCC', 20);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 13 13-9', 'Arauca', 'EEEE', 30);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 13 13-9', 'Arauca', 'DDDD', 40);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 139 139-99', 'Bucaramanga', 'FFFF', 20);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 139 139-99', 'Bucaramanga', 'GGGG', 30);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 139 139-99', 'Bucaramanga', '0000', 40);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 139 139-99', 'Bucaramanga', '0ABC', 20);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 139 139-99', 'Bucaramanga', 'EEEC', 30);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CL 7 5-74', 'Bogotá', 'AAAA', 40);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CL 7 5-74', 'Bogotá', 'BBBB', 20);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CL 7 5-74', 'Bogotá', 'CCCC', 30);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CL 7 5-74', 'Bogotá', 'EEEE', 40);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CL 7 5-74', 'Bogotá', 'DDDD', 20);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CL 7 5-74', 'Bogotá', 'FFFF', 30);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CL 7 5-74', 'Bogotá', 'GGGG', 40);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CL 7 5-74', 'Bogotá', '0000', 20);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CL 7 5-74', 'Bogotá', '0ABC', 30);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CL 7 5-74', 'Bogotá', 'EEEC', 40);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CLL 34 43-65', 'Medellín', 'AAAA', 20);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CLL 34 43-65', 'Medellín', 'BBBB', 30);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CLL 34 43-65', 'Medellín', 'CCCC', 40);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CLL 34 43-65', 'Medellín', 'EEEE', 20);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CLL 34 43-65', 'Medellín', 'DDDD', 30);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CLL 34 43-65', 'Medellín', 'FFFF', 40);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CLL 34 43-65', 'Medellín', 'GGGG', 20);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CLL 34 43-65', 'Medellín', '0000', 30);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CLL 34 43-65', 'Medellín', '0ABC', 40);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CLL 34 43-65', 'Medellín', 'EEEC', 20);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 8 13-68', 'Cali', 'AAAA', 30);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 8 13-68', 'Cali', 'BBBB', 40);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 8 13-68', 'Cali', 'CCCC', 20);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 8 13-68', 'Cali', 'EEEE', 30);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 8 13-68', 'Cali', 'DDDD', 40);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 8 13-68', 'Cali', 'FFFF', 20);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 8 13-68', 'Cali', 'GGGG', 30);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 8 13-68', 'Cali', '0000', 40);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 8 13-68', 'Cali', '0ABC', 20);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CR 8 13-68', 'Cali', 'EEEC', 30);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CL 50 26A-8 SUR', 'Cartagena', 'AAAA', 40);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CL 50 26A-8 SUR', 'Cartagena', 'BBBB', 20);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CL 50 26A-8 SUR', 'Cartagena', 'CCCC', 30);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CL 50 26A-8 SUR', 'Cartagena', 'EEEE', 40);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CL 50 26A-8 SUR', 'Cartagena', 'DDDD', 20);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CL 50 26A-8 SUR', 'Cartagena', 'FFFF', 30);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CL 50 26A-8 SUR', 'Cartagena', 'GGGG', 40);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CL 50 26A-8 SUR', 'Cartagena', '0000', 20);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CL 50 26A-8 SUR', 'Cartagena', '0ABC', 30);
INSERT INTO A_PRODUCTOS_OFRECIDOS (direccionsucursal, ciudad, codigobarras, nivelreorden) VALUES('CL 50 26A-8 SUR', 'Cartagena', 'EEEC', 40);
------------------------------------------------------------------------------------
--CANTIDAD EN BODEGA
-- Estas son las sucursales que ofrecen productos, todas ofrecen todos los productos
--'CR 139 139-99', 'Bucaramanga'
--'CR 13 13-9', 'Arauca'
--'CL 7 5-74', 'Bogotá'
--'CLL 34 43-65', 'Medellín'
--'CR 8 13-68', 'Cali'
--'CL 50 26A-8 SUR', 'Cartagena'
INSERT INTO a_cantidad_en_bodega (codigobarras, direccionbodega, direccionsucursal, ciudad, cantidadactual, cantidadminima) VALUES ('FFFF', 'CL 26 #68-10', 'CR 139 139-99', 'Bucaramanga', 2000, 100);
INSERT INTO a_cantidad_en_bodega (codigobarras, direccionbodega, direccionsucursal, ciudad, cantidadactual, cantidadminima) VALUES ('AAAA', 'CL 26 #68-10', 'CR 139 139-99', 'Bucaramanga', 3000, 250);
INSERT INTO a_cantidad_en_bodega (codigobarras, direccionbodega, direccionsucursal, ciudad, cantidadactual, cantidadminima) VALUES ('0000', 'CR 65 6-07', 'CR 139 139-99', 'Bucaramanga', 8500, 30);
INSERT INTO a_cantidad_en_bodega (codigobarras, direccionbodega, direccionsucursal, ciudad, cantidadactual, cantidadminima) VALUES ('0ABC', 'TRANS. 95C 21-03', 'CL 50 26A-8 SUR', 'Cartagena', 4600, 1000);
INSERT INTO a_cantidad_en_bodega (codigobarras, direccionbodega, direccionsucursal, ciudad, cantidadactual, cantidadminima) VALUES ('EEEC', 'DIAG 73 48-22', 'CR 139 139-99', 'Bucaramanga', 2000, 100);
INSERT INTO a_cantidad_en_bodega (codigobarras, direccionbodega, direccionsucursal, ciudad, cantidadactual, cantidadminima) VALUES ('GGGG', 'CL 26 #68-10', 'CR 139 139-99', 'Bucaramanga', 2000, 100);
--INSERT INTO a_cantidad_en_bodega (codigobarras,direccionbodega,direccionsucursal,ciudad,cantidadactual,cantidadminima) VALUES ('','','','',,);
--INSERT INTO a_cantidad_en_bodega (codigobarras,direccionbodega,direccionsucursal,ciudad,cantidadactual,cantidadminima) VALUES ('','','','',,);
--INSERT INTO a_cantidad_en_bodega (codigobarras,direccionbodega,direccionsucursal,ciudad,cantidadactual,cantidadminima) VALUES ('','','','',,);
--INSERT INTO a_cantidad_en_bodega (codigobarras,direccionbodega,direccionsucursal,ciudad,cantidadactual,cantidadminima) VALUES ('','','','',,);
------------------------------------------------------------------------------------
--ESTANTE
-- Estas son las sucursales que ofrecen productos, todas ofrecen todos los productos
--'CR 139 139-99', 'Bucaramanga'
--'CR 13 13-9', 'Arauca'
--'CL 7 5-74', 'Bogotá'
--'CLL 34 43-65', 'Medellín'
--'CR 8 13-68', 'Cali'
--'CL 50 26A-8 SUR', 'Cartagena'
INSERT INTO a_estante (tipoestante, volumenestante, idestante, pesoestante, nivelabastecimiento, direccionsucursal, ciudad) VALUES ('Congelados', 100, 2, 50, 10, 'CR 139 139-99', 'Bucaramanga');
INSERT INTO a_estante (tipoestante, volumenestante, idestante, pesoestante, nivelabastecimiento, direccionsucursal, ciudad) VALUES ('No perecederos', 200, 3, 78, 90, 'CR 139 139-99', 'Bucaramanga');
--INSERT INTO a_estante(tipoestante,volumenestante,idestante,pesoestante,nivelabastecimiento,direccionsucursal,ciudad) VALUES ('',,,,,'','');
--INSERT INTO a_estante(tipoestante,volumenestante,idestante,pesoestante,nivelabastecimiento,direccionsucursal,ciudad) VALUES ('',,,,,'','');
--INSERT INTO a_estante(tipoestante,volumenestante,idestante,pesoestante,nivelabastecimiento,direccionsucursal,ciudad) VALUES ('',,,,,'','');
--INSERT INTO a_estante(tipoestante,volumenestante,idestante,pesoestante,nivelabastecimiento,direccionsucursal,ciudad) VALUES ('',,,,,'','');
--INSERT INTO a_estante(tipoestante,volumenestante,idestante,pesoestante,nivelabastecimiento,direccionsucursal,ciudad) VALUES ('',,,,,'','');
--INSERT INTO a_estante(tipoestante,volumenestante,idestante,pesoestante,nivelabastecimiento,direccionsucursal,ciudad) VALUES ('',,,,,'','');
--INSERT INTO a_estante(tipoestante,volumenestante,idestante,pesoestante,nivelabastecimiento,direccionsucursal,ciudad) VALUES ('',,,,,'','');
--INSERT INTO a_estante(tipoestante,volumenestante,idestante,pesoestante,nivelabastecimiento,direccionsucursal,ciudad) VALUES ('',,,,,'','');
------------------------------------------------------------------------------------
--PERSONA_NATURAL
--INSERT INTO A_PERSONA_NATURAL (correo, documento) VALUES('',);
--INSERT INTO A_PERSONA_NATURAL (correo, documento) VALUES('',);
--INSERT INTO A_PERSONA_NATURAL (correo, documento) VALUES('',);
--INSERT INTO A_PERSONA_NATURAL (correo, documento) VALUES('',);
--INSERT INTO A_PERSONA_NATURAL (correo, documento) VALUES('',);
--INSERT INTO A_PERSONA_NATURAL (correo, documento) VALUES('',);
--INSERT INTO A_PERSONA_NATURAL (correo, documento) VALUES('',);
--INSERT INTO A_PERSONA_NATURAL (correo, documento) VALUES('',);
--INSERT INTO A_PERSONA_NATURAL (correo, documento) VALUES('',);
--INSERT INTO A_PERSONA_NATURAL (correo, documento) VALUES('',);
--INSERT INTO A_PERSONA_NATURAL (correo, documento) VALUES('',);
------------------------------------------------------------------------------------
--EMPRESA
--INSERT INTO A_EMPRESA (correo, nitempresa,direccion) VALUES('',,'');
--INSERT INTO A_EMPRESA (correo, nitempresa,direccion) VALUES('',,'');
--INSERT INTO A_EMPRESA (correo, nitempresa,direccion) VALUES('',,'');
--INSERT INTO A_EMPRESA (correo, nitempresa,direccion) VALUES('',,'');
--INSERT INTO A_EMPRESA (correo, nitempresa,direccion) VALUES('',,'');
--INSERT INTO A_EMPRESA (correo, nitempresa,direccion) VALUES('',,'');
--INSERT INTO A_EMPRESA (correo, nitempresa,direccion) VALUES('',,'');
--INSERT INTO A_EMPRESA (correo, nitempresa,direccion) VALUES('',,'');
--INSERT INTO A_EMPRESA (correo, nitempresa,direccion) VALUES('',,'');
--INSERT INTO A_EMPRESA (correo, nitempresa,direccion) VALUES('',,'');
------------------------------------------------------------------------------------
--TIPO_PRODUCTO
INSERT INTO A_TIPO_PRODUCTO(nombretipo,nombrecategoria) VALUES('A','Aseo');
INSERT INTO A_TIPO_PRODUCTO(nombretipo,nombrecategoria) VALUES('B','Carnes');
INSERT INTO A_TIPO_PRODUCTO(nombretipo,nombrecategoria) VALUES('C','Congelados');
INSERT INTO A_TIPO_PRODUCTO(nombretipo,nombrecategoria) VALUES('D','Cuidado personal');
INSERT INTO A_TIPO_PRODUCTO(nombretipo,nombrecategoria) VALUES('E','Dulces');
INSERT INTO A_TIPO_PRODUCTO(nombretipo,nombrecategoria) VALUES('F','Especiales');
INSERT INTO A_TIPO_PRODUCTO(nombretipo,nombrecategoria) VALUES('G','Frutas');
INSERT INTO A_TIPO_PRODUCTO(nombretipo,nombrecategoria) VALUES('H','Lacteos');
INSERT INTO A_TIPO_PRODUCTO(nombretipo,nombrecategoria) VALUES('I','No perecederos');
INSERT INTO A_TIPO_PRODUCTO(nombretipo,nombrecategoria) VALUES('J','Lacteos');
------------------------------------------------------------------------------------
--PEDIDO_PRODUCTO
--INSERT INTO A_PEDIDO_PRODUCTO(codigobarras,idpedido,cantidadproducto,precioproducto) VALUES('',,,);
--INSERT INTO A_PEDIDO_PRODUCTO(codigobarras,idpedido,cantidadproducto,precioproducto) VALUES('',,,);
--INSERT INTO A_PEDIDO_PRODUCTO(codigobarras,idpedido,cantidadproducto,precioproducto) VALUES('',,,);
--INSERT INTO A_PEDIDO_PRODUCTO(codigobarras,idpedido,cantidadproducto,precioproducto) VALUES('',,,);
--INSERT INTO A_PEDIDO_PRODUCTO(codigobarras,idpedido,cantidadproducto,precioproducto) VALUES('',,,);
--INSERT INTO A_PEDIDO_PRODUCTO(codigobarras,idpedido,cantidadproducto,precioproducto) VALUES('',,,);
--INSERT INTO A_PEDIDO_PRODUCTO(codigobarras,idpedido,cantidadproducto,precioproducto) VALUES('',,,);
--INSERT INTO A_PEDIDO_PRODUCTO(codigobarras,idpedido,cantidadproducto,precioproducto) VALUES('',,,);
--INSERT INTO A_PEDIDO_PRODUCTO(codigobarras,idpedido,cantidadproducto,precioproducto) VALUES('',,,);
--INSERT INTO A_PEDIDO_PRODUCTO(codigobarras,idpedido,cantidadproducto,precioproducto) VALUES('',,,);
------------------------------------------------------------------------------------
--COMPRADOS
--INSERT INTO A_COMPRADOS(codigobarras,idfactura,cantidad,preciototal) VALUES('',,,);
--INSERT INTO A_COMPRADOS(codigobarras,idfactura,cantidad,preciototal) VALUES('',,,);
--INSERT INTO A_COMPRADOS(codigobarras,idfactura,cantidad,preciototal) VALUES('',,,);
--INSERT INTO A_COMPRADOS(codigobarras,idfactura,cantidad,preciototal) VALUES('',,,);
--INSERT INTO A_COMPRADOS(codigobarras,idfactura,cantidad,preciototal) VALUES('',,,);
--INSERT INTO A_COMPRADOS(codigobarras,idfactura,cantidad,preciototal) VALUES('',,,);
--INSERT INTO A_COMPRADOS(codigobarras,idfactura,cantidad,preciototal) VALUES('',,,);
--INSERT INTO A_COMPRADOS(codigobarras,idfactura,cantidad,preciototal) VALUES('',,,);
--INSERT INTO A_COMPRADOS(codigobarras,idfactura,cantidad,preciototal) VALUES('',,,);
--INSERT INTO A_COMPRADOS(codigobarras,idfactura,cantidad,preciototal) VALUES('',,,);
------------------------------------------------------------------------------------
--CANTIDAD EN ESTANTES
INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantidadactual, cantidadminima) VALUES ('AAAA',2,10,1);
INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantidadactual, cantidadminima) VALUES ('BBBB',3,10,1);
INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantidadactual, cantidadminima) VALUES ('0000',2,10,1);
INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantidadactual, cantidadminima) VALUES ('GGGG',3,10,1);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
--INSERT INTO A_CANTIDAD_EN_ESTANTES (codigobarras, idestante, cantiadactual, cantidadminima) VALUES ('',0,0,0);
------------------------------------------------------------------------------------
--FACTURA
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (1,TO_DATE('2018-05-10','yyyy-MM-dd'),15000,'empresa1@hotmail.com','Bucaramanga','CR 139 139-99');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (2,TO_DATE('2018-06-10','yyyy-MM-dd'),15000,'empresa2@hotmail.com','Arauca','CR 13 13-9');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (3,TO_DATE('2018-07-10','yyyy-MM-dd'),20000,'empresa3@hotmail.com','Bogotá','CL 7 5-74');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (4,TO_DATE('2018-09-10','yyyy-MM-dd'),20000,'empresa4@hotmail.com','Medellín','CLL 34 43-65');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (5,TO_DATE('2018-10-10','yyyy-MM-dd'),25000,'empresa5@hotmail.com','Cali','CR 8 13-68');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (6,TO_DATE('2018-05-11','yyyy-MM-dd'),25000,'empresa6@hotmail.com','Cartagena','CL 50 26A-8 SUR');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (7,TO_DATE('2018-06-11','yyyy-MM-dd'),30000,'empresa7@hotmail.com','Bucaramanga','CR 139 139-99');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (8,TO_DATE('2018-07-11','yyyy-MM-dd'),30000,'empresa8@hotmail.com','Arauca','CR 13 13-9');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (9,TO_DATE('2018-08-11','yyyy-MM-dd'),35000,'empresa9@hotmail.com','Bogotá','CL 7 5-74');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (10,TO_DATE('2018-09-11','yyyy-MM-dd'),35000,'empresa10@hotmail.com','Medellín','CLL 34 43-65');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (11,TO_DATE('2018-10-11','yyyy-MM-dd'),40000,'mocampo@gmail.com','Cali','CR 8 13-68');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (12,TO_DATE('2018-05-12','yyyy-MM-dd'),40000,'lromero@gmail.com','Cartagena','CL 50 26A-8 SUR');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (13,TO_DATE('2018-06-12','yyyy-MM-dd'),45000,'amalvarez@gmail.com','Bucaramanga','CR 139 139-99');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (14,TO_DATE('2018-07-12','yyyy-MM-dd'),45000,'nvargas@gmail.com','Arauca','CR 13 13-9');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (15,TO_DATE('2018-08-12','yyyy-MM-dd'),50000,'dsavedra@gmail.com','Bogotá','CL 7 5-74');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (16,TO_DATE('2018-09-12','yyyy-MM-dd'),50000,'drivera@gmail.com','Medellín','CLL 34 43-65');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (17,TO_DATE('2018-10-12','yyyy-MM-dd'),55000,'jlrosales@gmail.com','Cali','CR 8 13-68');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (18,TO_DATE('2018-05-13','yyyy-MM-dd'),55000,'ahernandez@gmail.com','Cartagena','CL 50 26A-8 SUR');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (19,TO_DATE('2018-06-13','yyyy-MM-dd'),60000,'fmaya@gmail.com','Bucaramanga','CR 139 139-99');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (20,TO_DATE('2018-07-13','yyyy-MM-dd'),60000,'socampo@gmail.com','Arauca','CR 13 13-9');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (21,TO_DATE('2018-08-13','yyyy-MM-dd'),65000,'empresa1@hotmail.com','Bogotá','CL 7 5-74');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (22,TO_DATE('2018-09-13','yyyy-MM-dd'),65000,'empresa2@hotmail.com','Medellín','CLL 34 43-65');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (23,TO_DATE('2018-10-13','yyyy-MM-dd'),70000,'empresa3@hotmail.com','Cali','CR 8 13-68');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (24,TO_DATE('2018-05-14','yyyy-MM-dd'),70000,'empresa4@hotmail.com','Cartagena','CL 50 26A-8 SUR');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (25,TO_DATE('2018-06-14','yyyy-MM-dd'),75000,'empresa5@hotmail.com','Bucaramanga','CR 139 139-99');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (26,TO_DATE('2018-07-14','yyyy-MM-dd'),75000,'empresa6@hotmail.com','Arauca','CR 13 13-9');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (27,TO_DATE('2018-08-14','yyyy-MM-dd'),80000,'empresa7@hotmail.com','Bogotá','CL 7 5-74');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (28,TO_DATE('2018-09-14','yyyy-MM-dd'),80000,'empresa8@hotmail.com','Medellín','CLL 34 43-65');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (29,TO_DATE('2018-10-14','yyyy-MM-dd'),85000,'empresa9@hotmail.com','Cali','CR 8 13-68');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (30,TO_DATE('2018-05-15','yyyy-MM-dd'),85000,'empresa10@hotmail.com','Cartagena','CL 50 26A-8 SUR');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (31,TO_DATE('2018-06-15','yyyy-MM-dd'),90000,'mocampo@gmail.com','Bucaramanga','CR 139 139-99');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (32,TO_DATE('2018-07-15','yyyy-MM-dd'),90000,'lromero@gmail.com','Arauca','CR 13 13-9');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (33,TO_DATE('2018-08-15','yyyy-MM-dd'),95000,'amalvarez@gmail.com','Bogotá','CL 7 5-74');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (34,TO_DATE('2018-09-15','yyyy-MM-dd'),95000,'nvargas@gmail.com','Medellín','CLL 34 43-65');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (35,TO_DATE('2018-10-15','yyyy-MM-dd'),100000,'dsavedra@gmail.com','Cali','CR 8 13-68');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (36,TO_DATE('2018-05-16','yyyy-MM-dd'),100000,'drivera@gmail.com','Cartagena','CL 50 26A-8 SUR');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (37,TO_DATE('2018-06-16','yyyy-MM-dd'),110000,'jlrosales@gmail.com','Bucaramanga','CR 139 139-99');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (38,TO_DATE('2018-07-16','yyyy-MM-dd'),110000,'ahernandez@gmail.com','Arauca','CR 13 13-9');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (39,TO_DATE('2018-08-16','yyyy-MM-dd'),105000,'fmaya@gmail.com','Bogotá','CL 7 5-74');
INSERT INTO A_FACTURA (idfactura, fecha, costototal, correocliente, ciudad, direccionsucursal) VALUES (40,TO_DATE('2018-09-16','yyyy-MM-dd'),105000,'socampo@gmail.com','Medellín','CLL 34 43-65');
------------------------------------------------------------------------------------
--ORDEN PEDIDO
--INSERT INTO A_ORDEN_PEDIDO (fechaespereadaentrega, estado, fechaentrega, calificacionpedido, nitproveedor, idpedido, ciudad, direccionsucursal, direccionbodega) VALUES (TO_DATE('','yyyy-MM-dd'), '', TO_DATE('','yyyy-MM-dd'), '', 0, 0, '', '', '');
--INSERT INTO A_ORDEN_PEDIDO (fechaespereadaentrega, estado, fechaentrega, calificacionpedido, nitproveedor, idpedido, ciudad, direccionsucursal, direccionbodega) VALUES (TO_DATE('','yyyy-MM-dd'), '', TO_DATE('','yyyy-MM-dd'), '', 0, 0, '', '', '');
--INSERT INTO A_ORDEN_PEDIDO (fechaespereadaentrega, estado, fechaentrega, calificacionpedido, nitproveedor, idpedido, ciudad, direccionsucursal, direccionbodega) VALUES (TO_DATE('','yyyy-MM-dd'), '', TO_DATE('','yyyy-MM-dd'), '', 0, 0, '', '', '');
--INSERT INTO A_ORDEN_PEDIDO (fechaespereadaentrega, estado, fechaentrega, calificacionpedido, nitproveedor, idpedido, ciudad, direccionsucursal, direccionbodega) VALUES (TO_DATE('','yyyy-MM-dd'), '', TO_DATE('','yyyy-MM-dd'), '', 0, 0, '', '', '');
--INSERT INTO A_ORDEN_PEDIDO (fechaespereadaentrega, estado, fechaentrega, calificacionpedido, nitproveedor, idpedido, ciudad, direccionsucursal, direccionbodega) VALUES (TO_DATE('','yyyy-MM-dd'), '', TO_DATE('','yyyy-MM-dd'), '', 0, 0, '', '', '');
--INSERT INTO A_ORDEN_PEDIDO (fechaespereadaentrega, estado, fechaentrega, calificacionpedido, nitproveedor, idpedido, ciudad, direccionsucursal, direccionbodega) VALUES (TO_DATE('','yyyy-MM-dd'), '', TO_DATE('','yyyy-MM-dd'), '', 0, 0, '', '', '');
--INSERT INTO A_ORDEN_PEDIDO (fechaespereadaentrega, estado, fechaentrega, calificacionpedido, nitproveedor, idpedido, ciudad, direccionsucursal, direccionbodega) VALUES (TO_DATE('','yyyy-MM-dd'), '', TO_DATE('','yyyy-MM-dd'), '', 0, 0, '', '', '');
--INSERT INTO A_ORDEN_PEDIDO (fechaespereadaentrega, estado, fechaentrega, calificacionpedido, nitproveedor, idpedido, ciudad, direccionsucursal, direccionbodega) VALUES (TO_DATE('','yyyy-MM-dd'), '', TO_DATE('','yyyy-MM-dd'), '', 0, 0, '', '', '');
--INSERT INTO A_ORDEN_PEDIDO (fechaespereadaentrega, estado, fechaentrega, calificacionpedido, nitproveedor, idpedido, ciudad, direccionsucursal, direccionbodega) VALUES (TO_DATE('','yyyy-MM-dd'), '', TO_DATE('','yyyy-MM-dd'), '', 0, 0, '', '', '');
--INSERT INTO A_ORDEN_PEDIDO (fechaespereadaentrega, estado, fechaentrega, calificacionpedido, nitproveedor, idpedido, ciudad, direccionsucursal, direccionbodega) VALUES (TO_DATE('','yyyy-MM-dd'), '', TO_DATE('','yyyy-MM-dd'), '', 0, 0, '', '', '');
--INSERT INTO A_ORDEN_PEDIDO (fechaespereadaentrega, estado, fechaentrega, calificacionpedido, nitproveedor, idpedido, ciudad, direccionsucursal, direccionbodega) VALUES (TO_DATE('','yyyy-MM-dd'), '', TO_DATE('','yyyy-MM-dd'), '', 0, 0, '', '', '');
------------------------------------------------------------------------------------
--PROMOCION
INSERT INTO A_PROMOCION (fechainicial, fechafinal, descripcion, idpromocion, unidadesdisponibles, unidadesvendidas, codigobarras, estado) VALUES (TO_DATE('2018-09-27','yyyy-MM-dd'), TO_DATE('2018-10-07','yyyy-MM-dd'), 'Descuento del 10%', 1, 30, 15, 'AAAA', 'FINALIZADA');
INSERT INTO A_PROMOCION (fechainicial, fechafinal, descripcion, idpromocion, unidadesdisponibles, unidadesvendidas, codigobarras, estado) VALUES (TO_DATE('2018-10-17','yyyy-MM-dd'), TO_DATE('2018-10-27','yyyy-MM-dd'), 'Descuento del 15%', 2, 30, 29, 'BBBB', 'FINALIZADA');
INSERT INTO A_PROMOCION (fechainicial, fechafinal, descripcion, idpromocion, unidadesdisponibles, unidadesvendidas, codigobarras, estado) VALUES (TO_DATE('2018-10-13','yyyy-MM-dd'), TO_DATE('2018-10-27','yyyy-MM-dd'), 'Descuento del 20%', 3, 30, 18, 'CCCC', 'FINALIZADA');
INSERT INTO A_PROMOCION (fechainicial, fechafinal, descripcion, idpromocion, unidadesdisponibles, unidadesvendidas, codigobarras, estado) VALUES (TO_DATE('2018-09-27','yyyy-MM-dd'), TO_DATE('2018-10-10','yyyy-MM-dd'), 'Descuento del 25%', 4, 30, 24, 'EEEE', 'FINALIZADA');
INSERT INTO A_PROMOCION (fechainicial, fechafinal, descripcion, idpromocion, unidadesdisponibles, unidadesvendidas, codigobarras, estado) VALUES (TO_DATE('2018-10-17','yyyy-MM-dd'), TO_DATE('2018-10-25','yyyy-MM-dd'), 'Descuento del 30%', 5, 30, 14, 'DDDD', 'FINALIZADA');
INSERT INTO A_PROMOCION (fechainicial, fechafinal, descripcion, idpromocion, unidadesdisponibles, unidadesvendidas, codigobarras, estado) VALUES (TO_DATE('2018-09-27','yyyy-MM-dd'), TO_DATE('2018-10-07','yyyy-MM-dd'), 'Descuento del 35%', 6, 30, 27, 'FFFF', 'FINALIZADA');
INSERT INTO A_PROMOCION (fechainicial, fechafinal, descripcion, idpromocion, unidadesdisponibles, unidadesvendidas, codigobarras, estado) VALUES (TO_DATE('2018-10-17','yyyy-MM-dd'), TO_DATE('2018-10-26','yyyy-MM-dd'), 'Pague 1 unidades, lleve 2', 7, 30, 7, 'GGGG', 'FINALIZADA');
INSERT INTO A_PROMOCION (fechainicial, fechafinal, descripcion, idpromocion, unidadesdisponibles, unidadesvendidas, codigobarras, estado) VALUES (TO_DATE('2018-09-27','yyyy-MM-dd'), TO_DATE('2018-10-17','yyyy-MM-dd'), 'Pague 2 unidades, lleve 3', 8, 30, 23, '0000', 'FINALIZADA');
INSERT INTO A_PROMOCION (fechainicial, fechafinal, descripcion, idpromocion, unidadesdisponibles, unidadesvendidas, codigobarras, estado) VALUES (TO_DATE('2018-10-17','yyyy-MM-dd'), TO_DATE('2018-10-24','yyyy-MM-dd'), 'Pague 1 unidades, lleve 2', 9, 30, 16, '0ABC', 'FINALIZADA');
INSERT INTO A_PROMOCION (fechainicial, fechafinal, descripcion, idpromocion, unidadesdisponibles, unidadesvendidas, codigobarras, estado) VALUES (TO_DATE('2018-09-27','yyyy-MM-dd'), TO_DATE('2018-10-07','yyyy-MM-dd'), 'Pague 2 unidades, lleve 3', 10, 30, 28, 'EEEC', 'FINALIZADA');
INSERT INTO A_PROMOCION (fechainicial, fechafinal, descripcion, idpromocion, unidadesdisponibles, unidadesvendidas, codigobarras, estado) VALUES (TO_DATE('2018-10-17','yyyy-MM-dd'), TO_DATE('2018-10-22','yyyy-MM-dd'), 'Pague 1 unidades, lleve 2', 11, 30, 19, 'AAAA', 'FINALIZADA');
INSERT INTO A_PROMOCION (fechainicial, fechafinal, descripcion, idpromocion, unidadesdisponibles, unidadesvendidas, codigobarras, estado) VALUES (TO_DATE('2018-09-27','yyyy-MM-dd'), TO_DATE('2018-10-13','yyyy-MM-dd'), 'Pague 2 unidades, lleve 3', 12, 30, 21, 'BBBB', 'FINALIZADA');
INSERT INTO A_PROMOCION (fechainicial, fechafinal, descripcion, idpromocion, unidadesdisponibles, unidadesvendidas, codigobarras, estado) VALUES (TO_DATE('2018-10-17','yyyy-MM-dd'), TO_DATE('2018-10-21','yyyy-MM-dd'), 'Pague 1 y lleve el siguiente con 20% de descuento', 13, 30, 3, 'CCCC', 'FINALIZADA');
INSERT INTO A_PROMOCION (fechainicial, fechafinal, descripcion, idpromocion, unidadesdisponibles, unidadesvendidas, codigobarras, estado) VALUES (TO_DATE('2018-09-27','yyyy-MM-dd'), TO_DATE('2018-10-24','yyyy-MM-dd'), 'Pague 2 y lleve el siguiente con 30% de descuento', 14, 30, 26, 'DDDD', 'FINALIZADA');
INSERT INTO A_PROMOCION (fechainicial, fechafinal, descripcion, idpromocion, unidadesdisponibles, unidadesvendidas, codigobarras, estado) VALUES (TO_DATE('2018-10-17','yyyy-MM-dd'), TO_DATE('2018-10-27','yyyy-MM-dd'), 'Pague 1 y lleve el siguiente con 40% de descuento', 15, 30, 5, 'EEEE', 'FINALIZADA');
INSERT INTO A_PROMOCION (fechainicial, fechafinal, descripcion, idpromocion, unidadesdisponibles, unidadesvendidas, codigobarras, estado) VALUES (TO_DATE('2018-09-27','yyyy-MM-dd'), TO_DATE('2018-10-05','yyyy-MM-dd'), 'Pague 2 y lleve el siguiente con 50% de descuento', 16, 30, 24, 'FFFF', 'FINALIZADA');
INSERT INTO A_PROMOCION (fechainicial, fechafinal, descripcion, idpromocion, unidadesdisponibles, unidadesvendidas, codigobarras, estado) VALUES (TO_DATE('2018-10-17','yyyy-MM-dd'), TO_DATE('2018-10-23','yyyy-MM-dd'), 'Pague 1 y lleve el siguiente con 60% de descuento', 17, 30, 12, 'GGGG', 'FINALIZADA');
INSERT INTO A_PROMOCION (fechainicial, fechafinal, descripcion, idpromocion, unidadesdisponibles, unidadesvendidas, codigobarras, estado) VALUES (TO_DATE('2018-09-27','yyyy-MM-dd'), TO_DATE('2018-10-07','yyyy-MM-dd'), 'Pague 2 y lleve el siguiente con 70% de descuento', 18, 30, 22, '0000', 'FINALIZADA');
INSERT INTO A_PROMOCION (fechainicial, fechafinal, descripcion, idpromocion, unidadesdisponibles, unidadesvendidas, codigobarras, estado) VALUES (TO_DATE('2018-10-17','yyyy-MM-dd'), TO_DATE('2018-10-27','yyyy-MM-dd'), 'Pague 170 cantidad, lleve 200', 19, 30, 11, '0ABC', 'FINALIZADA');
INSERT INTO A_PROMOCION (fechainicial, fechafinal, descripcion, idpromocion, unidadesdisponibles, unidadesvendidas, codigobarras, estado) VALUES (TO_DATE('2018-09-27','yyyy-MM-dd'), TO_DATE('2018-10-04','yyyy-MM-dd'), 'Pague 200 cantidad, lleve 250', 20, 30, 28, 'EEEC', 'FINALIZADA');
INSERT INTO A_PROMOCION (fechainicial, fechafinal, descripcion, idpromocion, unidadesdisponibles, unidadesvendidas, codigobarras, estado) VALUES (TO_DATE('2018-10-17','yyyy-MM-dd'), TO_DATE('2018-10-20','yyyy-MM-dd'), 'Pague 120 cantidad, lleve 160', 21, 30, 17, 'AAAA', 'FINALIZADA');
INSERT INTO A_PROMOCION (fechainicial, fechafinal, descripcion, idpromocion, unidadesdisponibles, unidadesvendidas, codigobarras, estado) VALUES (TO_DATE('2018-09-27','yyyy-MM-dd'), TO_DATE('2018-10-27','yyyy-MM-dd'), 'Pague 70 cantidad, lleve 120', 22, 30, 26, 'BBBB', 'FINALIZADA');
INSERT INTO A_PROMOCION (fechainicial, fechafinal, descripcion, idpromocion, unidadesdisponibles, unidadesvendidas, codigobarras, estado) VALUES (TO_DATE('2018-10-17','yyyy-MM-dd'), TO_DATE('2018-10-27','yyyy-MM-dd'), 'Pague 90 cantidad, lleve 130', 23, 30, 16, 'CCCC', 'FINALIZADA');
INSERT INTO A_PROMOCION (fechainicial, fechafinal, descripcion, idpromocion, unidadesdisponibles, unidadesvendidas, codigobarras, estado) VALUES (TO_DATE('2018-09-27','yyyy-MM-dd'), TO_DATE('2018-10-07','yyyy-MM-dd'), 'Pague 140 cantidad, lleve 180', 24, 30, 30, 'DDDD', 'FINALIZADA');
------------------------------------------------------------------------------------
--PROMO CANTIDADES
INSERT INTO A_PROMO_CANTIDADES (cantidadpagar, cantidadllevar, idpromocion) VALUES (170, 200, 19);
INSERT INTO A_PROMO_CANTIDADES (cantidadpagar, cantidadllevar, idpromocion) VALUES (200, 250, 20);
INSERT INTO A_PROMO_CANTIDADES (cantidadpagar, cantidadllevar, idpromocion) VALUES (120, 160, 21);
INSERT INTO A_PROMO_CANTIDADES (cantidadpagar, cantidadllevar, idpromocion) VALUES (70, 120, 22);
INSERT INTO A_PROMO_CANTIDADES (cantidadpagar, cantidadllevar, idpromocion) VALUES (90, 130, 23);
INSERT INTO A_PROMO_CANTIDADES (cantidadpagar, cantidadllevar, idpromocion) VALUES (140, 180, 24);
------------------------------------------------------------------------------------
--PROMO UNIDADES
INSERT INTO A_PROMO_UNIDADES (unidadespagar, unidadesllevar, idpromocion) VALUES (1, 2, 7);
INSERT INTO A_PROMO_UNIDADES (unidadespagar, unidadesllevar, idpromocion) VALUES (2, 3, 8);
INSERT INTO A_PROMO_UNIDADES (unidadespagar, unidadesllevar, idpromocion) VALUES (1, 2, 9);
INSERT INTO A_PROMO_UNIDADES (unidadespagar, unidadesllevar, idpromocion) VALUES (2, 3, 10);
INSERT INTO A_PROMO_UNIDADES (unidadespagar, unidadesllevar, idpromocion) VALUES (1, 2, 11);
INSERT INTO A_PROMO_UNIDADES (unidadespagar, unidadesllevar, idpromocion) VALUES (2, 3, 12);
------------------------------------------------------------------------------------
--PROMO DESCUENTO
INSERT INTO A_PROMO_DESCUENTO (descuento, idpromocion) VALUES (10, 1);
INSERT INTO A_PROMO_DESCUENTO (descuento, idpromocion) VALUES (15, 2);
INSERT INTO A_PROMO_DESCUENTO (descuento, idpromocion) VALUES (20, 3);
INSERT INTO A_PROMO_DESCUENTO (descuento, idpromocion) VALUES (25, 4);
INSERT INTO A_PROMO_DESCUENTO (descuento, idpromocion) VALUES (30, 5);
INSERT INTO A_PROMO_DESCUENTO (descuento, idpromocion) VALUES (35, 6);
------------------------------------------------------------------------------------
--PROMO UNIDAD DESCUENTO
INSERT INTO A_PROMO_UNIDADES_DESCUENTO (unidades, descuento, idpromocion) VALUES (1, 20, 13);
INSERT INTO A_PROMO_UNIDADES_DESCUENTO (unidades, descuento, idpromocion) VALUES (2, 30, 14);
INSERT INTO A_PROMO_UNIDADES_DESCUENTO (unidades, descuento, idpromocion) VALUES (1, 40, 15);
INSERT INTO A_PROMO_UNIDADES_DESCUENTO (unidades, descuento, idpromocion) VALUES (2, 50, 16);
INSERT INTO A_PROMO_UNIDADES_DESCUENTO (unidades, descuento, idpromocion) VALUES (1, 60, 17);
INSERT INTO A_PROMO_UNIDADES_DESCUENTO (unidades, descuento, idpromocion) VALUES (1, 70, 18);
------------------------------------------------------------------------------------
--PROMOCIONES
INSERT INTO A_PROMOCIONES (idpromocion, direccionsucursal, ciudad) VALUES (1, 'CR 139 139-99', 'Bucaramanga');
INSERT INTO A_PROMOCIONES (idpromocion, direccionsucursal, ciudad) VALUES (2, 'CR 13 13-9', 'Arauca');
INSERT INTO A_PROMOCIONES (idpromocion, direccionsucursal, ciudad) VALUES (3, 'CL 7 5-74', 'Bogotá');
INSERT INTO A_PROMOCIONES (idpromocion, direccionsucursal, ciudad) VALUES (4, 'CLL 34 43-65', 'Medellín');
INSERT INTO A_PROMOCIONES (idpromocion, direccionsucursal, ciudad) VALUES (5, 'CR 8 13-68', 'Cali');
INSERT INTO A_PROMOCIONES (idpromocion, direccionsucursal, ciudad) VALUES (6, 'CL 50 26A-8 SUR', 'Cartagena');
INSERT INTO A_PROMOCIONES (idpromocion, direccionsucursal, ciudad) VALUES (7, 'CR 139 139-99', 'Bucaramanga');
INSERT INTO A_PROMOCIONES (idpromocion, direccionsucursal, ciudad) VALUES (8, 'CR 13 13-9', 'Arauca');
INSERT INTO A_PROMOCIONES (idpromocion, direccionsucursal, ciudad) VALUES (9, 'CL 7 5-74', 'Bogotá');
INSERT INTO A_PROMOCIONES (idpromocion, direccionsucursal, ciudad) VALUES (10, 'CLL 34 43-65', 'Medellín');
INSERT INTO A_PROMOCIONES (idpromocion, direccionsucursal, ciudad) VALUES (11, 'CR 8 13-68', 'Cali');
INSERT INTO A_PROMOCIONES (idpromocion, direccionsucursal, ciudad) VALUES (12, 'CL 50 26A-8 SUR', 'Cartagena');
INSERT INTO A_PROMOCIONES (idpromocion, direccionsucursal, ciudad) VALUES (13, 'CR 139 139-99', 'Bucaramanga');
INSERT INTO A_PROMOCIONES (idpromocion, direccionsucursal, ciudad) VALUES (14, 'CR 13 13-9', 'Arauca');
INSERT INTO A_PROMOCIONES (idpromocion, direccionsucursal, ciudad) VALUES (15, 'CL 7 5-74', 'Bogotá');
INSERT INTO A_PROMOCIONES (idpromocion, direccionsucursal, ciudad) VALUES (16, 'CLL 34 43-65', 'Medellín');
INSERT INTO A_PROMOCIONES (idpromocion, direccionsucursal, ciudad) VALUES (17, 'CR 8 13-68', 'Cali');
INSERT INTO A_PROMOCIONES (idpromocion, direccionsucursal, ciudad) VALUES (18, 'CL 50 26A-8 SUR', 'Cartagena');
INSERT INTO A_PROMOCIONES (idpromocion, direccionsucursal, ciudad) VALUES (19, 'CR 139 139-99', 'Bucaramanga');
INSERT INTO A_PROMOCIONES (idpromocion, direccionsucursal, ciudad) VALUES (20, 'CR 13 13-9', 'Arauca');
INSERT INTO A_PROMOCIONES (idpromocion, direccionsucursal, ciudad) VALUES (21, 'CL 7 5-74', 'Bogotá');
INSERT INTO A_PROMOCIONES (idpromocion, direccionsucursal, ciudad) VALUES (22, 'CLL 34 43-65', 'Medellín');
INSERT INTO A_PROMOCIONES (idpromocion, direccionsucursal, ciudad) VALUES (23, 'CR 8 13-68', 'Cali');
INSERT INTO A_PROMOCIONES (idpromocion, direccionsucursal, ciudad) VALUES (24, 'CL 50 26A-8 SUR', 'Cartagena');

------------------------------------------------------------------------------------
--CARRITO
INSERT INTO A_CARRITO(idcarrito, direccionsucursal, ciudad, correocliente) VALUES (1, 'CR 139 139-99', 'Bucaramanga','empresa1@hotmail.com');
INSERT INTO A_CARRITO(idcarrito, direccionsucursal, ciudad, correocliente) VALUES (7, 'CR 139 139-99', 'Bucaramanga','empresa4@hotmail.com');
INSERT INTO A_CARRITO(idcarrito, direccionsucursal, ciudad, correocliente) VALUES (4, 'CLL 34 43-65', 'Medellín','lromero@gmail.com');
INSERT INTO A_CARRITO(idcarrito, direccionsucursal, ciudad, correocliente) VALUES (10, 'CLL 34 43-65', 'Medellín','socampo@gmail.com');
INSERT INTO A_CARRITO(idcarrito, direccionsucursal, ciudad, correocliente) VALUES (5, 'CL 7 5-74', 'Bogotá','empresa3@hotmail.com');
INSERT INTO A_CARRITO(idcarrito, direccionsucursal, ciudad, correocliente) VALUES (9, 'CL 7 5-74', 'Bogotá','empresa5@hotmail.com');
INSERT INTO A_CARRITO(idcarrito, direccionsucursal, ciudad, correocliente) VALUES (6, 'CR 13 13-9', 'Arauca','amalvarez@gmail.com');
INSERT INTO A_CARRITO(idcarrito, direccionsucursal, ciudad, correocliente) VALUES (8, 'CR 13 13-9', 'Arauca','nvargas@gmail.com');
INSERT INTO A_CARRITO(idcarrito, direccionsucursal, ciudad, correocliente) VALUES (2, 'CR 8 13-68', 'Cali', 'mocampo@gmail.com');
INSERT INTO A_CARRITO(idcarrito, direccionsucursal, ciudad, correocliente) VALUES (3, 'CL 50 26A-8 SUR', 'Cartagena','empresa2@hotmail.com');

------------------------------------------------------------------------------------
--PRODUCTOS CARRITO
INSERT INTO A_PRODUCTOS_CARRITO(idcarrito, codbarras, cantidad) VALUES (1,'AAAA',2);
INSERT INTO A_PRODUCTOS_CARRITO(idcarrito, codbarras, cantidad) VALUES (1,'BBBB',3);
INSERT INTO A_PRODUCTOS_CARRITO(idcarrito, codbarras, cantidad) VALUES (2,'CCCC',2);
INSERT INTO A_PRODUCTOS_CARRITO(idcarrito, codbarras, cantidad) VALUES (2,'DDDD',3);
INSERT INTO A_PRODUCTOS_CARRITO(idcarrito, codbarras, cantidad) VALUES (3,'EEEE',2);
INSERT INTO A_PRODUCTOS_CARRITO(idcarrito, codbarras, cantidad) VALUES (3,'FFFF',3);
INSERT INTO A_PRODUCTOS_CARRITO(idcarrito, codbarras, cantidad) VALUES (4,'0000',2);
INSERT INTO A_PRODUCTOS_CARRITO(idcarrito, codbarras, cantidad) VALUES (4,'GGGG',3);
INSERT INTO A_PRODUCTOS_CARRITO(idcarrito, codbarras, cantidad) VALUES (5,'0ABC',2);
INSERT INTO A_PRODUCTOS_CARRITO(idcarrito, codbarras, cantidad) VALUES (5,'EEEC',3);
INSERT INTO A_PRODUCTOS_CARRITO(idcarrito, codbarras, cantidad) VALUES (6,'EEEC',2);
INSERT INTO A_PRODUCTOS_CARRITO(idcarrito, codbarras, cantidad) VALUES (6,'0ABC',3);
INSERT INTO A_PRODUCTOS_CARRITO(idcarrito, codbarras, cantidad) VALUES (7,'GGGG',2);
INSERT INTO A_PRODUCTOS_CARRITO(idcarrito, codbarras, cantidad) VALUES (7,'0000',3);
INSERT INTO A_PRODUCTOS_CARRITO(idcarrito, codbarras, cantidad) VALUES (8,'FFFF',2);
INSERT INTO A_PRODUCTOS_CARRITO(idcarrito, codbarras, cantidad) VALUES (8,'EEEE',3);
INSERT INTO A_PRODUCTOS_CARRITO(idcarrito, codbarras, cantidad) VALUES (9,'DDDD',2);
INSERT INTO A_PRODUCTOS_CARRITO(idcarrito, codbarras, cantidad) VALUES (9,'CCCC',3);
INSERT INTO A_PRODUCTOS_CARRITO(idcarrito, codbarras, cantidad) VALUES (10,'BBBB',2);
INSERT INTO A_PRODUCTOS_CARRITO(idcarrito, codbarras, cantidad) VALUES (10,'AAAA',3);

COMMIT;