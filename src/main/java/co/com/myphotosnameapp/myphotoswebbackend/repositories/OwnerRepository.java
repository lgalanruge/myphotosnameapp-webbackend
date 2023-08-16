package co.com.myphotosnameapp.myphotoswebbackend.repositories;

import co.com.myphotosnameapp.myphotoswebbackend.entities.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerEntity, String> {

}
