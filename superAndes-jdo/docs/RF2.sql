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
