package mahdziak.cars.saloncars.controller;

import mahdziak.cars.saloncars.dto.request.ProductRequest;
import mahdziak.cars.saloncars.dto.response.DataResponseForUser;
import mahdziak.cars.saloncars.dto.response.ProductResponse;
import mahdziak.cars.saloncars.dto.response.UserResponse;
import mahdziak.cars.saloncars.exception.WrongInputException;
import mahdziak.cars.saloncars.service.ProductService;
import mahdziak.cars.saloncars.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;



//    @CrossOrigin
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("public/users")
    public DataResponseForUser<UserResponse> getUsers(@RequestParam(required = false) String value,
                                                      @RequestParam Integer page,
                                                      @RequestParam Integer size,
                                                      @RequestParam String fieldName,
                                                      @RequestParam Sort.Direction direction){
        System.out.println("GET ALL USERS");
        return userService.findAllByFilter(value,page,size,fieldName,direction);
    }


//    @PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER')")
    @GetMapping("/users/{id}")
    public UserResponse getUsersById(@PathVariable Long id){
        System.out.println("Get user by id : "+id);
        return userService.findOne(id);
    }

//    @PreAuthorize("hasAnyAuthority('ADMIN','CUSTOMER')")
    @DeleteMapping("/public/users/{id}")
    public void deleteUser(@PathVariable Long id) throws WrongInputException {
        System.out.println("Delete user by id "+id);
        userService.delete(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/products")
    public ProductResponse save(@RequestBody @Valid ProductRequest productRequest) throws WrongInputException, IOException {
        return productService.save(productRequest);
    }

//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/products/{id}")
    public ProductResponse Update(@PathVariable Long id, @RequestBody ProductRequest productRequest) throws WrongInputException, IOException {
        return productService.update(id, productRequest);
    }

//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) throws WrongInputException {
        productService.delete(id);
    }

}
