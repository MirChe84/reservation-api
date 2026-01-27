-- Brisanje starih tabela ako postoje
DROP TABLE IF EXISTS rezervacija CASCADE;
DROP TABLE IF EXISTS termin CASCADE;
DROP TABLE IF EXISTS resurs CASCADE;
DROP TABLE IF EXISTS korisnik CASCADE;
DROP TABLE IF EXISTS rezervaciona_serija CASCADE;

-- Tabela korisnik
CREATE TABLE korisnik (
    id BIGSERIAL PRIMARY KEY,
    ime VARCHAR(100) NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Tabela resurs
CREATE TABLE resurs (
    id BIGSERIAL PRIMARY KEY,
    naziv VARCHAR(100) NOT NULL,
    tip VARCHAR(50) NOT NULL,
    radno_vreme VARCHAR(50)
);

-- Tabela termin
CREATE TABLE termin (
    termin_id BIGSERIAL PRIMARY KEY,
    datum DATE NOT NULL,
    vreme VARCHAR(10) NOT NULL,
    resurs_id BIGINT NOT NULL REFERENCES resurs(id)
);

-- Tabela rezervacija
CREATE TABLE rezervacija (
    id BIGSERIAL PRIMARY KEY,
    korisnik_id BIGINT NOT NULL REFERENCES korisnik(id),
    termin_id BIGINT NOT NULL REFERENCES termin(termin_id),
    status VARCHAR(20) NOT NULL,
    datum_rezervacije TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    rezervacija_id VARCHAR(255) NOT NULL
);

-- Tabela rezervaciona_serija
CREATE TABLE rezervaciona_serija (
    id BIGSERIAL PRIMARY KEY,
    naziv VARCHAR(100) NOT NULL,
    tip VARCHAR(50),
    do_datum DATE
);
