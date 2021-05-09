package ro.tuc.ds2020.repositories;

import ro.tuc.ds2020.entities.ActivityTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<ActivityTable,Integer> {
}
