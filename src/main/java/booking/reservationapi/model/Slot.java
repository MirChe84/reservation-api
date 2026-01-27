package booking.reservationapi.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "termin")
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "termin_id")
    private Long id;   // standardizovano ime polja

    @Column(nullable = false)
    private LocalDate datum;

    @Column(nullable = false)
    private LocalTime vreme;

    @ManyToOne
    @JoinColumn(name = "resurs_id", nullable = false)
    private Resource resource;

    public Slot() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDatum() { return datum; }
    public void setDatum(LocalDate datum) { this.datum = datum; }

    public LocalTime getVreme() { return vreme; }
    public void setVreme(LocalTime vreme) { this.vreme = vreme; }

    public Resource getResource() { return resource; }
    public void setResource(Resource resource) { this.resource = resource; }

    @Override
    public String toString() {
        return "Slot{id=" + id + ", datum=" + datum + ", vreme=" + vreme + "}";
    }
}
