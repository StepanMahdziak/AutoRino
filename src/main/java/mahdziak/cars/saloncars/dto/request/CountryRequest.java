package mahdziak.cars.saloncars.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class CountryRequest {

    @NotEmpty
    @NotBlank
    private String name;
}
