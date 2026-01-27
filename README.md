# Reservation API Project -  Sistem za rezervaciju termina

## 📌 Opis
Ovaj projekat implementira REST API za sistem rezervacije termina.  
Podržava autentifikaciju korisnika, pregled resursa i termina, kreiranje i otkazivanje rezervacija, kao i ponavljajuće rezervacije.

## 🛠️ Tehnologije
- java version "25.0.1" (Eclipse IDE - Version: 2026-03 M1 (4.39.0 M1)) - Maven
- Spring Boot
- Spring Security (JWT autentifikacija)
- PostgreSQL (Baza podataka: `reservationdb` i `reservationdb-project`)
- Postman (za testiranje API poziva)

## 🚀 Setup
1. Klonirati repozitorijum.
2. Kreirati bazu `reservationdb-project` u PostgreSQL.
3. Pokrenuti SQL skriptu (`schema.sql`) da se naprave tabele i test podaci.
4. U `application.properties` podesiti konekciju na bazu:
