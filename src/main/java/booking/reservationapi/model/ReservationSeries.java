package booking.reservationapi.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rezervaciona_serija")
public class ReservationSeries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serija;

    @Column(length = 50)
    private String tip;

    // datum do kog traje serija
    private LocalDate doDatum;

    // korisnik koji pravi seriju rezervacija
    private Long userId;

    // slot (termin) koji se ponavlja
    private Long slotId;

    // datum početka serije
    private LocalDate startDate;

    // koliko puta se ponavlja
    private int repeatCount;

    // poslovni ključ serije
    private String seriesId;

    public ReservationSeries() {}

    // --- GETTERI I SETTERI ---
    public Long getSerija() { return serija; }
    public void setSerija(Long serija) { this.serija = serija; }

    public String getTip() { return tip; }
    public void setTip(String tip) { this.tip = tip; }

    public LocalDate getDoDatum() { return doDatum; }
    public void setDoDatum(LocalDate doDatum) { this.doDatum = doDatum; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getSlotId() { return slotId; }
    public void setSlotId(Long slotId) { this.slotId = slotId; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public int getRepeatCount() { return repeatCount; }
    public void setRepeatCount(int repeatCount) { this.repeatCount = repeatCount; }

    public String getSeriesId() { return seriesId; }
    public void setSeriesId(String seriesId) { this.seriesId = seriesId; }

    @Override
    public String toString() {
        return "ReservationSeries{" +
                "serija=" + serija +
                ", tip='" + tip + '\'' +
                ", doDatum=" + doDatum +
                ", userId=" + userId +
                ", slotId=" + slotId +
                ", startDate=" + startDate +
                ", repeatCount=" + repeatCount +
                ", seriesId='" + seriesId + '\'' +
                '}';
    }
}
