package co.com.myphotosnameapp.myphotoswebbackend.services.implementation;


import co.com.myphotosnameapp.myphotoswebbackend.dtos.CompanyDto;
import co.com.myphotosnameapp.myphotoswebbackend.entities.CompanyEntity;
import co.com.myphotosnameapp.myphotoswebbackend.repositories.CompanyRepository;
import co.com.myphotosnameapp.myphotoswebbackend.services.ICompanyService;
import co.com.myphotosnameapp.myphotoswebbackend.utilities.IUtilityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService implements ICompanyService {

    @Autowired
    CompanyRepository repository ;

    @Autowired
    IUtilityMapper mapper ;

    @Override
    public Optional<CompanyDto> getById(String id) {
        Optional<CompanyEntity> company = repository.findById(id);
        if(company.isPresent())
            return Optional.of(mapper.toDto(company.get()));
        return Optional.empty();
    }
}
