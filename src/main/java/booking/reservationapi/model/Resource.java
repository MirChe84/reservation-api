package booking.reservationapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "resurs")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resurs_id")
    private Long id;   // standardizovano ime polja

    @Column(nullable = false, length = 100)
    private String naziv;

    @Column(nullable = false, length = 50)
    private String tip;

    @Column(name = "radno_vreme", nullable = false, length = 100)
    private String radnoVreme;

    public Resource() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public String getTip() { return tip; }
    public void setTip(String tip) { this.tip = tip; }

    public String getRadnoVreme() { return radnoVreme; }
    public void setRadnoVreme(String radnoVreme) { this.radnoVreme = radnoVreme; }

    @Override
    public String toString() {
        return "Resource{id=" + id +
               ", naziv='" + naziv + '\'' +
               ", tip='" + tip + '\'' +
               ", radnoVreme='" + radnoVreme + '\'' +
               '}';
    }
}
