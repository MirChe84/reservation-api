package booking.reservationapi.controller;

import booking.reservationapi.dto.SlotDTO;
import booking.reservationapi.model.Resource;
import booking.reservationapi.repository.ResourceRepository;
import booking.reservationapi.service.SlotService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    private final ResourceRepository resourceRepository;
    private final SlotService slotService;

    public ResourceController(ResourceRepository resourceRepository,
                              SlotService slotService) {
        this.resourceRepository = resourceRepository;
        this.slotService = slotService;
    }

    // ✅ Pregled svih resursa
    @GetMapping
    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    // ✅ Pregled slobodnih termina po datumu za resurs
    @GetMapping("/{id}/slots")
    public List<SlotDTO> getAvailableSlots(@PathVariable Long id,
                                           @RequestParam("date") String date) {
        LocalDate localDate = LocalDate.parse(date);
        return slotService.getAvailableSlots(id, localDate);
    }
}
