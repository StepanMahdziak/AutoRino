package mahdziak.cars.saloncars.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String color;

    private Integer year;

    private Double volume;

    private String engine;

    private Integer maxSpeed;

    private Integer acceleration;

    private Boolean newCar;

    private String  fuelType;



    @OneToOne(mappedBy = "car")
    private Product product;

    @ManyToOne
    private BodyType bodyType;

    @ManyToOne
    private Model model;


}
