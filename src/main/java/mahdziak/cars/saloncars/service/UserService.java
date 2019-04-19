package mahdziak.cars.saloncars.service;

import mahdziak.cars.saloncars.Repository.UserRepository;
import mahdziak.cars.saloncars.dto.request.UserRequest;
import mahdziak.cars.saloncars.dto.request.UserWithLoginRequest;
import mahdziak.cars.saloncars.dto.request.UserWithPasswordRequest;
import mahdziak.cars.saloncars.dto.response.DataResponseForUser;
import mahdziak.cars.saloncars.dto.response.UserResponse;
import mahdziak.cars.saloncars.entity.User;
import mahdziak.cars.saloncars.entity.UserRoles;
import mahdziak.cars.saloncars.exception.WrongInputDataException;
import mahdziak.cars.saloncars.exception.WrongInputException;
import mahdziak.cars.saloncars.security.tokenUtils.TokenTool;
import mahdziak.cars.saloncars.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenTool tokenTool;




    private User userRequestToUser(User user, UserRequest request) throws WrongInputException {
        if (user == null) {
            user = new User();
        }
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setBirthDate(request.getBirthDate());
        user.setCountry(request.getCountry());
        user.setLogin(request.getLogin());


//        customer.setUser(userService.findOneByEmail(customerRequest.getUserRequest().getLogin()));
//        customer.setUser(userService.findOneByLogin(customerRequest.getLogin()));


        return userRepository.save(user);
    }
    public UserResponse save(UserRequest request) throws Exception {
        if (userRepository.findByLoginEquals(request.getLogin()).isPresent()) {
            throw new Exception("Credentials are busy. Please, try one more time " +
                    "with other login");
        }
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setBirthDate(request.getBirthDate());
        user.setCountry(request.getCountry());
        user.setLogin(request.getLogin());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(UserRoles.CUSTOMER);

        user = userRepository.saveAndFlush(user);
        User saving = userRepository.save(user);
        Long id = saving.getId();
        UserRoles role = saving.getRole();
        String firstName = saving.getFirstName();
        String lastName = saving.getLastName();
        String birthDate = saving.getBirthDate();
        String country = saving.getCountry();
        String email = saving.getLogin();
        String password = tokenTool.createToken(user.getLogin(), user.getRole().name());


        return settingData(id,password,role,firstName, lastName, birthDate, country, email);

    }

    private UserResponse settingData(Long id, String password, UserRoles role, String firstName, String lastName, String birthDate, String country, String email){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(id);
        userResponse.setPassword(password);
        userResponse.setStatus(role);
        userResponse.setFirstName(firstName);
        userResponse.setLastName(lastName);
        userResponse.setBirthDate(birthDate);
        userResponse.setCountry(country);
        userResponse.setEmail(email);
        return userResponse;
    }

    public DataResponseForUser<UserResponse> findAllByFilter(String value, Integer page, Integer size, String fieldName, Sort.Direction direction) {
        Sort sort = Sort.by(direction, fieldName);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<User> pageUser;
        if (value != null && !value.equals("")) {
            UserSpecification specification = new UserSpecification(value);
            pageUser = userRepository.findAll(specification, pageRequest);
        } else {
            pageUser = userRepository.findAll(pageRequest);
        }
        return new DataResponseForUser<UserResponse>(pageUser.stream().map(UserResponse::new).collect(Collectors.toList()), pageUser);
    }

    public UserResponse findOneByRequest(UserWithLoginRequest request) throws WrongInputDataException {
        User user = userRepository.findByLoginEquals(request.getLogin()).orElseThrow(() -> new WrongInputDataException("User with login " + request.getLogin() + " not exists"));

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            Long id = user.getId();
            UserRoles role = user.getRole();
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            String birthDate = user.getBirthDate();
            String country = user.getCountry();
            String email = user.getLogin();
            String token = tokenTool.createToken(user.getLogin(), user.getRole().name());
            return settingData(id, token, role, firstName, lastName,birthDate,country, email);
        }

        throw new IllegalArgumentException("Wrong login or password");
    }

    //Create new password
    public String renamePassword(UserWithPasswordRequest userWithPasswordRequest) throws WrongInputDataException {
        User user = userRepository.findByLoginEquals(userWithPasswordRequest.getLogin()).orElseThrow(() -> new WrongInputDataException("User with login " + userWithPasswordRequest.getLogin() + " not exists"));

        if (passwordEncoder.matches(userWithPasswordRequest.getPassword(), user.getPassword())) {

            if (userWithPasswordRequest.getNewPassword().equals(userWithPasswordRequest.getRepeatNewPassword())) {
                user.setPassword(passwordEncoder.encode(userWithPasswordRequest.getNewPassword()));
            } else throw new WrongInputDataException("Wrong write new or old password");
        }
        userRepository.save(user);
        return user.getPassword();
    }


    @Transactional
    public UserResponse findOne(Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            return new UserResponse(userOptional.get());
        }else{
            throw new IllegalArgumentException("User with id "+id+" not found");
        }
    }


    public User findOneId(Long id) throws WrongInputException {
        return userRepository.findById(id)
                .orElseThrow(() -> new WrongInputException("User with id " + id + " not exists"));
    }


    public void delete(Long id) throws WrongInputException {
        userRepository.deleteById(id);
    }


    public UserResponse update(UserRequest userRequest, Long id) throws WrongInputException {
        return new UserResponse(userRequestToUser(findOneId(id), userRequest));
    }


}