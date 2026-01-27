package booking.reservationapi.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationSeriesResponseDTO {
    private Long seriesId;
    private String nazivSerije;
    private LocalDateTime pocetak;
    private LocalDateTime kraj;
    private String frekvencija;

    // Lista rezervacija koje pripadaju seriji
    private List<ReservationResponseDTO> reservations;

    // Getteri i setteri
    public Long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Long seriesId) {
        this.seriesId = seriesId;
    }

    public String getNazivSerije() {
        return nazivSerije;
    }

    public void setNazivSerije(String nazivSerije) {
        this.nazivSerije = nazivSerije;
    }

    public LocalDateTime getPocetak() {
        return pocetak;
    }

    public void setPocetak(LocalDateTime pocetak) {
        this.pocetak = pocetak;
    }

    public LocalDateTime getKraj() {
        return kraj;
    }

    public void setKraj(LocalDateTime kraj) {
        this.kraj = kraj;
    }

    public String getFrekvencija() {
        return frekvencija;
    }

    public void setFrekvencija(String frekvencija) {
        this.frekvencija = frekvencija;
    }

    public List<ReservationResponseDTO> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationResponseDTO> reservations) {
        this.reservations = reservations;
    }
}
