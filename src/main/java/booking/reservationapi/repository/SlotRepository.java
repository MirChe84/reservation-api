package booking.reservationapi.repository;

import booking.reservationapi.model.Slot;
import booking.reservationapi.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {

    // vraća sve slotove za određeni resurs
    List<Slot> findByResource(Resource resource);

    // vraća sve slotove po ID-u resursa
    List<Slot> findByResourceId(Long resourceId);

    // vraća slotove za resurs i određeni datum
    List<Slot> findByResourceIdAndDatum(Long resourceId, LocalDate datum);
}
