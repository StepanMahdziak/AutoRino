package mahdziak.cars.saloncars.controller;

import mahdziak.cars.saloncars.dto.request.UserRequest;
import mahdziak.cars.saloncars.dto.request.UserWithLoginRequest;
import mahdziak.cars.saloncars.dto.request.UserWithPasswordRequest;
import mahdziak.cars.saloncars.dto.response.UserResponse;
import mahdziak.cars.saloncars.exception.WrongInputDataException;
import mahdziak.cars.saloncars.exception.WrongInputException;
import mahdziak.cars.saloncars.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;


@CrossOrigin
@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("public/registration")
    public UserResponse saveUsers(@RequestBody @Valid UserRequest userRequest) throws Exception {
        return userService.save(userRequest);
    }

    @PostMapping("public/login")
    public UserResponse login(@RequestBody UserWithLoginRequest userWithLoginRequest) throws Exception {
        return userService.findOneByRequest(userWithLoginRequest);
    }

    @PutMapping("/profile/password")
    public String updatePassword(@RequestBody @Valid UserWithPasswordRequest userWithPassword) throws WrongInputDataException {
        return userService.renamePassword(userWithPassword);
    }

    @PutMapping("/public/profile/information/{id}")
    public UserResponse updateData( @RequestBody UserRequest userRequest, @PathVariable Long id) throws WrongInputException, IOException {
        return userService.update(userRequest,id);
    }

}
