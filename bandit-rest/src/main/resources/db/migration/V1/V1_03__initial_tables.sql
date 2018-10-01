--
-- Application tables
--
--

CREATE TABLE app_user (
    id bigserial NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(60) NOT NULL,
    email character varying(100) NOT NULL,
    first_name character varying(50),
    last_name character varying(50),

    created_by character varying(50) NOT NULL,
    created_date timestamp NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp
);

CREATE TABLE bandit (
    id bigserial NOT NULL,
    first_name character varying(255) NOT NULL,
    not_available boolean NOT NULL,
    content text,
    id_boss bigserial NOT NULL,
    is_boss boolean NOT NULL,
    bandit_date timestamp NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp,
    UNIQUE (id)
);

CREATE TABLE history (
    id bigserial NOT NULL,
    id_bandit bigserial NOT NULL,
    id_child bigserial NOT NULL,
    created_by character varying(50) NOT NULL,
    created_date timestamp NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp,
    UNIQUE (id)
);

INSERT INTO app_user (id, username, password, email, first_name, last_name, created_by,created_date)
    VALUES (1, 'user', '$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K', 'user@localhost', 'User', 'User', 'system', CURRENT_TIMESTAMP);

-- JEFAZO 1
INSERT INTO bandit (id, first_name, not_available, content, id_boss, is_boss, bandit_date, created_by,created_date)
    VALUES (1, 'SuperCapo David García','0', 'El super jefazo (jefazo 1, año 2000)', 1, '1', '2000-01-01 01:00:00', 'system', CURRENT_TIMESTAMP);
-- --------- BANDA JEFAZO 1
INSERT INTO bandit (id, first_name, not_available, content, id_boss, is_boss,bandit_date, created_by,created_date)
    VALUES (11, 'Julio García','1', 'empleado a sueldo de García (año 2006)', 1, '0', '2006-01-01 01:00:00', 'system', CURRENT_TIMESTAMP);
INSERT INTO bandit (id, first_name, not_available, content, id_boss, is_boss,bandit_date, created_by,created_date)
    VALUES (12, 'Pepe García','1', 'empleado a sueldo de García (año 2003)', 1, '0', '2003-01-01 01:00:00', 'system', CURRENT_TIMESTAMP);
INSERT INTO bandit (id, first_name, not_available, content, id_boss, is_boss, bandit_date, created_by,created_date)
    VALUES (13, 'Manolo García','0', 'empleado a sueldo de García (año 2002)', 1, '0', '2002-01-01 01:00:00', 'system', CURRENT_TIMESTAMP);
INSERT INTO bandit (id, first_name, not_available, content, id_boss, is_boss, bandit_date, created_by,created_date)
    VALUES (14, 'Maria García','0', 'empleado a sueldo de García (año 2004)', 1, '0', '2004-01-01 01:00:00', 'system', CURRENT_TIMESTAMP);
INSERT INTO bandit (id, first_name, not_available, content, id_boss, is_boss, bandit_date, created_by,created_date)
    VALUES (15, 'Iñaki García','0', 'empleado a sueldo de García (año 2007)', 1, '0', '2007-01-01 01:00:00', 'system', CURRENT_TIMESTAMP);
INSERT INTO bandit (id, first_name, not_available, content, id_boss, is_boss, bandit_date, created_by,created_date)
    VALUES (16, 'Julian García','0', 'empleado a sueldo de García (año 2008)', 1, '0', '2008-01-01 01:00:00', 'system', CURRENT_TIMESTAMP);
INSERT INTO bandit (id, first_name, not_available, content, id_boss, is_boss, bandit_date, created_by,created_date)
    VALUES (17, 'Max García','0', 'empleado a sueldo de García (año 2009)', 1, '0', '2009-01-01 01:00:00', 'system', CURRENT_TIMESTAMP);

-- JEFAZO 2 (su lider JEFAZO 1)
 INSERT INTO bandit (id, first_name, not_available, content, id_boss, is_boss, bandit_date, created_by,created_date)
   VALUES (10, 'Capo Miguel García Perez','0', 'empleado a sueldo de García (jefazo 2, año 2001)', 1, '1', '2001-01-01 01:00:00', 'system', CURRENT_TIMESTAMP);
-- --------- BANDA JEFAZO 2
-- JEFAZO 3 (su lider jefazo 2)
INSERT INTO bandit (id, first_name, not_available, content, id_boss, is_boss, bandit_date, created_by,created_date)
    VALUES (20, 'Capo Angel García Perez Sanz','0', 'empleado a sueldo de García Perez (jefazo 3, año 2010)', 10, '1', '2010-01-01 01:00:00', 'system', CURRENT_TIMESTAMP);
INSERT INTO bandit (id, first_name, not_available, content, id_boss, is_boss, bandit_date, created_by,created_date)
    VALUES (21, 'Maria García Perez','0', 'empleado a sueldo de García Perez (año 2011)', 10, '0', '2011-02-01 01:00:00', 'system', CURRENT_TIMESTAMP);

-- --------- BANDA JEFAZO 3
 INSERT INTO bandit (id, first_name, not_available, content, id_boss, is_boss, bandit_date, created_by,created_date)
    VALUES (31, 'Alberto García Perez Sanz','0', 'empleado a sueldo de García Perez Sanz (año 2015)', 20, '0', '2015-01-01 01:00:00', 'system', CURRENT_TIMESTAMP);
 INSERT INTO bandit (id, first_name, not_available, content, id_boss, is_boss, bandit_date, created_by,created_date)
    VALUES (32, 'Justo García Perez Sanz','0', 'empleado a sueldo de García Perez Sanz (año 2016)', 20, '0', '2016-01-01 01:00:00', 'system', CURRENT_TIMESTAMP);
INSERT INTO bandit (id, first_name, not_available, content, id_boss, is_boss, bandit_date, created_by,created_date)
    VALUES (33, 'Sergio García Perez Sanz','0', 'empleado a sueldo de García Perez Sanz (año 2017)', 20, '0', '2017-01-01 01:00:00', 'system', CURRENT_TIMESTAMP);

--
-- Constrains
--

ALTER TABLE app_user
    ADD CONSTRAINT pk_app_user PRIMARY KEY (id);

ALTER TABLE app_user
    ADD CONSTRAINT app_user_email_key UNIQUE (email);

ALTER TABLE app_user
    ADD CONSTRAINT app_user_login_key UNIQUE (username);


ALTER TABLE bandit
    ADD CONSTRAINT pk_bandit PRIMARY KEY (id);


