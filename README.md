# Reservation API Project -  Sistem za rezervaciju termina

## Opis
Ovaj projekat implementira REST API za sistem rezervacije termina.  
Podržava autentifikaciju korisnika, pregled resursa i termina, kreiranje i otkazivanje rezervacija, kao i ponavljajuće rezervacije.

## Tehnologije
- java version "25.0.1" (Eclipse IDE - Version: 2026-03 M1 (4.39.0 M1)) - Maven
- Spring Boot
- Spring Security (JWT autentifikacija)
- PostgreSQL (Baza podataka: `reservationdb-project`)
- Postman (za testiranje API poziva)

## Setup
1. Klonirati repozitorijum.
git clone https://github.com/MirChe84/reservation-api.git
cd reservation-api

2. Kreirati bazu `reservationdb-project` u PostgreSQL:
CREATE DATABASE reservationdb_project;

3. Pokrenuti SQL skriptu (`schema.sql`) da se naprave tabele i test podaci.
4. U `application.properties` podesiti konekciju na bazu:

server.port=8081
server.shutdown=graceful

spring.datasource.url=jdbc:postgresql://localhost:5432/reservationdb-project
spring.datasource.username=korisnik
spring.datasource.password=Mikica84
spring.datasource.driver-class-name=org.postgresql.Driver


5.Pokrenuti aplikaciju:
mvn spring-boot:run ili ReservationApiApplication Run As Java Application

## API Endpoints
reservation_api_postman_collection.json
Autentifikacija

POST /api/auth  
→ Login korisnika, vraća JWT token.
Body: { "username": "testuser", "password": "testpass" }

Response
Json
{ "token": "jwt-token-value" }

Resursi
GET /api/resources  
→ Pregled svih resursa (naziv, tip, radno vreme).

GET /api/resources/{id}/slots  
→ Pregled slobodnih termina za određeni resurs po datumu.
Response primer:
Json
[
  { "slotId": 1, "datum": "2026-01-28", "vreme": "10:00", "resourceNaziv": "Sala 1" }
]

Rezervacije
GET /api/reservations  
→ Pregled svih rezervacija (admin funkcionalnost).

GET /api/reservations/{id}  
→ Pregled rezervacije po ID‑ju.

POST /api/reservations  
→ Kreiranje rezervacije.
Body:
Json
{
  "rezervacijaId": "RES-003",
  "status": "PENDING",
  "datumRezervacije": "2026-01-27T22:30:00",
  "userId": 1,
  "slotId": 2
}

PUT /api/reservations/{id}  
→ Izmena rezervacije (parcijalni update — možeš menjati samo status ili ceo objekat).
Body primer (samo status):
Json
{ "status": "CONFIRMED" }


DELETE /api/reservations/{id}  
→ Otkazivanje rezervacije.
Response:
Json
{ "message": "Reservation deleted successfully" }


POST /api/reservations/series  
→ Kreiranje ponavljajućih rezervacija do određenog datuma (do_datum).
Body primer:
Json
{
  "tip": "WEEKLY",
  "doDatum": "2026-02-28",
  "slotId": 2,
  "userId": 1
}


GET /api/myReservations  
→ Pregled sopstvenih rezervacija (na osnovu JWT tokena).
Response primer:
Json
[
  {
    "id": 3,
    "rezervacijaId": "RES-003",
    "status": "CONFIRMED",
    "datumRezervacije": "2026-01-27T22:30:00",
    "slotId": 2,
    "datum": "2026-01-28",
    "vreme": "10:00",
    "resourceNaziv": "Sala 1",
    "resourceTip": "Konferencijska"
  }
]



Postman Primeri
Autentifikacija
POST /api/auth

Body:
Json
{
  "username": "testuser",
  "password": "testpass"
}

Response:

json
{
  "token": "jwt-token-value"
}


Resursi
GET /api/resources

Response:

json
[
  { "resurs_id": 1, "naziv": "Sala 1", "tip": "Konferencijska", "radno_vreme": "08-16" },
  { "resurs_id": 2, "naziv": "Sala 2", "tip": "Radna", "radno_vreme": "09-17" }
]



GET /api/resources/{id}/slots

Response:

json
[
  { "slotId": 1, "datum": "2026-01-28", "vreme": "10:00", "resourceNaziv": "Sala 1" },
  { "slotId": 2, "datum": "2026-01-28", "vreme": "11:00", "resourceNaziv": "Sala 1" }
]



Rezervacije
GET /api/reservations

Response:

json
[
  { "id": 1, "rezervacijaId": "RES-001", "status": "CONFIRMED", "datumRezervacije": "2026-01-27T10:00:00" },
  { "id": 2, "rezervacijaId": "RES-002", "status": "PENDING", "datumRezervacije": "2026-01-27T11:00:00" }
]


GET /api/reservations/{id}

Response:
Json
{
  "id": 1,
  "rezervacijaId": "RES-001",
  "status": "CONFIRMED",
  "datumRezervacije": "2026-01-27T10:00:00",
  "userId": 1,
  "username": "testuser",
  "ime": "Petar Petrovic",
  "slotId": 2,
  "datum": "2026-01-28",
  "vreme": "10:00",
  "resourceNaziv": "Sala 1",
  "resourceTip": "Konferencijska"
}


POST /api/reservations

Body:
Json
{
  "rezervacijaId": "RES-003",
  "status": "PENDING",
  "datumRezervacije": "2026-01-27T22:30:00",
  "userId": 1,
  "slotId": 2
}


Response:
Json
{
  "id": 3,
  "rezervacijaId": "RES-003",
  "status": "PENDING",
  "datumRezervacije": "2026-01-27T22:30:00",
  "userId": 1,
  "username": "testuser",
  "ime": "Petar Petrovic",
  "slotId": 2,
  "datum": "2026-01-28",
  "vreme": "10:00",
  "resourceNaziv": "Sala 1",
  "resourceTip": "Konferencijska"
}


PUT /api/reservations/{id}

Body:
json
{ "status": "CONFIRMED" }

Response:
json
{
  "id": 3,
  "rezervacijaId": "RES-003",
  "status": "CONFIRMED",
  "datumRezervacije": "2026-01-27T22:30:00",
  "userId": 1,
  "slotId": 2
}


DELETE /api/reservations/{id}

Response:
json
{ "message": "Reservation deleted successfully" }

##Postman
Postman kolekcija: reservation_api_postman_collection.json

Postman environment: reservation_api_environment.json

##SQL Skripta za Reservation API
reservation_api_pg_dump.sql ili reservation_api.sql

##Dizajnerske odluke
Detaljno objašnjenje nalazi se u fajlu:
design_decisions.md

##Struktura projekta
struktura_projekta.txt




