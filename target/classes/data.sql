-- Korisnici
INSERT INTO korisnik (ime, username, password) VALUES ('Miroslav', 'miroslav', 'test123');
INSERT INTO korisnik (ime, username, password) VALUES ('Ana', 'ana', 'pass456');

-- Resursi
INSERT INTO resurs (naziv, tip, radno_vreme) VALUES ('Sala A', 'Sala', '08:00-16:00');
INSERT INTO resurs (naziv, tip, radno_vreme) VALUES ('Sala B', 'Sala', '09:00-17:00');

-- Termini
INSERT INTO termin (datum, vreme, resurs_id) VALUES ('2026-01-25', '10:00', 1);
INSERT INTO termin (datum, vreme, resurs_id) VALUES ('2026-01-25', '11:00', 2);

-- Rezervacije
INSERT INTO rezervacija (korisnik_id, termin_id, status, rezervacija_id)
VALUES (1, 1, 'PENDING', 'RZV-001');

INSERT INTO rezervacija (korisnik_id, termin_id, status, rezervacija_id)
VALUES (2, 2, 'CONFIRMED', 'RZV-002');

-- Serije rezervacija
INSERT INTO rezervaciona_serija (naziv, tip, do_datum)
VALUES ('Sedmična sala A', 'weekly', '2026-03-01');
