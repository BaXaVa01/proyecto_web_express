-- Datos iniciales

-- Categorías con IDs explícitos para asegurar consistencia
INSERT INTO categoria (id, nombre) VALUES
(1, 'Tecnología'),
(2, 'Ropa'),
(3, 'Hogar'),
(4, 'Accesorios');

-- Ajustar la secuencia de IDs de categoría para que el próximo INSERT automático no falle
ALTER TABLE categoria ALTER COLUMN id RESTART WITH 5;

-- Productos
INSERT INTO producto (nombre, descripcion, precio, imagen, stock, categoria_id) VALUES
('Mouse inalámbrico', 'Mouse ergonómico con conexión USB.', 12.99, 'mouse.jpg', 25, 1),
('Teclado mecánico', 'Teclado mecánico con iluminación LED.', 39.99, 'teclado.jpg', 15, 1),
('Camiseta básica', 'Camiseta de algodón disponible en varias tallas.', 9.50, 'camiseta.jpg', 40, 2),
('Taza personalizada', 'Taza de cerámica ideal para regalos.', 6.75, 'taza.jpg', 30, 3),
('Mochila urbana', 'Mochila resistente para uso diario.', 24.99, 'mochila.jpg', 20, 4);

-- Pedidos
INSERT INTO pedido (id, nombre_cliente, correo, comentario, total, fecha_pedido)
VALUES (1, 'Cliente de prueba', 'cliente@correo.com', 'Pedido generado como dato inicial.', 52.98, CURRENT_TIMESTAMP);

ALTER TABLE pedido ALTER COLUMN id RESTART WITH 2;

-- Detalle de Pedidos
INSERT INTO detalle_pedido (pedido_id, producto_id, cantidad, precio_unitario, subtotal) VALUES
(1, 1, 1, 12.99, 12.99),
(1, 2, 1, 39.99, 39.99);
