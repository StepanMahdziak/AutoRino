package mahdziak.cars.saloncars.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mahdziak.cars.saloncars.entity.Model;

@Getter
@Setter
@NoArgsConstructor
public class ModelResponse {
    private Long id;

    private String name;


//    private Long makeId;
    private MakeResponse make;

    public ModelResponse(Model model){
        id = model.getId();
        name = model.getName();
//        makeId = model.getMake().getId();
        make = new MakeResponse(model.getMake());

    }
}
