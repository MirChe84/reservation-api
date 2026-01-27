package booking.reservationapi.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class SlotDTO {
    private Long id;
    private LocalDate datum;
    private LocalTime vreme;
    private boolean available;

    public SlotDTO(Long id, LocalDate datum, LocalTime vreme, boolean available) {
        this.id = id;
        this.datum = datum;
        this.vreme = vreme;
        this.available = available;
    }

    // Getteri i setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public LocalTime getVreme() {
        return vreme;
    }

    public void setVreme(LocalTime vreme) {
        this.vreme = vreme;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
