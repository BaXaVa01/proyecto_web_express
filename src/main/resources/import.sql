-- Datos iniciales para Tienda Web Express

-- Categorías
INSERT INTO categoria (nombre) VALUES ('Tecnología') ON CONFLICT DO NOTHING;
INSERT INTO categoria (nombre) VALUES ('Ropa') ON CONFLICT DO NOTHING;
INSERT INTO categoria (nombre) VALUES ('Hogar') ON CONFLICT DO NOTHING;
INSERT INTO categoria (nombre) VALUES ('Accesorios') ON CONFLICT DO NOTHING;

-- Productos
-- Tecnología
INSERT INTO producto (nombre, descripcion, precio, imagen, stock, categoria_id)
SELECT 'Mouse inalámbrico', 'Mouse ergonómico con conexión USB.', 12.99, 'mouse.jpg', 25, id
FROM categoria WHERE nombre = 'Tecnología'
ON CONFLICT DO NOTHING;

INSERT INTO producto (nombre, descripcion, precio, imagen, stock, categoria_id)
SELECT 'Teclado mecánico', 'Teclado mecánico con iluminación LED.', 39.99, 'teclado.jpg', 15, id
FROM categoria WHERE nombre = 'Tecnología'
ON CONFLICT DO NOTHING;

-- Ropa
INSERT INTO producto (nombre, descripcion, precio, imagen, stock, categoria_id)
SELECT 'Camiseta básica', 'Camiseta de algodón disponible en varias tallas.', 9.50, 'camiseta.jpg', 40, id
FROM categoria WHERE nombre = 'Ropa'
ON CONFLICT DO NOTHING;

-- Hogar
INSERT INTO producto (nombre, descripcion, precio, imagen, stock, categoria_id)
SELECT 'Taza personalizada', 'Taza de cerámica ideal para regalos.', 6.75, 'taza.jpg', 30, id
FROM categoria WHERE nombre = 'Hogar'
ON CONFLICT DO NOTHING;

-- Accesorios
INSERT INTO producto (nombre, descripcion, precio, imagen, stock, categoria_id)
SELECT 'Mochila urbana', 'Mochila resistente para uso diario.', 24.99, 'mochila.jpg', 20, id
FROM categoria WHERE nombre = 'Accesorios'
ON CONFLICT DO NOTHING;
