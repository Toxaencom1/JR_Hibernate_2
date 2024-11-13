package entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "actor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Short id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @ToString.Exclude
    @ManyToMany(mappedBy = "actors")
//    @JoinTable(name = "film_actor",
//            joinColumns = @JoinColumn(name = "actor_id",referencedColumnName = "actor_id"),
//            inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"))
    private List<Film> films;
}
