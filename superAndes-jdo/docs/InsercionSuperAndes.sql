--SUCURSAL
INSERT INTO a_sucursal (
    tamanio,
    direccionsucursal,
    ciudad,
    nombresucursal
) VALUES (
    10,
    'CR 139 139-99',
    'Bucaramanga',
    'A13'
);

INSERT INTO a_sucursal (
    tamanio,
    direccionsucursal,
    ciudad,
    nombresucursal
) VALUES (
    1,
    'CR 13 13-9',
    'Arauca',
    'F3'
);

INSERT INTO a_sucursal (
    tamanio,
    direccionsucursal,
    ciudad,
    nombresucursal
) VALUES (
    60,
    'CL 50 26A-8 SUR',
    'Cartagena',
    'N5'
);
--CLIENTE

INSERT INTO a_cliente (
    nombreconsumidor,
    correo,
    puntos
) VALUES (
    'Leidy Romero',
    'lj.romero@uniandes.edu.co',
    0
);

INSERT INTO a_cliente (
    nombreconsumidor,
    correo,
    puntos
) VALUES (
    'Maria Ocampo',
    'mj.ocampov@uniandes.edu.co',
    40
);

INSERT INTO a_cliente (
    nombreconsumidor,
    correo,
    puntos
) VALUES (
    'Pepito Perez',
    'p.perez@gmail.com',
    10
);

INSERT INTO a_cliente (
    nombreconsumidor,
    correo,
    puntos
) VALUES (
    'Andrea Rodriguez',
    'a.rodriguez@hotmail.com',
    34
);
--BODEGA

INSERT INTO a_bodega (
    tipobodega,
    volumenbodega,
    pesobodega,
    direccionbodega,
    direccionsucursal,
    ciudad
) VALUES (
    'Perecederos',
    4000,
    2600,
    'CL 26 #68-10',
    'CR 139 139-99',
    'Bucaramanga'
);

INSERT INTO a_bodega (
    tipobodega,
    volumenbodega,
    pesobodega,
    direccionbodega,
    direccionsucursal,
    ciudad
) VALUES (
    'Congelados',
    3500,
    3000,
    'DIAG 73 48-22',
    'CR 139 139-99',
    'Bucaramanga'
);

INSERT INTO a_bodega (
    tipobodega,
    volumenbodega,
    pesobodega,
    direccionbodega,
    direccionsucursal,
    ciudad
) VALUES (
    'No perecederos',
    10,
    15,
    'TRANS. 95C 21-03',
    'CL 50 26A-8 SUR',
    'Cartagena'
);

INSERT INTO a_bodega (
    tipobodega,
    volumenbodega,
    pesobodega,
    direccionbodega,
    direccionsucursal,
    ciudad
) VALUES (
    'Aseo',
    10000,
    6578,
    'CR 65 6-07',
    'CR 139 139-99',
    'Bucaramanga'
);
--PRODUCTO

INSERT INTO a_producto (
    nombreproducto,
    marca,
    preciounitario,
    presentacion,
    preciounimedida,
    cantpresentacion,
    unidadmedida,
    codbarras,
    pesoproducto,
    volumenproducto
) VALUES (
    'Papas de pollo',
    'Super ricas',
    1500,
    'Paquete 30 gr',
    10,
    25,
    'gr',
    'FFFF',
    '32 gr',
    '1000 mm^3'
);

INSERT INTO a_producto (
    nombreproducto,
    marca,
    preciounitario,
    presentacion,
    preciounimedida,
    cantpresentacion,
    unidadmedida,
    codbarras,
    pesoproducto,
    volumenproducto
) VALUES (
    'Manzana',
    'Productos del bosque',
    800,
    'Individual',
    50,
    1,
    'gr',
    'AAAA',
    'excelente',
    '20 gr',
    '10 cm^3'
);

INSERT INTO a_producto (
    nombreproducto,
    marca,
    preciounitario,
    presentacion,
    preciounimedida,
    cantpresentacion,
    unidadmedida,
    codbarras,
    calidad,
    fechavencimiento,
    pesoproducto,
    volumenproducto
) VALUES (
    'Salchichas',
    'Zenu',
    2700,
    'Paquete doble',
    100,
    2,
    'gr',
    '0000',
    'bueno',
    TO_DATE('2017-10-22','yyyy-MM-dd'),
    '300 gr',
    '50 mm^3'
);

INSERT INTO a_producto (
    nombreproducto,
    marca,
    preciounitario,
    presentacion,
    preciounimedida,
    cantpresentacion,
    unidadmedida,
    codbarras,
    calidad,
    fechavencimiento,
    pesoproducto,
    volumenproducto
) VALUES (
    'Detergente',
    'PyG',
    3000,
    'Paquete 300 gr',
    358,
    1,
    'gr',
    '0ABC',
    'bueno',
    TO_DATE('2017-10-22','yyyy-MM-dd'),
    '1200 gr',
    '1000 mm^3'
);

INSERT INTO a_producto (
    nombreproducto,
    marca,
    preciounitario,
    presentacion,
    preciounimedida,
    cantpresentacion,
    unidadmedida,
    codbarras,
    calidad,
    fechavencimiento,
    pesoproducto,
    volumenproducto
) VALUES (
    'Jabon',
    'PyG',
    2900,
    'Paquete 30 gr',
    20,
    2,
    'gr',
    'EEEC',
    'bueno',
    TO_DATE('2017-10-22','yyyy-MM-dd'),
    '78 gr',
    '12 mm^3'
);
--CANTIDAD EN BODEGA

INSERT INTO a_cantidad_en_bodega (
    codigobarras,
    direccionbodega,
    direccionsucursal,
    ciudad,
    cantidadactual,
    cantidadminima
) VALUES (
    'FFFF',
    'CL 26 #68-10',
    'CR 139 139-99',
    'Bucaramanga',
    2000,
    100
);

INSERT INTO a_cantidad_en_bodega (
    codigobarras,
    direccionbodega,
    direccionsucursal,
    ciudad,
    cantidadactual,
    cantidadminima
) VALUES (
    'AAAA',
    'CL 26 #68-10',
    'CR 139 139-99',
    'Bucaramanga',
    3000,
    250
);

INSERT INTO a_cantidad_en_bodega (
    codigobarras,
    direccionbodega,
    direccionsucursal,
    ciudad,
    cantidadactual,
    cantidadminima
) VALUES (
    '0000',
    'CR 65 6-07',
    'CR 139 139-99',
    'Bucaramanga',
    8500,
    30
);

INSERT INTO a_cantidad_en_bodega (
    codigobarras,
    direccionbodega,
    direccionsucursal,
    ciudad,
    cantidadactual,
    cantidadminima
) VALUES (
    '0ABC',
    'TRANS. 95C 21-03',
    'CL 50 26A-8 SUR',
    'Cartagena',
    4600,
    1000
);

INSERT INTO a_cantidad_en_bodega (
    codigobarras,
    direccionbodega,
    direccionsucursal,
    ciudad,
    cantidadactual,
    cantidadminima
) VALUES (
    'EEEC',
    'DIAG 73 48-22',
    'CR 139 139-99',
    'Bucaramanga',
    2000,
    100
);
--ESTANTE
INSERT INTO a_estante (
    tipoestante,
    volumenestante,
    idestante,
    pesoestante,
    nivelabastecimiento,
    direccionsucursal,
    ciudad
) VALUES (
    'Congelados',
    100,
    2,
    50,
    10,
    'CR 139 139-99',
    'Bucaramanga'
);

INSERT INTO a_estante (
    tipoestante,
    volumenestante,
    idestante,
    pesoestante,
    nivelabastecimiento,
    direccionsucursal,
    ciudad
) VALUES (
    'No perecederos',
    200,
    3,
    78,
    90,
    'CR 139 139-99',
    'Bucaramanga'
);