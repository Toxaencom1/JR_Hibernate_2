package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "film_text")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmText {
    @Id
    @Column(name = "film_id")
    private Short filmId;

    private String title;
    private String description;

    @OneToOne
    @JoinColumn(name = "film_id")
    private Film film;
}
