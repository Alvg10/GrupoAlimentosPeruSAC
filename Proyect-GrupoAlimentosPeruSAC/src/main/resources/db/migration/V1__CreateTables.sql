CREATE TABLE IF NOT EXISTS categoria (
    id_categoria SERIAL PRIMARY KEY,
    nombre_categoria VARCHAR(100) NOT NULL,
    descripcion_categoria VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS tienda (
    id_tienda SERIAL PRIMARY KEY,
    nombre_tienda VARCHAR(100) NOT NULL,
    direccion_tienda VARCHAR(200) NOT NULL,
    distrito_tienda VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS producto (
    id_producto SERIAL PRIMARY KEY,
    nombre_producto VARCHAR(150) NOT NULL,
    precio_producto DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    id_categoria INT NOT NULL,
    id_tienda INT NOT NULL,
    CONSTRAINT fk_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria),
    CONSTRAINT fk_tienda FOREIGN KEY (id_tienda) REFERENCES tienda(id_tienda)
);
