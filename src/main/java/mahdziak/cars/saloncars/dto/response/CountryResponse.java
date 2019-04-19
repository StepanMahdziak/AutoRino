package mahdziak.cars.saloncars.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mahdziak.cars.saloncars.entity.Country;

@Getter
@Setter
@NoArgsConstructor
public class CountryResponse {
    private Long id;
    private String name;

    public CountryResponse(Country country) {
        id = country.getId();
        name = country.getName();

    }
}
