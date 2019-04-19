package mahdziak.cars.saloncars.controller;

import mahdziak.cars.saloncars.dto.request.ModelRequest;
import mahdziak.cars.saloncars.dto.response.DataResponse;
import mahdziak.cars.saloncars.dto.response.ModelResponse;
import mahdziak.cars.saloncars.entity.Model;
import mahdziak.cars.saloncars.exception.WrongInputException;
import mahdziak.cars.saloncars.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("products/cars/model")
public class ModelController {


    @Autowired
    private ModelService modelService;

    @PostMapping
    public ModelResponse save(@Valid @RequestBody ModelRequest modelRequest) throws WrongInputException {
        return modelService.save(modelRequest);
    }

    @GetMapping
    public List<ModelResponse> findAll() {
        return modelService.findAll();
    }


    @GetMapping("/{id}")
    public Model getModelById( @Valid @PathVariable Long id) throws WrongInputException {
        System.out.println("Get model by id : "+id);
        return modelService.findOne(id);
    }
    

    @PutMapping("/{id}")
    public ModelResponse update( @Valid @RequestBody ModelRequest modelRequest, @PathVariable  Long id) throws WrongInputException {
        return modelService.update(modelRequest, id);
    }

    @DeleteMapping("/{id}")
    public void delete(  @Valid @PathVariable Long id) throws WrongInputException {
        System.out.println("Delete model by id "+id);
        modelService.delete(id);
    }
}
