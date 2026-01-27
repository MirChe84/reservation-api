package booking.reservationapi.service;

import booking.reservationapi.model.Reservation;
import booking.reservationapi.model.Status;
import booking.reservationapi.model.User;
import booking.reservationapi.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    // CRUD
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    // 🔧 Dodatne metode

    // Pregled rezervacije po poslovnom ključu
    public Reservation getReservationByCode(String rezervacijaId) {
        return reservationRepository.findByRezervacijaId(rezervacijaId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    // Pregled rezervacija za jednog korisnika
    public List<Reservation> getReservationsByUser(User user) {
        return reservationRepository.findByUser(user);
    }

    // Pregled rezervacija po statusu
    public List<Reservation> getReservationsByStatus(Status status) {
        return reservationRepository.findByStatus(status);
    }
}
