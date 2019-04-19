package mahdziak.cars.saloncars.specification;

import mahdziak.cars.saloncars.entity.*;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class CarSpecification implements Specification<Car> {


    private Long bodyTypeId;

    private Long modelId;

    private String value;

    public CarSpecification(Long bodyTypeId, Long modelId, String value) {
        this.bodyTypeId = bodyTypeId;
        this.modelId = modelId;
        this.value = value;
    }

    private Predicate findByColor(Root<User> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(root.get("color"), value);
    }

    @Override
    public Predicate toPredicate(Root<Car> r,
                                 CriteriaQuery<?> q,
                                 CriteriaBuilder cb) {

        List<Predicate> predicates = new ArrayList<>();



        Predicate byBodyTypeId = byBodyTypeId(r,cb);
        if (byBodyTypeId != null) {
            predicates.add(byBodyTypeId);
        }

        Predicate byModelId = byModelId(r, cb);
        if (byModelId != null){
            predicates.add(byModelId);
        }


        return cb.and(predicates.toArray(new Predicate[0]));
    }


    private Predicate byBodyTypeId(Root<Car> r,
                                  CriteriaBuilder cb) {
        if (bodyTypeId == null) return null;

        Join<Car, BodyType> bodyType = r.join("bodyType");

        return cb.equal(bodyType.get("id"), bodyTypeId);
    }

    private Predicate byModelId(Root<Car> r,
                                CriteriaBuilder cb){
        if (modelId == null) return null;

        Join<Car, Model> model = r.join("model");

        return cb.equal(model.get("id"), modelId);
    }
}













//
//
//    private CarFilterRequest filter;
//
//    public CarSpecification(CarFilterRequest filter) {
//        this.filter = filter;
//    }
//
//    @Override
//    public Predicate toPredicate(Root<Car> r, CriteriaQuery<?> cq, CriteriaBuilder cb) {
//        List<Predicate> predicates = new ArrayList<>();
//
//        Predicate byYear = findByYear(r, cb);
//        if (byYear != null) predicates.add(byYear);
//
//        Predicate byVolume = findByVolume(r, cb);
//        if (byVolume != null) predicates.add(byVolume);
//
//        Predicate byEngineLike = findByEngineLike(r, cb);
//        if (byEngineLike != null) predicates.add(byEngineLike);
//
//        Predicate byColorLike = findByColorLike(r,cb);
//        if (byColorLike != null) predicates.add(byColorLike);
//
//        cb.equal( r.get("newCar"), true);
//
//
//        return cb.and(predicates.toArray(new Predicate[0]));
//    }
//
//
//    private Predicate findByEngineLike(Root<Car> r, CriteriaBuilder cb) {
//        String engine = filter.getEngine();
//        if (engine == null || engine.trim().isEmpty()) {
//            return null;
//        }
//        return cb.like(r.get("engine"), '%' + engine + '%');
//    }
//
//    private Predicate findByColorLike(Root<Car> r, CriteriaBuilder cb){
//        String color = filter.getColor();
//        if (color == null || color.trim().isEmpty()){
//            return null;
//        }
//        return  cb.like(r.get("color"), '%' + color + '%');
//    }
//
//    private Predicate findByYear(Root<Car> r, CriteriaBuilder cb) {
//        if (filter.getYearFrom() == null && filter.getYearTo() == null) {
//            return null;
//        }
//        if (filter.getYearFrom() == null) {
//            filter.setYearFrom(0);
//        }
//        if (filter.getYearTo() == null) {
//            filter.setYearTo(Integer.MAX_VALUE);
//        }
//        return cb.between(r.get("year"), filter.getYearFrom(), filter.getYearTo());
//    }
//
//
//    private Predicate findByVolume(Root<Car> r, CriteriaBuilder cb) {
//        if (filter.getVolumeFrom() == null && filter.getVolumeTo() == null) {
//            return null;
//        }
//        if (filter.getVolumeFrom() == null) {
//            filter.setVolumeFrom(0d);
//        }
//        if (filter.getVolumeTo() == null) {
//            filter.setVolumeTo(Double.MAX_VALUE);
//        }
//        return cb.between(r.get("volume"), filter.getVolumeFrom(), filter.getVolumeTo());
//    }
//}
