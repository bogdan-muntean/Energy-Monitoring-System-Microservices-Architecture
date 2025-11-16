SELECT * 
FROM users

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
	CONSTRAINT chk_user_role CHECK (role IN ('Administrator', 'Client'))
);

DROP TABLE users

INSERT INTO users (username, password, role) VALUES
('andrei_popescu', 'password123', 'Administrator'),
('maria_ionescu', 'clientpass1', 'Client'),
('ioana_dumitrescu', 'adminpass1', 'Administrator'),
('george_radu', 'clientpass2', 'Client'),
('ana_marin', 'adminpass2', 'Administrator'),
('mihai_stoica', 'clientpass3', 'Client'),
('alina_popa', 'adminpass3', 'Administrator'),
('florin_ilie', 'clientpass4', 'Client'),
('elena_petrescu', 'adminpass4', 'Administrator'),
('vlad_enescu', 'clientpass5', 'Client'),
('raluca_georgescu', 'adminpass5', 'Administrator'),
('stefan_ionescu', 'clientpass6', 'Client'),
('gabriela_mihai', 'adminpass6', 'Administrator'),
('nicu_dobre', 'clientpass7', 'Client'),
('roxana_badea', 'adminpass7', 'Administrator');