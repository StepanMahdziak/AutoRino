package mahdziak.cars.saloncars.specification;

import mahdziak.cars.saloncars.entity.Car;
import mahdziak.cars.saloncars.entity.Country;
import mahdziak.cars.saloncars.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification implements Specification<Product> {

    private Long countryId;

    private Long carId;

    private Integer minPrice;

    private Integer maxPrice;

//    private Integer priceWithDiscount;

    public ProductSpecification(Long countryId, Long carId, Integer minPrice, Integer maxPrice) {
        this.countryId = countryId;
        this.carId = carId;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
//        this.priceWithDiscount = priceWithDiscount;
    }



    @Override
    public Predicate toPredicate(Root<Product> r,
                                 CriteriaQuery<?> q,
                                 CriteriaBuilder cb) {

        List<Predicate> predicates = new ArrayList<>();

        Predicate byPrice = byPrice(r, cb);
        if (byPrice != null) {
            predicates.add(byPrice);
        }

        Predicate byCountryId = byCountryId(r, cb);
        if (byCountryId != null) {
            predicates.add(byCountryId);
        }


        Predicate byCarId = byCarId(r, cb);
        if (byCountryId != null) {
            predicates.add(byCarId);
        }
//        Predicate byWithDiscount = priceWithDiscount(r , cb);
//        if(byWithDiscount == null)
//            return byPrice;
        cb.equal( r.get("isDiscount"), true);
        cb.equal(r.get("isSold"),true);
        cb.equal(r.get("isReserved"),true);



        return cb.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate byCountryId(Root<Product> r,
                                  CriteriaBuilder cb) {
        if (countryId == null) return null;

        Join<Product, Country> country = r.join("country");

        return cb.equal(country.get("id"), countryId);
    }


    private Predicate byCarId(Root<Product> r,
                              CriteriaBuilder cb) {
        if (carId == null) return null;

        Join<Product, Car> country = r.join("car");

        return cb.equal(country.get("id"), carId);
    }


    private Predicate byPrice(Root<Product> r,
                              CriteriaBuilder cb) {
//        if (priceWithDiscount == null)return

        if (minPrice == null && maxPrice == null) return null;

        if (minPrice == null) {
            minPrice = 0;
        }

        if (maxPrice == null) {
            maxPrice = Integer.MAX_VALUE;
        }

        return cb.between(r.get("price"), minPrice, maxPrice);
    }

}

//
//
//        Boolean value = "true".equalsIgnoreCase(text) ? Boolean.TRUE :
//                "false".equalsIgnoreCase(text) ? Boolean.FALSE : null;
//
//        if(value != null) {
//            value.add(cb.equal(r.get("isActive"), value));
//        }

//         r.get("labels").in(1,2,3);


//    private LocalDate dateAdded;
