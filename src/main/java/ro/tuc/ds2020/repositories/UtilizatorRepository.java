package ro.tuc.ds2020.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.entities.Utilizator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface UtilizatorRepository extends JpaRepository<Utilizator, UUID> {

    @Query("select u from Utilizator u where u.username = ?1 and u.password = ?2")
    Optional<Utilizator> findUserByUsernameAndPassword(String username, String password);

}



