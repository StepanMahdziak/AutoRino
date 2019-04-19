package mahdziak.cars.saloncars.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor

public class BodyTypeRequest {
    @NotBlank
    @NotEmpty
    private String name;
}
