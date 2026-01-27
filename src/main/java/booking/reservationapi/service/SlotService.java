package booking.reservationapi.service;

import booking.reservationapi.dto.SlotDTO;
import booking.reservationapi.model.Reservation;
import booking.reservationapi.model.Slot;
import booking.reservationapi.repository.ReservationRepository;
import booking.reservationapi.repository.SlotRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SlotService {

    private final SlotRepository slotRepository;
    private final ReservationRepository reservationRepository;

    // Konstruktor injection
    public SlotService(SlotRepository slotRepository,
                       ReservationRepository reservationRepository) {
        this.slotRepository = slotRepository;
        this.reservationRepository = reservationRepository;
    }

    // Vrati sve slotove
    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }

    // Vrati slot po ID-u
    public Slot getSlotById(Long id) {
        return slotRepository.findById(id).orElse(null);
    }

    // Sačuvaj ili ažuriraj slot
    public Slot saveSlot(Slot slot) {
        return slotRepository.save(slot);
    }

    // Obriši slot po ID-u
    public void deleteSlot(Long id) {
        slotRepository.deleteById(id);
    }

    // ✅ Vrati slotove za resurs i datum, sa oznakom dostupnosti
    public List<SlotDTO> getAvailableSlots(Long resourceId, LocalDate date) {
        // svi slotovi za resurs
        List<Slot> slots = slotRepository.findByResourceId(resourceId);

        // sve rezervacije za taj resurs i datum
        List<Reservation> rezervacije = reservationRepository
                .findBySlot_Resource_IdAndSlot_Datum(resourceId, date);

        return slots.stream()
                .filter(slot -> slot.getDatum().equals(date))
                .map(slot -> {
                    boolean zauzet = rezervacije.stream()
                            .anyMatch(r -> r.getSlot().getId().equals(slot.getId()));
                    return new SlotDTO(slot.getId(), slot.getDatum(), slot.getVreme(), !zauzet);
                })
                .collect(Collectors.toList());
    }
}
