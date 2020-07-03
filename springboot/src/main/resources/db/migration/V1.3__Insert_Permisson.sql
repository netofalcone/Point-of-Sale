INSERT INTO permission(id, deleted, description, name) VALUES (1, false, 'Read All items', 'read'), (2, false, 'insert any items', 'insert'), (3, false, 'update any items', 'update'), (4, false, 'Delete any items', 'delete'), (5, false, 'Associate any item to other item', 'associate');

INSERT INTO role(id, deleted, description, name) VALUES (1, false, 'Can do everything', 'Super User');

INSERT INTO public.role_permission(	id_role, id_permission)	VALUES (1, 1), (1, 2), (1, 3), (1, 4), (1, 5);

INSERT INTO public.user(id, email, name, password, cpf, deleted, phone, idrole) VALUES (1, 'admin@admin.com', 'admin', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '00000000000', false, '00000000',1);