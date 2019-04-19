package mahdziak.cars.saloncars.controller;


import mahdziak.cars.saloncars.dto.request.MakeRequest;
import mahdziak.cars.saloncars.dto.response.DataResponse;
import mahdziak.cars.saloncars.dto.response.MakeResponse;
import mahdziak.cars.saloncars.entity.Make;
import mahdziak.cars.saloncars.exception.WrongInputException;
import mahdziak.cars.saloncars.service.MakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("products/make")
public class MakeController {


    @Autowired
    private MakeService makeService;

    @PostMapping
    public MakeResponse save(@Valid @RequestBody MakeRequest makeRequest) {
        return makeService.save(makeRequest);
    }

    @GetMapping
    public List<MakeResponse> findAll() {
        return makeService.findAll();
    }


    @GetMapping("/{id}")
    public Make getMakeById( @Valid @PathVariable Long id) throws WrongInputException {
        System.out.println("Get Make by id : "+id);
        return makeService.findOne(id);
    }
    

    @PutMapping("/{id}")
    public MakeResponse update( @Valid @RequestBody MakeRequest makeRequest, @PathVariable Long id) throws WrongInputException {
        return makeService.update(makeRequest, id);
    }

    @DeleteMapping("/{id}")
    public void delete( @Valid @PathVariable Long id) throws WrongInputException {
        System.out.println("Delete make by id "+id);
        makeService.delete(id);
    }
}
