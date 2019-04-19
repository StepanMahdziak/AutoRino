package mahdziak.cars.saloncars.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
//
//    private LocaleData data;



    @OneToMany(mappedBy = "favorite")
    private List<Product> products = new ArrayList<>();

    @OneToOne(mappedBy = "favorite")
    private User user;
}
