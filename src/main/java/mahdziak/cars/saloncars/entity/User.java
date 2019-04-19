package mahdziak.cars.saloncars.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity


public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String birthDate;

    private String country;

    private String login;

    private String password;

    @NotNull
    private UserRoles role;

    @OneToOne
    private Favorite favorite;

    @OneToMany(mappedBy = "user")
    private List<Product> products = new ArrayList<>();


}
