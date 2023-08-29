package co.com.myphotosnameapp.myphotoswebbackend.repositories;

import co.com.myphotosnameapp.myphotoswebbackend.entities.ImageEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, String> {


    List<ImageEntity> findByNameAndProviderId(String s, String providerId);

    List<ImageEntity> findByStatusAndProviderId(String status, String providerId);

    List<ImageEntity> findByCustomerId(String customerId);

    List<ImageEntity> findByProviderId(String providerId);


    List<ImageEntity> findByProviderIdAndSourceDirectory(String providerId, String sourceDirectory);

    List<ImageEntity> findByProviderIdAndNameAndSourceDirectory(String providerId, String name, String sourceDirectory);



}
