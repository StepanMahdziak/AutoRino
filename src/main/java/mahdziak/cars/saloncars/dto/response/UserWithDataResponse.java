package mahdziak.cars.saloncars.dto.response;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserWithDataResponse {
    private Long id;

    private String firstName;

    private String lastName;

    private String birthDate;

    private String country;



    private String email;

    private String token;

}
