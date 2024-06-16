use ferremas;
INSERT INTO tp_usuario (tipo) VALUES 
('Administrador'),
('Vendedor'),
('Cliente');

-- Insertar usuarios
INSERT INTO usuario (apmaterno, appaterno, gmail, password, pnombre, snombre, tipo_usuario_id) VALUES
('González', 'Pérez', 'gonzalez@example.com', 'contrasena', 'Juan', 'Carlos', 1),
('Rodríguez', 'Martínez', 'rodriguez@example.com', 'contrasena', 'María', 'Fernanda', 2),
('López', 'García', 'lopez@example.com', 'contrasena', 'Pedro', 'Antonio', 3);

-- Insertar tipos de producto
INSERT INTO tipo_producto (tipo) VALUES 
('Herramienta'),
('Electrónico'),
('Mueble');

-- Insertar herramientas
INSERT INTO herramienta (tipo_herramienta, id_tipo_producto) VALUES 
('Martillo', 1),
('Destornillador', 1),
('Sierra', 1);

-- Insertar marcas de producto

INSERT INTO marca_producto (direccion, gmail, nombre) VALUES 
('Calle Falsa 123', 'info@marcamuebles.com', 'Muebles de Lujo'),
('Avenida Principal 456', 'info@marcamartillos.com', 'Herramientas Profesionales');

-- Insertar productos
INSERT INTO producto (descripcion, nombre, precio, stock, id_herramienta, id_marca) VALUES 
('Martillo de acero forjado', 'Martillo de carpintero', 20.99, 100, 1, 2),
('Destornillador de punta Phillips', 'Destornillador Phillips', 12.50, 150, 2, 2),
('Sierra de mano para madera', 'Sierra de carpintero', 35.75, 75, 3, 2);

-- Insertar estados de venta
INSERT INTO estado_venta (tipo) VALUES 
('Pendiente'),
('En proceso'),
('Completado');

-- Insertar ventas
INSERT INTO venta (estado, fecha_venta, precio_total, id_estado_venta, id_usuario) VALUES 
('Completado', '2024-05-06 10:00:00', 68.24, 3, 1),
('Pendiente', '2024-05-05 14:30:00', 40.25, 1, 2),
('En proceso', '2024-05-04 11:45:00', 105.80, 2, 3);

-- Insertar detalles de ventas
INSERT INTO venta_detalle (cant_producto, precio_detalle, id_producto, id_venta) VALUES 
('1', 20.99, 1, 1),
('2', 25.00, 2, 2),
('3', 35.60, 3, 3);
