package co.com.myphotosnameapp.myphotoswebbackend.repositories;

import co.com.myphotosnameapp.myphotoswebbackend.entities.ImageSetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageSetRepository extends JpaRepository<ImageSetEntity, String> {

    List<ImageSetEntity> findByImageSourceId(String imageSourceId);


}
