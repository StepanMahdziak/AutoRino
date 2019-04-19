package mahdziak.cars.saloncars.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest {
    @NotNull
    private String name;

    @Min(0) @Max(Integer.MAX_VALUE)
    private Integer price;

    private Integer priceWithDiscount;

    private Boolean isReserved;

    private Boolean isSold;

    private Boolean isDiscount;

    private LocalDate dateAdded;


    private Long countryId;

    private Long carId;
//    private CarRequest car;

    private FileRequest fileRequest;

//    private List<FileRequest> fileRequests = new ArrayList<>();




}
