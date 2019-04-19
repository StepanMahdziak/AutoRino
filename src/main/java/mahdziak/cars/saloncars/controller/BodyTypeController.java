package mahdziak.cars.saloncars.controller;

import mahdziak.cars.saloncars.dto.request.BodyTypeRequest;
import mahdziak.cars.saloncars.dto.response.BodyTypeResponse;
import mahdziak.cars.saloncars.exception.WrongInputException;
import mahdziak.cars.saloncars.service.BodyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bodyType")
public class BodyTypeController {

    @Autowired
    private BodyTypeService bodyTypeService;


    @PostMapping
    public BodyTypeResponse save(@Valid @RequestBody BodyTypeRequest bodyTypeRequest) {
        return bodyTypeService.save(bodyTypeRequest);
    }

    @GetMapping
    public List<BodyTypeResponse> findAll() {
        return bodyTypeService.findAll();
    }


    @PutMapping("/{id}")
    public BodyTypeResponse update(@RequestBody BodyTypeRequest bodyTypeRequest, @PathVariable Long id) throws WrongInputException {
        return bodyTypeService.update(bodyTypeRequest, id);
    }



    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws WrongInputException {
        System.out.println("Delete BodyType by id "+id);
        bodyTypeService.delete(id);
    }
}
