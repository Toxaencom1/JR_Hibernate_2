package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Byte id;

    private String name;

    @Column(name = "last_update")
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @ToString.Exclude
    @ManyToMany(mappedBy = "categories")
//    @JoinTable(name = "film_category",
//            joinColumns = @JoinColumn(name = "category_id",referencedColumnName = "category_id"),
//            inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"))
    private List<Film> films;
}
