package booking.reservationapi.repository;

import booking.reservationapi.model.Reservation;
import booking.reservationapi.model.Slot;
import booking.reservationapi.model.Status;
import booking.reservationapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // Pretraga po poslovnom ključu (rezervacijaId)
    Optional<Reservation> findByRezervacijaId(String rezervacijaId);

    // Pretraga svih rezervacija za jednog korisnika
    List<Reservation> findByUser(User user);

    // Pretraga rezervacija po statusu
    List<Reservation> findByStatus(Status status);

    // Pretraga rezervacije po slotu (terminu)
    Optional<Reservation> findBySlot(Slot slot);

    // ✅ Pretraga rezervacija po resursu i datumu
    List<Reservation> findBySlot_Resource_IdAndSlot_Datum(Long resourceId, LocalDate datum);
}
