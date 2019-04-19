package mahdziak.cars.saloncars.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mahdziak.cars.saloncars.entity.Favorite;
import mahdziak.cars.saloncars.entity.User;
import mahdziak.cars.saloncars.entity.UserRoles;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String birthDate;

    private String country;

    private String email;

    private UserRoles status;

    private String password;
//    private List<Favorite> favorites = new ArrayList<>();


    public UserResponse(User user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        birthDate = user.getBirthDate();
        country = user.getCountry();
        email = user.getLogin();
        status = user.getRole();
        password = user.getPassword();
//        this.favorites = user.getFavorite().getId().toString().split(',')
    }

}
