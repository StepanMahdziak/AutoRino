package mahdziak.cars.saloncars.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mahdziak.cars.saloncars.dto.request.FileRequest;
import mahdziak.cars.saloncars.entity.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponse {

    private Long id;

    private String name;

    private Integer price;

    private Integer priceWithDiscount;

    private Boolean isReserved;

    private Boolean isSold;

    private Boolean isDiscount;

    private LocalDate dateAdded;



    private String country;

    private String logo;

    private List<String> photos = new ArrayList<>();

    private CarResponse productEssence;




    public ProductResponse(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        priceWithDiscount = product.getPriceWithDiscount();
        isReserved = product.getIsReserved();
        isSold = product.getIsSold();
        isDiscount = product.getIsDiscount();
        dateAdded = product.getDateAdded();
        productEssence = new CarResponse(product.getCar());
        logo = product.getPathToImg();
//        country = new CountryResponse(product.getCountry());
        country = product.getCountry().getName();


    }

}
