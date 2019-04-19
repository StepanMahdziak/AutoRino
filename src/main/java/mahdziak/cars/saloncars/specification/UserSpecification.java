package mahdziak.cars.saloncars.specification;

import mahdziak.cars.saloncars.entity.User;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSpecification implements Specification<User> {

    private String value;

    public UserSpecification(String value) {
        this.value = value;
    }

    private Predicate findByFirstName(Root<User> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(root.get("firstName"), value);
    }

    private Predicate findByLastName(Root<User> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(root.get("lastName"), value);
    }
    private Predicate findByCountry(Root<User> root, CriteriaBuilder criteriaBuilder){
        return criteriaBuilder.like(root.get("country"), value);
    }
    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.or(findByFirstName(root, criteriaBuilder), findByLastName(root, criteriaBuilder), findByCountry(root, criteriaBuilder));
    }
}

//}
//    private UserSearchRequest filter;
//
//    public UserSpecification(UserSearchRequest filter) {
//        this.filter = filter;
//    }
//
//    @Override
//    public Predicate toPredicate(Root<User> r, CriteriaQuery<?> cq, CriteriaBuilder cb) {
//        if (filter.getFirstName() == null && filter.getLastName() == null && filter.getBirthDate() == null ) {
//            return null;
//        }
//        List<Predicate> predicates = new ArrayList<>();
//        if (filter.getFirstName() != null && !filter.getFirstName().trim().isEmpty()) {
//            predicates.add(cb.like(r.get("firstName"), filter.getFirstName()));
//        }
//        if (filter.getLastName() != null && !filter.getLastName().trim().isEmpty()) {
//            predicates.add(cb.like(r.get("lastName"), filter.getLastName()));
//        }
//
//        if (filter.getBirthDate() != null && !filter.getBirthDate().trim().isEmpty()) {
//            predicates.add(cb.like(r.get("birthData"), filter.getBirthDate()));
//
//        }
//
//
//        return cb.or(predicates.toArray(new Predicate[0]));
//    }
//
//}