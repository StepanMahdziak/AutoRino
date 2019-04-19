package mahdziak.cars.saloncars.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class CarRequest {

    @NotBlank
    @NotNull
    private String color;
    @NotBlank
    @NotNull
    private Integer year;

    @Positive
    @Max(10)
    private Double volume;

    @NotBlank
    @NotNull
    private String engine;
    @Min(100)@Max(600)
    private Integer maxSpeed;

    private Integer acceleration;
    @NotBlank
    @NotNull
    private String  fuelType;

    private Boolean newCar;

    @NotNull
    private Long bodyTypeId;

    @NotNull
    private Long modelId;


}
