package booking.reservationapi.service;

import booking.reservationapi.model.Reservation;
import booking.reservationapi.model.ReservationSeries;
import booking.reservationapi.model.Slot;
import booking.reservationapi.model.User;
import booking.reservationapi.model.Status;
import booking.reservationapi.repository.ReservationRepository;
import booking.reservationapi.repository.ReservationSeriesRepository;
import booking.reservationapi.repository.SlotRepository;
import booking.reservationapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationSeriesService {
    private final ReservationSeriesRepository reservationSeriesRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final SlotRepository slotRepository;

    public ReservationSeriesService(ReservationSeriesRepository reservationSeriesRepository,
                                    ReservationRepository reservationRepository,
                                    UserRepository userRepository,
                                    SlotRepository slotRepository) {
        this.reservationSeriesRepository = reservationSeriesRepository;
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.slotRepository = slotRepository;
    }

    // CRUD nad serijama
    public List<ReservationSeries> getAllSeries() {
        return reservationSeriesRepository.findAll();
    }

    public ReservationSeries getSeriesById(Long id) {
        return reservationSeriesRepository.findById(id).orElse(null);
    }

    public ReservationSeries saveSeries(ReservationSeries series) {
        return reservationSeriesRepository.save(series);
    }

    public void deleteSeries(Long id) {
        reservationSeriesRepository.deleteById(id);
    }

    // ✅ Kreiranje ponavljajućih rezervacija na osnovu serije
    public List<Reservation> createReservationSeries(ReservationSeries series) {
        User user = userRepository.findById(series.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Slot slot = slotRepository.findById(series.getSlotId())
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        List<Reservation> reservations = new ArrayList<>();

        for (int i = 0; i < series.getRepeatCount(); i++) {
            Reservation reservation = new Reservation();
            reservation.setUser(user);
            reservation.setSlot(slot);
            reservation.setStatus(Status.CONFIRMED);
            reservation.setDatumRezervacije(series.getStartDate().plusWeeks(i).atStartOfDay());  // primer: svake nedelje
            reservation.setRezervacijaId(series.getSeriesId() + "-" + (i + 1));

            reservations.add(reservationRepository.save(reservation));
        }

        return reservations;
    }
}
