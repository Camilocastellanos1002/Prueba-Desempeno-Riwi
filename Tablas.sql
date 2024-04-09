CREATE TABLE empresa(
	id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR (255) NOT NULL,
    sector VARCHAR (255) NOT NULL,
    ubicacion VARCHAR (255) NOT NULL,
    contacto VARCHAR (255) NOT NULL
);
CREATE TABLE vacante (
	id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR (255) NOT NULL,
    descripcion TEXT NOT NULL,
    duracion VARCHAR (255) NOT NULL,
    estado VARCHAR (50) CHECK (estado IN("ACTIVO","INACTIVO")),
    empresa_id INT,
    CONSTRAINT fk_empresa_id FOREIGN KEY (empresa_id) REFERENCES empresa (id) ON DELETE CASCADE
);
CREATE TABLE coder(
	id INT AUTO_INCREMENT PRIMARY KEY ,
    nombre VARCHAR (255) NOT NULL,
    apellidos VARCHAR (255) NOT NULL,
    documento VARCHAR (255) UNIQUE NOT NULL,
    cohorte INT NOT NULL, 
    cv TEXT NOT NULL
);
CREATE TABLE contratacion (
	id INT AUTO_INCREMENT PRIMARY KEY,
    fecha_aplicacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR (255) CHECK (estado IN("ACTIVO","INACTIVO","PENDIENTE")),
    salario DECIMAL (10,2), 
    vacante_id INT,
    CONSTRAINT fk_vacante_id FOREIGN KEY (vacante_id) REFERENCES vacante (id) ON DELETE CASCADE,
    coder_id INT,
    CONSTRAINT fk_coder_id FOREIGN KEY (coder_id) REFERENCES coder (id) ON DELETE CASCADE
);

SELECT *FROM empresa;
SELECT *FROM vacante;
DELETE FROM vacante WHERE id=1;
SELECT *FROM coder;

ALTER TABLE vacante
ADD COLUMN tecnologia VARCHAR (50);

ALTER TABLE coder
ADD COLUMN clan VARCHAR (50);

INSERT INTO empresa (nombre, sector, ubicacion, contacto) VALUES 
("Globant","tecnologia","medellin","3134254361"),
("Tigo","tecnologia","medellin","3007002020"),
("Epm","energia","sabaneta","3425437689"),
("Banco de Bogota","financiero","bogota","3134254361"),
("Index","cultura","guajira","3013155569");


SELECT * FROM contratacion INNER JOIN coder ON coder.id = contratacion.coder_id  INNER JOIN vacante ON vacante.id = contratacion.vacante_id INNER JOIN empresa;
SELECT * FROM vacante INNER JOIN empresa  ON empresa.id = vacante.empresa_id WHERE estado= 'ACTIVO';

SELECT * FROM contratacion;
DELETE FROM contratacion WHERE id=3;
