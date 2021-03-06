--- Sentencias SQL para la creación del esquema de superAndes
--- Las tablas tienen prefijo A_ para facilitar su acceso desde SQL Developer
-- USO
-- Copie el contenido de este archivo en una pestaña SQL de SQL Developer
-- Ejecútelo como un script - Utilice el botón correspondiente de la pestaña
-- utilizada
-- Creación del secuenciador
CREATE sequence pedidos_sequence;
CREATE sequence promocion_sequence;
CREATE sequence factura_sequence;
CREATE sequence carrito_sequence;

  -- Creación de la tabla CATEGORIA y especificación de sus restricciones
 CREATE
  TABLE A_CATEGORIA
  (
    NOMBRECATEGORIA VARCHAR(100),
    CONSTRAINT A_CATEGORIA_PK PRIMARY KEY(NOMBRECATEGORIA)
  );

  -- Creaación de la tabla PRODUCTO y especificación de sus restricciones
  CREATE
    TABLE A_PRODUCTO
    (
      NOMBREPRODUCTO   VARCHAR(255) NOT NULL,
      MARCA             VARCHAR(255) NOT NULL,
      PRECIOUNITARIO   NUMBER NOT NULL,
      PRESENTACION      VARCHAR(50) NOT NULL,
      PRECIOUNIMEDIDA NUMBER NOT NULL,
      CANTPRESENTACION NUMBER NOT NULL,
      UNIDADMEDIDA     VARCHAR(50) NOT NULL,
      VOLUMENPRODUCTO  VARCHAR(255) NOT NULL,
      PESOPRODUCTO     VARCHAR(255) NOT NULL,
      CODBARRAS        VARCHAR(255) NOT NULL,
      CALIDAD           VARCHAR(255),
      FECHAVENCIMIENTO DATE,
      NOMBRECATEGORIA        VARCHAR(100) NOT NULL,
      CONSTRAINT A_PRODUCTO_PK PRIMARY KEY(CODBARRAS)
    );
  ALTER TABLE A_PRODUCTO
    ADD CONSTRAINT FK_NC_PRODUCTO
      FOREIGN KEY (NOMBRECATEGORIA)
      REFERENCES A_CATEGORIA(NOMBRECATEGORIA)
  ENABLE;
  ALTER TABLE A_PRODUCTO 
    ADD CONSTRAINT CK_PRODUCTO_PU 
    CHECK (PRECIOUNITARIO > 0) 
  ENABLE;
  ALTER TABLE A_PRODUCTO 
    ADD CONSTRAINT CK_PRODUCTO_PUM 
    CHECK (PRECIOUNIMEDIDA > 0)
  ENABLE;
  ALTER TABLE A_PRODUCTO 
    ADD CONSTRAINT CK_PRODUCTO_CP 
    CHECK (CANTPRESENTACION > 0) 
  ENABLE;
    
  -- Creación de la tabla SUCURSAL y especificación de sus restricciones
  CREATE
    TABLE A_SUCURSAL
    (
      TAMANIO NUMBER NOT NULL,
      DIRECCIONSUCURSAL VARCHAR(255),
      CIUDAD VARCHAR(255),
      NOMBRESUCURSAL VARCHAR(255) NOT NULL,
      CONSTRAINT A_SUCURSAL_PK PRIMARY KEY(DIRECCIONSUCURSAL, CIUDAD)
    );
    
  -- Creación de la tabla CLIENTE y especificación de sus restricciones    
  CREATE
    TABLE A_CLIENTE
    (
      NOMBRECONSUMIDOR VARCHAR(255) NOT NULL,
      CORREO VARCHAR(255),
      PUNTOS NUMBER,
      CONSTRAINT A_CLIENTE_PK PRIMARY KEY(CORREO)
    );
  ALTER TABLE A_CLIENTE
    ADD CONSTRAINT CK_CLIENTE_PT
    CHECK(PUNTOS >= 0)
  ENABLE;
  -- Creación de la tabla PERSONA NATURAL y especificación de sus restricciones 
  CREATE
    TABLE A_PERSONA_NATURAL
    (
      CORREO VARCHAR(255),
      DOCUMENTO VARCHAR(255) NOT NULL,
      CONSTRAINT A_PERSONA_NATURAL_PK PRIMARY KEY (CORREO)
    );
  ALTER TABLE A_PERSONA_NATURAL
    ADD CONSTRAINT FK_PN_CLIENTE
      FOREIGN KEY (CORREO)
      REFERENCES A_CLIENTE(CORREO)
  ENABLE;

  -- Creación de la tabla EMPRESA y especificación de sus restricciones
  CREATE
    TABLE A_EMPRESA
    (
      CORREO VARCHAR(255),
      NITEMPRESA NUMBER,
      DIRECCION VARCHAR(255) NOT NULL,
      CONSTRAINT A_EMPRESA_PK PRIMARY KEY (CORREO)
    );
  ALTER TABLE A_EMPRESA
    ADD CONSTRAINT FK_EM_CLIENTE
      FOREIGN KEY (CORREO)
      REFERENCES A_CLIENTE(CORREO)
  ENABLE;
  
  -- Creación de la tabla PROVEEDOR y especificación de sus restricciones
  CREATE
    TABLE A_PROVEEDOR
    (
      NITPROVEEDOR NUMBER,
      NOMBREPROVEEDOR VARCHAR(255) NOT NULL,
      CALIFICACIONPROVEEDOR VARCHAR(255),
      CONSTRAINT A_PROVEEDOR_PK PRIMARY KEY(NITPROVEEDOR)
    );
  
  -- Creación de la tabla TIPO PRODUCTO y especificación de sus restricciones
  CREATE
    TABLE A_TIPO_PRODUCTO
    (
      NOMBRETIPO VARCHAR(255),
      NOMBRECATEGORIA VARCHAR(255),
      CONSTRAINT A_TIPO_PRODUCTO_PK PRIMARY KEY(NOMBRETIPO)
    );
  ALTER TABLE A_TIPO_PRODUCTO
    ADD CONSTRAINT FK_TP_CATEGORIA
      FOREIGN KEY (NOMBRECATEGORIA)
      REFERENCES A_CATEGORIA (NOMBRECATEGORIA)
  ENABLE;
  
  -- Creación de la tabla FACTURA y especificaión de sus restricciones
  CREATE
    TABLE A_FACTURA
    (
      IDFACTURA NUMBER,
      FECHA DATE NOT NULL,
      COSTOTOTAL NUMBER NOT NULL,
      CORREOCLIENTE VARCHAR(255),
      DIRECCIONSUCURSAL VARCHAR(255),
      CIUDAD VARCHAR(255),
      CONSTRAINT A_FACTURA_PK PRIMARY KEY(IDFACTURA)
    );
  ALTER TABLE A_FACTURA
    ADD CONSTRAINT CK_A_FACTURA
    CHECK (COSTOTOTAL >= 0)
  ENABLE;
  ALTER TABLE A_FACTURA
    ADD CONSTRAINT FK_CC_FACTURA
      FOREIGN KEY (CORREOCLIENTE)
      REFERENCES A_CLIENTE (CORREO)
  ENABLE;
 ALTER TABLE A_FACTURA
    ADD CONSTRAINT FK_S_FACTURA
    FOREIGN KEY (DIRECCIONSUCURSAL, CIUDAD)
    REFERENCES A_SUCURSAL (DIRECCIONSUCURSAL, CIUDAD)
 ENABLE;
  -- Creación de la tabla PROVEEN y especificación de sus restricciones
  CREATE
    TABLE A_PROVEEN
    (
      NITPROVEEDOR NUMBER,
      CODBARRAS VARCHAR(255),
      CONSTRAINT A_PROVEEN_PK PRIMARY KEY(NITPROVEEDOR, CODBARRAS)
    );
  ALTER TABLE A_PROVEEN
    ADD CONSTRAINT FK_NP_PROVEEN
      FOREIGN KEY (NITPROVEEDOR)
      REFERENCES A_PROVEEDOR (NITPROVEEDOR)
  ENABLE;
  ALTER TABLE A_PROVEEN
    ADD CONSTRAINT FK_CB_PROVEEN
      FOREIGN KEY (CODBARRAS)
      REFERENCES A_PRODUCTO (CODBARRAS)
  ENABLE;
    
  -- Creacion de la tabla ESTANTE y especificacion de sus restricciones
  CREATE
  TABLE A_ESTANTE
  (
       TIPOESTANTE VARCHAR(255) NOT NULL,
       VOLUMENESTANTE NUMBER NOT NULL,
       IDESTANTE NUMBER,
       PESOESTANTE NUMBER NOT NULL,
       NIVELABASTECIMIENTO NUMBER NOT NULL,
       DIRECCIONSUCURSAL VARCHAR(255),
       CIUDAD VARCHAR(255),
       CONSTRAINT A_ESTANTE_PK PRIMARY KEY (IDESTANTE)
  );
  ALTER TABLE A_ESTANTE
    ADD CONSTRAINT FK_DS_ESTANTE
      FOREIGN KEY (DIRECCIONSUCURSAL, CIUDAD)
      REFERENCES A_SUCURSAL (DIRECCIONSUCURSAL, CIUDAD)
  ENABLE;
  ALTER TABLE A_ESTANTE
   ADD CONSTRAINT CK_ESTANTE_VE
   CHECK (VOLUMENESTANTE > 0) 
  ENABLE;
  ALTER TABLE A_ESTANTE
   ADD CONSTRAINT CK_ESTANTE_PE
   CHECK (PESOESTANTE > 0) 
  ENABLE;
 
 -- Creación de la tabla PROMOCIÓN y especificación de sus restricciones
 CREATE
    TABLE A_PROMOCION
    (
        FECHAINICIAL DATE NOT NULL,
        FECHAFINAL DATE,
        DESCRIPCION VARCHAR(255) NOT NULL,
        IDPROMOCION NUMBER,
        UNIDADESDISPONIBLES NUMBER NOT NULL,
        UNIDADESVENDIDAS NUMBER,
        CODIGOBARRAS VARCHAR(255) NOT NULL,
        ESTADO VARCHAR(255) NOT NULL,
        CONSTRAINT A_PROMOCION_PK PRIMARY KEY (IDPROMOCION)
    );
 ALTER TABLE A_PROMOCION
    ADD CONSTRAINT FK_CD_PROMOCION
    FOREIGN KEY (CODIGOBARRAS)
    REFERENCES A_PRODUCTO (CODBARRAS)
 ENABLE;
 ALTER TABLE A_PROMOCION
    ADD CONSTRAINT CK_UD_PROMOCION
    CHECK (UNIDADESDISPONIBLES >= 0)
 ENABLE;
  ALTER TABLE A_PROMOCION
    ADD CONSTRAINT CK_UV_PROMOCION
    CHECK (UNIDADESVENDIDAS >= 0)
 ENABLE;
  ALTER TABLE A_PROMOCION
    ADD CONSTRAINT CK_E_PROMOCION
    CHECK (ESTADO IN ('VIGENTE', 'FINALIZADA'))
 ENABLE;    
 ALTER TABLE A_PROMOCION
    ADD CONSTRAINT CK_F_PROMOCION
    CHECK (FECHAFINAL>=FECHAINICIAL)
 ENABLE;
 
 -- Creación de la tabla PROMOCIONES y especificación de sus restricciones
 CREATE
    TABLE A_PROMOCIONES
    (
        IDPROMOCION NUMBER,
        DIRECCIONSUCURSAL VARCHAR(255),
        CIUDAD VARCHAR(255),
        CONSTRAINT A_PROMOCIONES_PK PRIMARY KEY (IDPROMOCION, DIRECCIONSUCURSAL, CIUDAD)
    );
 ALTER TABLE A_PROMOCIONES
    ADD CONSTRAINT FK_S_PROMOCIONES
    FOREIGN KEY (DIRECCIONSUCURSAL, CIUDAD)
    REFERENCES A_SUCURSAL (DIRECCIONSUCURSAL, CIUDAD)
 ENABLE;
 ALTER TABLE A_PROMOCIONES
    ADD CONSTRAINT FK_IP_PROMOCIONES
    FOREIGN KEY (IDPROMOCION)
    REFERENCES A_PROMOCION (IDPROMOCION)
 ENABLE;
 
  -- Creacion de la tabla PROMO UNIDADES y especificacion de sus restricciones
  CREATE
  TABLE A_PROMO_UNIDADES
  (
    UNIDADESPAGAR NUMBER NOT NULL,
    UNIDADESLLEVAR NUMBER NOT NULL,
    IDPROMOCION NUMBER,
    CONSTRAINT A_PROMO_UNIDADES_PK PRIMARY KEY (IDPROMOCION)     
  );
  ALTER TABLE A_PROMO_UNIDADES
    ADD CONSTRAINT FK_IP_PROMO_UNIDADES
    FOREIGN KEY (IDPROMOCION)
    REFERENCES A_PROMOCION (IDPROMOCION)
  ENABLE;
  ALTER TABLE A_PROMO_UNIDADES
    ADD CONSTRAINT CK_UP_PROMO_UNIDADES
    CHECK (UNIDADESPAGAR > 0)
  ENABLE;
  ALTER TABLE A_PROMO_UNIDADES
    ADD CONSTRAINT CK_UL_PROMO_UNIDADES
    CHECK (UNIDADESLLEVAR > UNIDADESPAGAR)
  ENABLE;
  
  -- Creacion de la tabla PROMO UNIDADES y especificacion de sus restricciones
  CREATE
  TABLE A_PROMO_CANTIDADES
  (
    CANTIDADPAGAR NUMBER NOT NULL,
    CANTIDADLLEVAR NUMBER NOT NULL,
    IDPROMOCION NUMBER,
    CONSTRAINT A_PROMO_CANTIDADES_PK PRIMARY KEY (IDPROMOCION)     
  );
  ALTER TABLE A_PROMO_CANTIDADES
    ADD CONSTRAINT FK_IP_PROMO_CANTIDADES
    FOREIGN KEY (IDPROMOCION)
    REFERENCES A_PROMOCION (IDPROMOCION)
  ENABLE;
  ALTER TABLE A_PROMO_CANTIDADES
    ADD CONSTRAINT CK_CP_PROMO_CANTIDADES
    CHECK (CANTIDADPAGAR > 0)
  ENABLE;
  ALTER TABLE A_PROMO_CANTIDADES
    ADD CONSTRAINT CK_CL_PROMO_CANTIDADES
    CHECK (CANTIDADLLEVAR > CANTIDADPAGAR)
  ENABLE;
  
  -- Creacion de la tabla PROMO UNIDADES y especificacion de sus restricciones
  CREATE
  TABLE A_PROMO_UNIDADES_DESCUENTO
  (
    UNIDADES NUMBER NOT NULL,
    DESCUENTO NUMBER NOT NULL,
    IDPROMOCION NUMBER,
    CONSTRAINT A_PROMO_UNIDADES_DESCUENTO_PK PRIMARY KEY (IDPROMOCION)     
  );
  ALTER TABLE A_PROMO_UNIDADES_DESCUENTO
    ADD CONSTRAINT FK_IP_PROMO_UNIDADES_DESCUENTO
    FOREIGN KEY (IDPROMOCION)
    REFERENCES A_PROMOCION (IDPROMOCION)
  ENABLE;
  ALTER TABLE A_PROMO_UNIDADES_DESCUENTO
    ADD CONSTRAINT CK_U_PROMO_UNIDADES_DESCUENTO
    CHECK (UNIDADES > 0)
  ENABLE;
  ALTER TABLE A_PROMO_UNIDADES_DESCUENTO
    ADD CONSTRAINT CK_D_PROMO_UNIDADES_DESCUENTO
    CHECK (DESCUENTO > 0)
  ENABLE;
  
  -- Creacion de la tabla PROMO UNIDADES y especificacion de sus restricciones
  CREATE
  TABLE A_PROMO_DESCUENTO
  (
    DESCUENTO NUMBER NOT NULL,
    IDPROMOCION NUMBER,
    CONSTRAINT A_PROMO_DESCUENTO_PK PRIMARY KEY (IDPROMOCION)     
  );
  ALTER TABLE A_PROMO_DESCUENTO
    ADD CONSTRAINT FK_IP_PROMO_DESCUENTO
    FOREIGN KEY (IDPROMOCION)
    REFERENCES A_PROMOCION (IDPROMOCION)
  ENABLE;
  ALTER TABLE A_PROMO_DESCUENTO
    ADD CONSTRAINT CK_D_PROMO_DESCUENTO
    CHECK (DESCUENTO > 0)
  ENABLE;

-- Creacion de la tabla BODEGA y especificacion de sus restricciones
 CREATE
  TABLE A_BODEGA
  (
      TIPOBODEGA VARCHAR(255)NOT NULL UNIQUE,
      VOLUMENBODEGA NUMBER NOT NULL,
      PESOBODEGA NUMBER NOT NULL,
      DIRECCIONBODEGA VARCHAR(255),
      DIRECCIONSUCURSAL VARCHAR(255),
      CIUDAD VARCHAR(255),
      CONSTRAINT A_BODEGA_PK PRIMARY KEY(DIRECCIONBODEGA, DIRECCIONSUCURSAL, CIUDAD)
  );
  ALTER TABLE A_BODEGA
    ADD CONSTRAINT FK_DS_BODEGA
    FOREIGN KEY (DIRECCIONSUCURSAL, CIUDAD)
    REFERENCES A_SUCURSAL (DIRECCIONSUCURSAL, CIUDAD)
  ENABLE;
  
-- Creacion de la tabla COMPRADOS y especificacion de sus restricciones
CREATE
  TABLE A_COMPRADOS
  (
      CODIGOBARRAS VARCHAR(255),
      IDFACTURA NUMBER,
      CANTIDAD NUMBER NOT NULL,
      PRECIOTOTAL NUMBER NOT NULL,
      CONSTRAINT A_COMPRADOS_PK PRIMARY KEY(CODIGOBARRAS, IDFACTURA)
  );  
  ALTER TABLE A_COMPRADOS
    ADD CONSTRAINT FK_CB_COMPRADOS
    FOREIGN KEY (CODIGOBARRAS)
    REFERENCES A_PRODUCTO (CODBARRAS)
  ENABLE;
  ALTER TABLE A_COMPRADOS
    ADD CONSTRAINT FK_IDF_COMPRADOS
    FOREIGN KEY (IDFACTURA)
    REFERENCES A_FACTURA (IDFACTURA)
  ENABLE;
  ALTER TABLE A_COMPRADOS
    ADD CONSTRAINT CK_COMPRADOS_C
    CHECK (CANTIDAD > 0) 
  ENABLE;
  ALTER TABLE A_COMPRADOS
    ADD CONSTRAINT CK_COMPRADOS_PT
    CHECK (PRECIOTOTAL > 0) 
  ENABLE;
-- Creacion de la tabla CANTIDAD_EN_BODEGA y especificacion de sus restricciones
CREATE
  TABLE A_CANTIDAD_EN_BODEGA
  (
      CODIGOBARRAS VARCHAR(255),
      DIRECCIONBODEGA VARCHAR(255),
      DIRECCIONSUCURSAL VARCHAR(255),
      CIUDAD VARCHAR(255),
      CANTIDADACTUAL NUMBER NOT NULL,
      CANTIDADMINIMA NUMBER NOT NULL,
      CONSTRAINT A_CANTIDAD_EN_BODEGA_PK PRIMARY KEY(CODIGOBARRAS, DIRECCIONBODEGA, DIRECCIONSUCURSAL, CIUDAD)
  );
  ALTER TABLE A_CANTIDAD_EN_BODEGA
    ADD CONSTRAINT FK_CB_CANTIDAD_EN_BODEGA
    FOREIGN KEY (CODIGOBARRAS)
    REFERENCES A_PRODUCTO (CODBARRAS)
  ENABLE;
  ALTER TABLE A_CANTIDAD_EN_BODEGA
    ADD CONSTRAINT FK_DB_CANTIDAD_EN_BODEGA
    FOREIGN KEY (DIRECCIONBODEGA, DIRECCIONSUCURSAL, CIUDAD)
    REFERENCES A_BODEGA (DIRECCIONBODEGA, DIRECCIONSUCURSAL, CIUDAD)
  ENABLE;
  ALTER TABLE A_CANTIDAD_EN_BODEGA
    ADD CONSTRAINT CK_CANTIDAD_EN_BODEGA_CA
    CHECK (CANTIDADACTUAL > 0) 
  ENABLE;
  ALTER TABLE A_CANTIDAD_EN_BODEGA
    ADD CONSTRAINT CK_CANTIDAD_EN_BODEGA_CM
    CHECK (CANTIDADMINIMA > 0) 
  ENABLE;
  
  -- Creación de la tabla CANTIDAD EN ESTANTES y especificación de sus restricciones
  CREATE
    TABLE A_CANTIDAD_EN_ESTANTES
    (
    CODIGOBARRAS VARCHAR(255),
    IDESTANTE NUMBER,
    CANTIDADACTUAL NUMBER NOT NULL,
    CANTIDADMINIMA NUMBER NOT NULL,
    CONSTRAINT A_CANTIDAD_EN_ESTANTES_PK PRIMARY KEY (CODIGOBARRAS, IDESTANTE)
    );
 ALTER TABLE A_CANTIDAD_EN_ESTANTES
    ADD CONSTRAINT FK_CD_PRODUCTO
      FOREIGN KEY (CODIGOBARRAS)
      REFERENCES A_PRODUCTO(CODBARRAS)
  ENABLE;
 ALTER TABLE A_CANTIDAD_EN_ESTANTES
    ADD CONSTRAINT FK_IE_PRODUCTO
      FOREIGN KEY (IDESTANTE)
      REFERENCES A_ESTANTE(IDESTANTE)
  ENABLE; 
 ALTER TABLE A_CANTIDAD_EN_ESTANTES
    ADD CONSTRAINT CK_CA_CANTIDAD_EN_ESTANTES
    CHECK (CANTIDADACTUAL > 0)
  ENABLE;
  ALTER TABLE A_CANTIDAD_EN_ESTANTES
    ADD CONSTRAINT CK_CM_CANTIDAD_EN_ESTANTES
    CHECK (CANTIDADMINIMA > 0)
  ENABLE;
  
  -- Creación de la tabla ORDEN PEDIDO y especificación de sus restricciones
 CREATE
    TABLE A_ORDEN_PEDIDO
    (
        FECHAESPERADAENTREGA DATE NOT NULL,
        ESTADO VARCHAR(255) NOT NULL,
        FECHAENTREGA DATE,
        CALIFICACIONPEDIDO VARCHAR(255),
        NITPROVEEDOR NUMBER,
        IDPEDIDO NUMBER,
        CIUDAD VARCHAR (255),
        DIRECCIONSUCURSAL VARCHAR(255),
        DIRECCIONBODEGA VARCHAR(255),
        CONSTRAINT A_ORDEN_PEDIDO_PK PRIMARY KEY (IDPEDIDO)
    );
 ALTER TABLE A_ORDEN_PEDIDO
    ADD CONSTRAINT FK_NP_ORDEN_PEDIDO
    FOREIGN KEY (NITPROVEEDOR)
    REFERENCES A_PROVEEDOR (NITPROVEEDOR)
 ENABLE;
 ALTER TABLE A_ORDEN_PEDIDO
    ADD CONSTRAINT FK_B_ORDEN_PEDIDO
    FOREIGN KEY (CIUDAD, DIRECCIONSUCURSAL, DIRECCIONBODEGA)
    REFERENCES A_BODEGA (CIUDAD, DIRECCIONSUCURSAL, DIRECCIONBODEGA)
 ENABLE;
 ALTER TABLE A_ORDEN_PEDIDO
    ADD CONSTRAINT CK_E_ORDEN_PEDIDO
    CHECK (ESTADO IN('Recibido','En espera','Cancelado'))
 ENABLE;
 
 -- Creacion de la tabla PEDIDO PRODUCTO y especificacion de sus restricciones
  CREATE
  TABLE A_PEDIDO_PRODUCTO
  (
      CODIGOBARRAS VARCHAR(255),
      IDPEDIDO NUMBER,
      CANTIDADPRODUCTO NUMBER,
      PRECIOPRODUCTO NUMBER,
      CONSTRAINT A_PEDIDO_PRODUCTO_PK PRIMARY KEY (IDPEDIDO)
  );
  ALTER TABLE A_PEDIDO_PRODUCTO
    ADD CONSTRAINT FK_CB_PEDIDO_PRODUCTO
    FOREIGN KEY (CODIGOBARRAS)
    REFERENCES A_PRODUCTO (CODBARRAS)
  ENABLE;
  ALTER TABLE A_PEDIDO_PRODUCTO
    ADD CONSTRAINT CK_PEDIDO_PRODUCTO_CP
    CHECK (CANTIDADPRODUCTO > 0) 
  ENABLE;
  ALTER TABLE A_PEDIDO_PRODUCTO
    ADD CONSTRAINT CK_PEDIDO_PRODUCTO_PP
    CHECK (PRECIOPRODUCTO > 0) 
  ENABLE;
  ALTER TABLE A_PEDIDO_PRODUCTO
    ADD CONSTRAINT FK_IP_PEDIDO_PRODUCTO
    FOREIGN KEY (IDPEDIDO)
    REFERENCES A_ORDEN_PEDIDO (IDPEDIDO)
  ENABLE;
  
 -- Creación de la tabla PRODUCTOS OFRECIDOS y especificación de sus restricciones
 CREATE
    TABLE A_PRODUCTOS_OFRECIDOS
    (
        DIRECCIONSUCURSAL VARCHAR(255),
        CIUDAD VARCHAR(255),
        CODIGOBARRAS VARCHAR(255),
        NIVELREORDEN NUMBER,
        CONSTRAINT A_PRODUCTOS_OFRECIDOS_PK PRIMARY KEY(DIRECCIONSUCURSAL, CIUDAD, CODIGOBARRAS)
    );
  ALTER TABLE A_PRODUCTOS_OFRECIDOS
    ADD CONSTRAINT FK_S_PRODUCTOS_OFRECIDOS
    FOREIGN KEY (DIRECCIONSUCURSAL, CIUDAD)
    REFERENCES A_SUCURSAL(DIRECCIONSUCURSAL, CIUDAD)
 ENABLE;
 ALTER TABLE A_PRODUCTOS_OFRECIDOS
    ADD CONSTRAINT FK_CD_PRODUCTOS_OFRECIDOS
    FOREIGN KEY (CODIGOBARRAS)
    REFERENCES A_PRODUCTO(CODBARRAS)
 ENABLE;
 ALTER TABLE A_PRODUCTOS_OFRECIDOS
    ADD CONSTRAINT CK_NR_PRODUCTOS_OFRECIDOS
    CHECK (NIVELREORDEN > 0)
 ENABLE;
 
--Creación de la tabla CARRITO y especificacion de sus restricciones
CREATE TABLE A_CARRITO
    (
        IDCARRITO NUMBER,
        DIRECCIONSUCURSAL VARCHAR(255),
        CIUDAD VARCHAR(255),
        CORREOCLIENTE VARCHAR(255),
        CONSTRAINT A_CARRITO_PK PRIMARY KEY(IDCARRITO)
    );
ALTER TABLE A_CARRITO
    ADD CONSTRAINT FK_DS_CARRITO
    FOREIGN KEY (DIRECCIONSUCURSAL, CIUDAD)
    REFERENCES A_SUCURSAL (DIRECCIONSUCURSAL, CIUDAD)
ENABLE;
  ALTER TABLE A_CARRITO
    ADD CONSTRAINT FK_CA_CLIENTE
      FOREIGN KEY (CORREOCLIENTE)
      REFERENCES A_CLIENTE(CORREO)
  ENABLE;
  
--Creación de la tabla PRODUCTOS CARRITO y especificacion de sus restricciones   
CREATE TABLE A_PRODUCTOS_CARRITO
    (
        CANTIDAD NUMBER NOT NULL,
        CODBARRAS VARCHAR(255),
        IDCARRITO NUMBER,
        CONSTRAINT A_PRODUCTOS_CARRITO_PK PRIMARY KEY(IDCARRITO, CODBARRAS)
    );
 ALTER TABLE A_PRODUCTOS_CARRITO
    ADD CONSTRAINT FK_P_CA_CODIGO
      FOREIGN KEY (CODBARRAS)
      REFERENCES A_PRODUCTO(CODBARRAS)
  ENABLE;
   ALTER TABLE A_PRODUCTOS_CARRITO
    ADD CONSTRAINT FK_P_CA_ID
      FOREIGN KEY (IDCARRITO)
      REFERENCES A_CARRITO(IDCARRITO)
  ENABLE;
   ALTER TABLE A_PRODUCTOS_CARRITO
    ADD CONSTRAINT CK_P_CA_CANTIDAD
    CHECK (CANTIDAD >= 0) 
  ENABLE;
COMMIT;
