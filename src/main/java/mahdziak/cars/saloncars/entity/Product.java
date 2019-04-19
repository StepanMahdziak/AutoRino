package mahdziak.cars.saloncars.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;

    private Integer price;
//    private BigDecimal price;

    private Integer priceWithDiscount;

    private Boolean isReserved;

    private Boolean isSold;

    private Boolean isDiscount;

    private LocalDate dateAdded;

    private String pathToImg;

//    private List<String> photos = new ArrayList<>();




    @OneToOne
    private Car car;

    @ManyToOne
    private User user;

    @ManyToOne
    private Favorite favorite;

    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "product")
    private List<Order> orders = new ArrayList<>();


//
//     @Enumerated(EnumType.STRING)
//    private ProductEssence productEssence;


//    @NotNull
//    private ProductEssence productEssence;


}
