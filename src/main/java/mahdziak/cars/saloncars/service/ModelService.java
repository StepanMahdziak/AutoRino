package mahdziak.cars.saloncars.service;

import mahdziak.cars.saloncars.Repository.ModelRepository;
import mahdziak.cars.saloncars.dto.request.ModelRequest;
import mahdziak.cars.saloncars.dto.response.DataResponse;
import mahdziak.cars.saloncars.dto.response.ModelResponse;
import mahdziak.cars.saloncars.entity.Model;
import mahdziak.cars.saloncars.exception.WrongInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private MakeService makeService;



    public ModelResponse save(ModelRequest request)throws WrongInputException  {
        return new ModelResponse(modelRequestToModel(request, null));
    }

    private Model modelRequestToModel(ModelRequest request, Model model) throws WrongInputException {
        if (model == null) {
            model = new Model();
        }
        model.setName(request.getName());
        model.setMake(makeService.findOne(request.getMakeId()));
        return modelRepository.save(model);
    }
    public List<ModelResponse> findAll() {
        return modelRepository.findAll().stream()
                .map(ModelResponse::new)
                .collect(Collectors.toList());
    }

    public Model findOne(Long id) throws WrongInputException {
        return modelRepository.findById(id)
                .orElseThrow(() -> new WrongInputException("Model with id " + id + " not exists"));
    }

//
//    public DataResponse<ModelResponse> findAll(PaginationRequest pagination) {
//        Page<Model> all = modelRepository.findAll(pagination.mapToPageRequest());
//        return new DataResponse<>(all.get().map(ModelResponse::new).collect(Collectors.toList()), all.getTotalPages(), all.getTotalElements());
//    }


    public ModelResponse update(ModelRequest request, Long id) throws WrongInputException {
        return new ModelResponse(modelRequestToModel(request, findOne(id)));
    }

    public void delete(Long id) throws WrongInputException {
        modelRepository.delete(findOne(id));
    }

}
