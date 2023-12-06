-- Esempi per la tabella 'user'
INSERT INTO _user (username, password, email) VALUES
    ('khaled_abdelrehim', 'password_1', 'khaled.abdelrehim@logiclytics.com'),
    ('yassin_gamal', 'password_2', 'yassin.gamal@logiclytics.com'),
    ('viewer', 'password_3', 'viewer@logiclytics.com');

-- Esempi per la tabella 'role'
INSERT INTO role (role_name) VALUES
    ('admin'),
    ('editor'),
    ('viewer');

-- Esempi per la tabella 'user_role'
INSERT INTO user_role (user_id, role_id) VALUES
    (1, 1), -- khaled è un admin
    (2, 2), -- yassin è un editor
    (3, 3); -- viewer è un viewer

-- Esempi per la tabella 'permission'
INSERT INTO permission (permission_name) VALUES
    ('create_product'),
    ('create_user'),
    ('registration_confirm'),
    ('view_dashboard');

-- Esempi per la tabella 'role_permission'
INSERT INTO role_permission (role_id, permission_id) VALUES
    (1, 1), -- Admin ha il permesso di creare prodotti
    (1, 2), -- Editor ha il permesso di creare utenti
    (3, 4); -- Viewer ha il permesso di visualizzare il dashboard
