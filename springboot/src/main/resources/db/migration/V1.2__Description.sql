
CREATE TABLE IF NOT EXISTS permission (
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(250) NULL,
	deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS role (
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(250) NULL,
	deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS role_permission (
	id_role INTEGER NOT NULL,
	id_permission INTEGER NOT NULL,
	PRIMARY KEY (id_role, id_permission),
	CONSTRAINT role_permission_role_FK FOREIGN KEY (id_role) REFERENCES role (id),
	CONSTRAINT role_permission_permission_FK FOREIGN KEY (id_permission) REFERENCES permission (id)
);

CREATE TABLE IF NOT EXISTS public.user (
	id SERIAL NOT NULL PRIMARY KEY,
	name VARCHAR(250) NOT NULL,
	email VARCHAR(250) NOT null,
	password VARCHAR(100) NOT null,
	phone VARCHAR(50),
	cpf VARCHAR(50) NOT NULL,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
	role_id INTEGER NOT NULL
);

INSERT INTO permission(id, deleted, description, name) VALUES (1, false, 'Read All items', 'read'), (2, false, 'insert any items', 'insert'), (3, false, 'update any items', 'update'), (4, false, 'Delete any items', 'delete'), (5, false, 'Associate any item to other item', 'associate');

INSERT INTO role(id, deleted, description, name) VALUES (1, false, 'Can do everything', 'Super User');

INSERT INTO public.role_permission(	id_role, id_permission)	VALUES (1, 1), (1, 2), (1, 3), (1, 4), (1, 5);

INSERT INTO public.user(id, email, name, password, cpf, deleted, phone, role_id) VALUES (1, 'admin@admin.com', 'admin', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '00000000000', false, '00000000',1);
