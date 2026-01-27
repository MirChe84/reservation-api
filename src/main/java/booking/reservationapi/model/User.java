package booking.reservationapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "korisnik")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;


    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 100)
    private String ime;

    public User() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }

    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "', ime='" + ime + "'}";
    }
}
