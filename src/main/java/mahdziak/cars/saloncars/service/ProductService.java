package mahdziak.cars.saloncars.service;

import mahdziak.cars.saloncars.Repository.ProductRepository;
import mahdziak.cars.saloncars.dto.request.*;
import mahdziak.cars.saloncars.dto.response.DataResponse;
import mahdziak.cars.saloncars.dto.response.ProductResponse;
import mahdziak.cars.saloncars.entity.Product;
import mahdziak.cars.saloncars.exception.WrongInputException;
import mahdziak.cars.saloncars.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CountryService countryService;

    @Autowired
    private CarService carService;

    @Autowired
    private FileService fileService;


    public ProductResponse save(ProductRequest productRequest) throws IOException, WrongInputException {
        return new ProductResponse(productRequestToProduct(null, productRequest));
    }


    private Product productRequestToProduct(Product product, ProductRequest request) throws WrongInputException, IOException {
        if (product == null) {
            product = new Product();
        }
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setPriceWithDiscount(request.getPriceWithDiscount());
        product.setDateAdded(request.getDateAdded());
        product.setIsDiscount(request.getIsDiscount());
        product.setIsReserved(request.getIsReserved());
        product.setIsSold(request.getIsSold());
        product.setCountry(countryService.findOne(request.getCountryId()));
        product.setCar(carService.findOne(request.getCarId()));

        String file = fileService.saveFile(request.getFileRequest());
        product.setPathToImg(file);

        return productRepository.save(product);
    }


    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(ProductResponse::new).collect(Collectors.toList());
    }


    public DataResponse findAllByFilter(Integer page, Integer size, String direction, String fieldName, Long countryId, Long carId, Integer minPrice, Integer maxPrice) {
        ProductSpecification productSpecification = new ProductSpecification(countryId, carId, minPrice, maxPrice);

        Page<Product> all = productRepository.findAll(productSpecification, PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), fieldName)));
        return new DataResponse<>(all.get().map(ProductResponse::new).collect(Collectors.toList()), all.getTotalPages(), all.getTotalElements());
    }

    public Product findOne(Long id) throws WrongInputException {
        return productRepository.findById(id).orElseThrow(() -> new WrongInputException("Product with id " + id + " not exists"));
    }

    public ProductResponse update(Long id, ProductRequest productRequest) throws WrongInputException, IOException {
        return new ProductResponse(productRequestToProduct(findOne(id), productRequest));
    }

    public void delete(Long id)throws WrongInputException {
        productRepository.deleteById(id);
    }

}





//    public DataResponse<ProductResponse> findAll(PaginationRequest pr) {
//        Page<Product> page = productRepository.findAll(pr.toPageRequest());
//        return new DataResponse<>(
//                page.get().map(ProductResponse::new).collect(Collectors.toList()),
//                page.getTotalPages(),
//                page.getTotalElements()
//        );
//    }

//
//    public ProductResponse update(Long id, ProductRequest productRequest) throws WrongInputException {
//        return new ProductResponse(productRequestToProduct(findOne(id), productRequest));
//    }
//
//    public DataResponse<ProductResponse> findAllByCountryId(Long countryId, PaginationRequest paginationRequest) throws WrongInputException {
//        Country country = countryService.findOne(countryId);
//        Page<Product> byCountry = productRepository.findAllByCountry(country, paginationRequest.mapToPageRequest());
//        return new DataResponse<>(byCountry.get().map(ProductResponse::new).collect(Collectors.toList()),
//                byCountry.getTotalPages(), byCountry.getTotalElements());
//    }
//    public DataResponse<ProductResponse> findAllByCarId(Long carId, PaginationRequest paginationRequest) throws WrongInputException {
//        Car car = carService.findOne(carId);
//        Page<Product> byCar = productRepository.findAllByCar(car, paginationRequest.mapToPageRequest());
//        return new DataResponse<>(byCar.get().map(ProductResponse::new).collect(Collectors.toList()),
//                byCar.getTotalPages(), byCar.getTotalElements());
//    }




