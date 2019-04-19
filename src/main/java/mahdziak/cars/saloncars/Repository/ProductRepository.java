package mahdziak.cars.saloncars.Repository;

import mahdziak.cars.saloncars.entity.Car;
import mahdziak.cars.saloncars.entity.Country;
import mahdziak.cars.saloncars.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> , JpaSpecificationExecutor<Product> {

}
