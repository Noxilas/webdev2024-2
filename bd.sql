CREATE DATABASE projeto_web;
USE projeto_web;

CREATE TABLE user(
    id                  INT unsigned NOT NULL AUTO_INCREMENT,  
    name                VARCHAR(48) NOT NULL,
    budget              DOUBLE unsigned NOT NULL,
    PRIMARY KEY         (id)
);


CREATE TABLE product(
    id                  INT unsigned NOT NULL AUTO_INCREMENT,  
    name                VARCHAR(32) NOT NULL,
    description         VARCHAR(64),
    reserve_price       DOUBLE unsigned NOT NULL DEFAULT 10.0,        -- minimum price definied by the seller
    PRIMARY KEY         (id)
);

CREATE TABLE bid(
    id_user             INT unsigned NOT NULL,  
    id_product          INT unsigned NOT NULL,
    time_stamp          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    price               DOUBLE unsigned NOT NULL,

    FOREIGN KEY         (id_user) REFERENCES user(id), 
    FOREIGN KEY         (id_product) REFERENCES product(id), 
    PRIMARY KEY         (id_user, id_product, time_stamp)
);



INSERT INTO user (name, budget) VALUES ('John Doe', 1000.0);
INSERT INTO user (name, budget) VALUES ('Jane Doe', 1020.0);

INSERT INTO product (name, description) VALUES ('Porsche Panamera', 'Last Owner: EduKof');
INSERT INTO product (name, description) VALUES ('1987 Walkman', 'No wear and tear');
INSERT INTO product (name, description) VALUES ('Lancha', 'Para 5 pessoas');
INSERT INTO product (name, description) VALUES ('Avião', 'Voador');


INSERT INTO bid (id_user, id_product, time_stamp, price) VALUES (1, 1, '2025-02-01 10:00:00', 120.0);



