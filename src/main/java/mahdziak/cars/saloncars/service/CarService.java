package mahdziak.cars.saloncars.service;

import mahdziak.cars.saloncars.Repository.CarRepository;
import mahdziak.cars.saloncars.dto.request.CarRequest;
import mahdziak.cars.saloncars.dto.request.PaginationRequest;
import mahdziak.cars.saloncars.dto.response.CarResponse;
import mahdziak.cars.saloncars.dto.response.DataResponse;
import mahdziak.cars.saloncars.entity.Car;
import mahdziak.cars.saloncars.exception.WrongInputException;
import mahdziak.cars.saloncars.specification.CarSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private BodyTypeService bodyTypeService;

    @Autowired
    private ModelService modelService;


    public CarResponse save(CarRequest carRequest) throws WrongInputException {
        return new CarResponse(carRequestToCar(null, carRequest));
    }

    private Car carRequestToCar(Car car, CarRequest request) throws WrongInputException {
        if (car == null) {
            car = new Car();
        }
        car.setVolume(request.getVolume());
        car.setYear(request.getYear());
        car.setMaxSpeed(request.getMaxSpeed());
        car.setEngine(request.getEngine());
        car.setColor(request.getColor());
        car.setAcceleration(request.getAcceleration());
        car.setFuelType(request.getFuelType());
        car.setNewCar(request.getNewCar());
        car.setBodyType(bodyTypeService.findOne(request.getBodyTypeId()));
        car.setModel(modelService.findOne(request.getModelId()));
        return carRepository.save(car);
    }

    public List<CarResponse> findAll() {
        return carRepository.findAll().stream().map(CarResponse::new).collect(Collectors.toList());
    }

    public DataResponse<CarResponse> findAll(PaginationRequest pr) {
        Page<Car> page = carRepository.findAll(pr.toPageRequest());

        return new DataResponse<>(
                page.get().map(CarResponse::new).collect(Collectors.toList()),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }

    public DataResponse findAllByFilter(Integer page, Integer size, String direction, String fieldName, Long bodyTypeId, Long modelId,String value) {

            CarSpecification carSpecification = new CarSpecification(bodyTypeId, modelId, value);

        Page<Car> all = carRepository.findAll(carSpecification,
                PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), fieldName)));

        return new DataResponse<>(all.get().map(CarResponse::new).collect(Collectors.toList()), all.getTotalPages(), all.getTotalElements());
    }





    public Car findOne(Long id) throws WrongInputException {
        return carRepository.findById(id).orElseThrow(() -> new WrongInputException("Car with id " + id + " not exists"));
    }
    public CarResponse update(Long id, CarRequest carRequest) throws WrongInputException {
        return new CarResponse(carRequestToCar(findOne(id), carRequest));
    }
    public void delete(Long id) throws WrongInputException {
        carRepository.delete(findOne(id));
    }




}
