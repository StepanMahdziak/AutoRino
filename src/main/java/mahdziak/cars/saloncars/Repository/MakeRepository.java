package mahdziak.cars.saloncars.Repository;

import mahdziak.cars.saloncars.entity.Make;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MakeRepository extends JpaRepository<Make,Long> {
}
