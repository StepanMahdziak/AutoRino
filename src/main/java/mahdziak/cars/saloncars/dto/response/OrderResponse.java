package mahdziak.cars.saloncars.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mahdziak.cars.saloncars.entity.Order;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class OrderResponse {
    private Long id;

    private LocalDate date;


    public OrderResponse(Order order) {
        id = order.getId();
        date = order.getDate();
    }
}
