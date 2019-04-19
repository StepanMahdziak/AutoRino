package mahdziak.cars.saloncars.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mahdziak.cars.saloncars.entity.BodyType;

@Getter
@Setter
@NoArgsConstructor
public class BodyTypeResponse {

    private Long id;

    private String name;


    public BodyTypeResponse (BodyType bodyType){
        id = bodyType.getId();
        name = bodyType.getName();
    }
}
