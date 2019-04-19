package mahdziak.cars.saloncars.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mahdziak.cars.saloncars.entity.Car;

@Getter
@Setter
@NoArgsConstructor

public class CarResponse {
    private Long id;

    private String color;

    private Integer year;

    private Double volume;

    private String engine;

    private Integer maxSpeed;

    private Integer acceleration;

    private String  fuelType;

    private Boolean newCar;



//
//    private Long bodyTypeId;
    private BodyTypeResponse bodyType;
//
//    private Long modelId;
    private ModelResponse model;



    public CarResponse(Car car) {
        id = car.getId();
        color = car.getColor();
        year = car.getYear();
        volume = car.getVolume();
        engine = car.getEngine();
        newCar = car.getNewCar();
        maxSpeed = car.getMaxSpeed();
        fuelType = car.getFuelType();
        acceleration = car.getAcceleration();
//        bodyTypeId = car.getBodyType().getId();
        bodyType = new BodyTypeResponse(car.getBodyType());
//        modelId = car.getModel().getId();
        model = new ModelResponse(car.getModel());

    }
}
