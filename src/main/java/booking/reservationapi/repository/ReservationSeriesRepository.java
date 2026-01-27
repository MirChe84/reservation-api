package booking.reservationapi.repository;

import booking.reservationapi.model.ReservationSeries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationSeriesRepository extends JpaRepository<ReservationSeries, Long> {
}
