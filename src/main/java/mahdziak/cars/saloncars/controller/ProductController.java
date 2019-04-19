package mahdziak.cars.saloncars.controller;
//
//import mahdziak.cars.saloncars.dto.request.PaginationRequest;
import mahdziak.cars.saloncars.dto.request.PaginationRequest;
import mahdziak.cars.saloncars.dto.request.ProductRequest;
import mahdziak.cars.saloncars.dto.response.DataResponse;
import mahdziak.cars.saloncars.dto.response.ProductResponse;
import mahdziak.cars.saloncars.exception.WrongInputException;
import mahdziak.cars.saloncars.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public List<ProductResponse> findAll() {
        return productService.findAll();
    }


    @GetMapping("/filter")
    public DataResponse<ProductResponse> getPageByFilter(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam String direction,
            @RequestParam String fieldName,
            @RequestParam(required = false) Long countryId,
            @RequestParam(required = false) Long carId,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice) {
        return productService.findAllByFilter(page, size, direction, fieldName, countryId,carId, minPrice, maxPrice);
    }

    @GetMapping("/")
    public  ProductResponse findOne(@PathVariable Long id,@RequestBody ProductRequest productRequest) throws WrongInputException {
        return new ProductResponse( productService.findOne(id));
    }













//    @PostMapping("/page/byCountryId")
//    public DataResponse<ProductResponse> findAllByCountryId(@RequestParam Long countryId, @RequestBody PaginationRequest paginationRequest) throws WrongInputException {
//        return productService.findAllByCountryId(countryId, paginationRequest);
//    }
//
//    @PostMapping("/page/byCarId")
//    public DataResponse<ProductResponse> findAllByCarId(@RequestParam Long carId, @RequestBody PaginationRequest paginationRequest) throws WrongInputException {
//        return productService.findAllByCarId(carId, paginationRequest);
//    }


}
