package mahdziak.cars.saloncars.Repository;

import mahdziak.cars.saloncars.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {

    Page<Car> findAllByVolumeBetween(
            Double from, Double to, Pageable pageable);
//
}
