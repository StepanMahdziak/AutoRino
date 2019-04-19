package mahdziak.cars.saloncars.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarFilterRequest {

    private Integer yearFrom;

    private Integer yearTo;

    private Double volumeFrom;
    private Double volumeTo;

    private String engine;

    private Boolean newCar;

    private String color;

    private Integer maxSpeed;

    private Integer acceleration;


//    private PaginationRequest pagination;
}
