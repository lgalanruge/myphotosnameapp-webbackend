package co.com.myphotosnameapp.myphotoswebbackend.repositories;

import co.com.myphotosnameapp.myphotoswebbackend.entities.CompanyEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, String> {

}
