-- =========================================
-- Kreiranje baze
-- =========================================
CREATE DATABASE reservationdb;
\c reservationdb;

-- =========================================
-- Tabela: korisnik
-- =========================================
CREATE TABLE korisnik (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    ime VARCHAR(100) NOT NULL
);

-- Primer podataka
INSERT INTO korisnik (username, password, ime)
VALUES 
('testuser', '$2a$10$hashZaLozinku', 'Petar Petrovic'),
('admin', '$2a$10$hashZaLozinkuAdmin', 'Admin Adminovic');

-- =========================================
-- Tabela: resurs
-- =========================================
CREATE TABLE resurs (
    resurs_id BIGSERIAL PRIMARY KEY,
    naziv VARCHAR(100) NOT NULL,
    tip VARCHAR(50) NOT NULL,
    radno_vreme VARCHAR(50)
);

-- Primer podataka
INSERT INTO resurs (naziv, tip, radno_vreme)
VALUES 
('Sala 1', 'Konferencijska', '08:00-16:00'),
('Sala 2', 'Radna', '09:00-17:00');

-- =========================================
-- Tabela: termin (slot)
-- =========================================
CREATE TABLE termin (
    termin_id BIGSERIAL PRIMARY KEY,
    datum DATE NOT NULL,
    vreme TIME NOT NULL,
    resurs_id BIGINT REFERENCES resurs(resurs_id) ON DELETE CASCADE
);

-- Primer podataka
INSERT INTO termin (datum, vreme, resurs_id)
VALUES 
('2026-01-28', '10:00', 1),
('2026-01-28', '11:00', 1),
('2026-01-28', '10:00', 2);

-- =========================================
-- Tabela: rezervacija
-- =========================================
CREATE TABLE rezervacija (
    id BIGSERIAL PRIMARY KEY,
    korisnik_id BIGINT REFERENCES korisnik(id) ON DELETE CASCADE,
    termin_id BIGINT REFERENCES termin(termin_id) ON DELETE CASCADE,
    status VARCHAR(20) NOT NULL CHECK (status IN ('PENDING','CONFIRMED','CANCELLED')),
    datum_rezervacije TIMESTAMP NOT NULL,
    rezervacijaId VARCHAR(50) UNIQUE NOT NULL
);

-- Primer podataka
INSERT INTO rezervacija (korisnik_id, termin_id, status, datum_rezervacije, rezervacijaId)
VALUES 
(1, 1, 'CONFIRMED', '2026-01-27 10:00:00', 'RES-001'),
(1, 2, 'PENDING', '2026-01-27 11:00:00', 'RES-002');

-- =========================================
-- Tabela: rezervaciona_serija
-- =========================================
CREATE TABLE rezervaciona_serija (
    serija BIGSERIAL PRIMARY KEY,
    tip VARCHAR(50) NOT NULL, -- DAILY, WEEKLY, MONTHLY
    do_datum DATE NOT NULL,
    korisnik_id BIGINT REFERENCES korisnik(id) ON DELETE CASCADE,
    slot_id BIGINT REFERENCES termin(termin_id) ON DELETE CASCADE
);

-- Primer podataka
INSERT INTO rezervaciona_serija (tip, do_datum, korisnik_id, slot_id)
VALUES 
('WEEKLY', '2026-02-28', 1, 2);
