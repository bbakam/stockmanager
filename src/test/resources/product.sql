CREATE TABLE product (
    id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(80) NOT NULL,
    description VARCHAR(250) NOT NULL,
    stock INT(6),
    price DECIMAL(5, 2)
)