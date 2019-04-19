package mahdziak.cars.saloncars.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class ProductFilterRequest {

    private String name;

    private Integer priceFrom;
    private Integer priceTo;

    private Integer priceWithDiscount;

    private Boolean isReserved;

    private Boolean isSold;

    private Boolean isDiscount;

    private LocalDate dateAdded;

    private List<Long> countriesId = new ArrayList<>();
    private List<Long> carsId = new ArrayList<>();


//    private PaginationRequest pagination;

}
