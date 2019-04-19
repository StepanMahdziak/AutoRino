package mahdziak.cars.saloncars.controller;

import mahdziak.cars.saloncars.dto.request.CarRequest;
import mahdziak.cars.saloncars.dto.response.CarResponse;
import mahdziak.cars.saloncars.exception.WrongInputException;
import mahdziak.cars.saloncars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/products/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping
    public CarResponse save(@RequestBody CarRequest carRequest) throws WrongInputException {
        return carService.save(carRequest);
    }

    @GetMapping
    public List<CarResponse> findAll() {
        return carService.findAll();
    }
//
//    @GetMapping("/filter")
//    public DataResponse<CarResponse> getPageByFilter(
//            @RequestParam Integer page,
//            @RequestParam Integer size,
//            @RequestParam String fieldName,
//            @RequestParam String direction,
//            @RequestParam(required = false) Long bodyTypeId,
//            @RequestParam(required = false) Long modelId,
//            @RequestParam(required = false) String value
//    )
//    {
//        return carService.findAllByFilter(page, size, direction, fieldName, value ,bodyTypeId, modelId);
//    }
    @PutMapping("/{id}")
    public CarResponse Update(@PathVariable @Valid Long id, @RequestBody CarRequest carRequest) throws WrongInputException {
        return carService.update(id, carRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Valid Long id) throws WrongInputException {
        carService.delete(id);
    }



}

