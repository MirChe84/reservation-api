package booking.reservationapi.controller;

import booking.reservationapi.dto.ReservationDTO;
import booking.reservationapi.dto.ReservationResponseDTO;
import booking.reservationapi.model.Reservation;
import booking.reservationapi.model.ReservationSeries;
import booking.reservationapi.model.Slot;
import booking.reservationapi.model.Status;
import booking.reservationapi.model.User;
import booking.reservationapi.repository.SlotRepository;
import booking.reservationapi.repository.UserRepository;
import booking.reservationapi.service.ReservationService;
import booking.reservationapi.service.ReservationSeriesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationSeriesService reservationSeriesService;
    private final UserRepository userRepository;
    private final SlotRepository slotRepository;

    public ReservationController(ReservationService reservationService,
                                 ReservationSeriesService reservationSeriesService,
                                 UserRepository userRepository,
                                 SlotRepository slotRepository) {
        this.reservationService = reservationService;
        this.reservationSeriesService = reservationSeriesService;
        this.userRepository = userRepository;
        this.slotRepository = slotRepository;
    }

    // ✅ GET all reservations
    @GetMapping("/reservations")
    public List<ReservationResponseDTO> getAllReservations() {
        return reservationService.getAllReservations().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // ✅ GET reservation by ID
    @GetMapping("/reservations/{id}")
    public ReservationResponseDTO getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        return mapToResponseDTO(reservation);
    }

    // ✅ POST create reservation
    @PostMapping("/reservations")
    public ReservationResponseDTO createReservation(@RequestBody ReservationDTO dto) {
        if (dto.getUserId() == null || dto.getSlotId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId and slotId must not be null");
        }
        if (dto.getStatus() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "status must not be null");
        }

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Slot slot = slotRepository.findById(dto.getSlotId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Slot not found"));

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setSlot(slot);
        reservation.setStatus(Status.valueOf(dto.getStatus().toUpperCase()));
        reservation.setDatumRezervacije(dto.getDatumRezervacije());
        reservation.setRezervacijaId(dto.getRezervacijaId());

        Reservation saved = reservationService.saveReservation(reservation);
        return mapToResponseDTO(saved);
    }

    // ✅ PUT update reservation (parcijalni update)
    @PutMapping("/reservations/{id}")
    public ReservationResponseDTO updateReservation(@PathVariable Long id, @RequestBody ReservationDTO dto) {
        Reservation reservation = reservationService.getReservationById(id);

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
            reservation.setUser(user);
        }

        if (dto.getSlotId() != null) {
            Slot slot = slotRepository.findById(dto.getSlotId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Slot not found"));
            reservation.setSlot(slot);
        }

        if (dto.getStatus() != null) {
            reservation.setStatus(Status.valueOf(dto.getStatus().toUpperCase()));
        }

        if (dto.getDatumRezervacije() != null) {
            reservation.setDatumRezervacije(dto.getDatumRezervacije());
        }

        if (dto.getRezervacijaId() != null) {
            reservation.setRezervacijaId(dto.getRezervacijaId());
        }

        Reservation updated = reservationService.saveReservation(reservation);
        return mapToResponseDTO(updated);
    }

    // ✅ DELETE reservation (sa porukom)
    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Map<String, String>> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Reservation deleted successfully");
        return ResponseEntity.ok(response);
    }

    // ✅ POST reservation series
    @PostMapping("/reservations/series")
    public List<ReservationResponseDTO> createReservationSeries(@RequestBody ReservationSeries series) {
        return reservationSeriesService.createReservationSeries(series).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // ✅ GET my reservations
    @GetMapping("/myReservations")
    public List<ReservationResponseDTO> getMyReservations(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        return reservationService.getReservationsByUser(user).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // ✅ Helper method for mapping Reservation -> ReservationResponseDTO
    private ReservationResponseDTO mapToResponseDTO(Reservation reservation) {
        ReservationResponseDTO response = new ReservationResponseDTO();
        response.setId(reservation.getId());
        response.setRezervacijaId(reservation.getRezervacijaId());
        response.setStatus(reservation.getStatus().name());
        response.setDatumRezervacije(reservation.getDatumRezervacije());

        if (reservation.getUser() != null) {
            response.setUserId(reservation.getUser().getId());
            response.setUsername(reservation.getUser().getUsername());
            response.setIme(reservation.getUser().getIme());
        }

        if (reservation.getSlot() != null) {
            response.setSlotId(reservation.getSlot().getId());
            response.setDatum(reservation.getSlot().getDatum().toString());
            response.setVreme(reservation.getSlot().getVreme().toString());
            if (reservation.getSlot().getResource() != null) {
                response.setResourceNaziv(reservation.getSlot().getResource().getNaziv());
                response.setResourceTip(reservation.getSlot().getResource().getTip());
            }
        }

        return response;
    }
}
