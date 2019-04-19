package mahdziak.cars.saloncars.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserWithPasswordRequest {

    private String password;

    private String newPassword;

    private String repeatNewPassword;

    private String login;
}
