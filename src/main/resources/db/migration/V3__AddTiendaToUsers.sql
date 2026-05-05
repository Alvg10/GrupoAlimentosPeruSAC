ALTER TABLE users
ADD COLUMN tienda_id BIGINT;

ALTER TABLE users
ADD CONSTRAINT fk_user_tienda
FOREIGN KEY (tienda_id) REFERENCES tienda(id_tienda);