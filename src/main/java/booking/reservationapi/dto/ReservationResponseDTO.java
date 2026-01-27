package booking.reservationapi.dto;

import java.time.LocalDateTime;

public class ReservationResponseDTO {
    private Long id;
    private String rezervacijaId;
    private String status;
    private LocalDateTime datumRezervacije;

    private Long userId;
    private String username;
    private String ime;

    private Long slotId;
    private String datum;
    private String vreme;
    private String resourceNaziv;
    private String resourceTip;

    // Getteri i setteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRezervacijaId() { return rezervacijaId; }
    public void setRezervacijaId(String rezervacijaId) { this.rezervacijaId = rezervacijaId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getDatumRezervacije() { return datumRezervacije; }
    public void setDatumRezervacije(LocalDateTime datumRezervacije) { this.datumRezervacije = datumRezervacije; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }

    public Long getSlotId() { return slotId; }
    public void setSlotId(Long slotId) { this.slotId = slotId; }

    public String getDatum() { return datum; }
    public void setDatum(String datum) { this.datum = datum; }

    public String getVreme() { return vreme; }
    public void setVreme(String vreme) { this.vreme = vreme; }

    public String getResourceNaziv() { return resourceNaziv; }
    public void setResourceNaziv(String resourceNaziv) { this.resourceNaziv = resourceNaziv; }

    public String getResourceTip() { return resourceTip; }
    public void setResourceTip(String resourceTip) { this.resourceTip = resourceTip; }
}
