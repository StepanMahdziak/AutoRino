package mahdziak.cars.saloncars.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserWithLoginRequest {

    private String login;

    private String password;


}
