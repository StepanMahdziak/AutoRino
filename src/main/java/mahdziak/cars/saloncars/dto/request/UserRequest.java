package mahdziak.cars.saloncars.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;


@Getter
@Setter
@NoArgsConstructor
public class UserRequest {

    @NotNull
    @Size(min = 2,max = 16)
    private String firstName;

    @NotNull
    private String lastName;

//    @Max(60)
//    @Min(18)
    private String birthDate;

    @NotBlank
    private String country;

    @NotEmpty
    @NotBlank
    @Email
    private String login;
    @NotEmpty
    @NotBlank
    @Min(8)
    private String password;





}
