package booking.reservationapi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rezervacija")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")   // primarni ključ
    private Long id;

    @ManyToOne
    @JoinColumn(name = "korisnik_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "termin_id", nullable = false)
    private Slot slot;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status;

    @Column(name = "datum_rezervacije", nullable = false)
    private LocalDateTime datumRezervacije = LocalDateTime.now();

    @Column(name = "rezervacija_id", nullable = false, unique = true)
    private String rezervacijaId;   // poslovni identifikator


    public Reservation() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Slot getSlot() { return slot; }
    public void setSlot(Slot slot) { this.slot = slot; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public LocalDateTime getDatumRezervacije() { return datumRezervacije; }
    public void setDatumRezervacije(LocalDateTime datumRezervacije) { this.datumRezervacije = datumRezervacije; }

    public String getRezervacijaId() { return rezervacijaId; }
    public void setRezervacijaId(String rezervacijaId) { this.rezervacijaId = rezervacijaId; }

    @Override
    public String toString() {
        return "Reservation{id=" + id +
               ", user=" + (user != null ? user.getId() : null) +
               ", slot=" + (slot != null ? slot.getId() : null) +
               ", status=" + status +
               ", datum=" + datumRezervacije +
               ", rezervacijaId='" + rezervacijaId + "'}";
    }
}
