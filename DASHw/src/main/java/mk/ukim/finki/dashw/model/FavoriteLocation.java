package mk.ukim.finki.dashw.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class FavoriteLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private User userr;
    private String location;

    public FavoriteLocation() {
    }

    public FavoriteLocation(User userr, String location) {
        this.userr = userr;
        this.location = location;
    }
}
