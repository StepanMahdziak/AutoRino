package mahdziak.cars.saloncars.service;

import mahdziak.cars.saloncars.Repository.BodyTypeRepository;
import mahdziak.cars.saloncars.dto.request.BodyTypeRequest;
import mahdziak.cars.saloncars.dto.response.BodyTypeResponse;
import mahdziak.cars.saloncars.entity.BodyType;
import mahdziak.cars.saloncars.exception.WrongInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BodyTypeService {

    @Autowired
    private BodyTypeRepository bodyTypeRepository;


    public BodyTypeResponse save(BodyTypeRequest request) {
        return new BodyTypeResponse(bodyTypeRequestToBodyType(request,null));
    }

    private BodyType bodyTypeRequestToBodyType(BodyTypeRequest request, BodyType bodyType) {
        if (bodyType == null) {
            bodyType = new BodyType();
        }
        bodyType.setName(request.getName());
        return bodyTypeRepository.save(bodyType);
    }


    public List<BodyTypeResponse> findAll() {
        return bodyTypeRepository.findAll().stream()
                .map(BodyTypeResponse::new)
                .collect(Collectors.toList());
    }



    public BodyType findOne(Long id) throws WrongInputException {
        return bodyTypeRepository.findById(id)
                .orElseThrow(() -> new WrongInputException("BodyType with id " + id + " not exists"));
    }


    public BodyTypeResponse update(BodyTypeRequest request, Long id) throws WrongInputException {
        return new BodyTypeResponse(bodyTypeRequestToBodyType(request, findOne(id)));
    }


    public void delete(Long id) throws WrongInputException {
        bodyTypeRepository.delete(findOne(id));
    }



}
