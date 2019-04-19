package mahdziak.cars.saloncars.Repository;

import mahdziak.cars.saloncars.dto.response.BodyTypeResponse;
import mahdziak.cars.saloncars.entity.BodyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyTypeRepository extends JpaRepository<BodyType,Long> {

}
