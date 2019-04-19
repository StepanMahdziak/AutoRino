package mahdziak.cars.saloncars.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mahdziak.cars.saloncars.entity.Make;

@Getter
@Setter
@NoArgsConstructor
public class MakeResponse {
    private Long id;
    private String name;


    public MakeResponse(Make make) {
        id = make.getId();
        name = make.getName();


    }
}
