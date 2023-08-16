package co.com.myphotosnameapp.myphotoswebbackend.repositories;

import co.com.myphotosnameapp.myphotoswebbackend.entities.CompanyEntity;
import co.com.myphotosnameapp.myphotoswebbackend.entities.ImageEntity;
import co.com.myphotosnameapp.myphotoswebbackend.entities.OwnerEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, String> {


    public List<ImageEntity> findByNameAndCompany(String s, CompanyEntity company);

    public List<ImageEntity> findByStatusAndCompany(String status, CompanyEntity company);

    public List<ImageEntity> findByOwner(OwnerEntity owner);

    public List<ImageEntity> findByCompany(CompanyEntity company);


    public List<ImageEntity> findByCompanyAndSourceDirectory(CompanyEntity company, String sourceDirectory);

    public List<ImageEntity> findByCompanyAndNameAndSourceDirectory(CompanyEntity company, String name, String sourceDirectory);



}
