package mahdziak.cars.saloncars.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter @Setter
public class SortRequest {
    private String field;
    private Sort.Direction direction;
}
