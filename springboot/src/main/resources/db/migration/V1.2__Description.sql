CREATE TABLE permission (
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(250) NULL,
	deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE role (
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(250) NULL,
	deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE role_permission (
	id_role INTEGER NOT NULL,
	id_permission INTEGER NOT NULL,
	PRIMARY KEY (id_role, id_permission),
	CONSTRAINT role_permission_role_FK FOREIGN KEY (id_role) REFERENCES role (id),
	CONSTRAINT role_permission_permission_FK FOREIGN KEY (id_permission) REFERENCES permission (id)
);

CREATE TABLE public.user (
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(250) NOT null,
	password VARCHAR(100) NOT null,
	phone VARCHAR(50) NOT null,
	cpf VARCHAR(50) NOT NULL,
	deleted BOOLEAN NOT null DEFAULT FALSE,
	idrole INTEGER NOT null,
	CONSTRAINT user_role_FK FOREIGN KEY (idrole) REFERENCES role (id)
);
