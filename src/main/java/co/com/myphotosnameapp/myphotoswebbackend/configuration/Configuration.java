package co.com.myphotosnameapp.myphotoswebbackend.configuration;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.CompanyDto;
import co.com.myphotosnameapp.myphotoswebbackend.dtos.OwnerDto;
import co.com.myphotosnameapp.myphotoswebbackend.entities.CompanyEntity;
import co.com.myphotosnameapp.myphotoswebbackend.services.ICompanyService;
import co.com.myphotosnameapp.myphotoswebbackend.services.IOwnerService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class Configuration {


    @Autowired
    ICompanyService companyService ;

    @Autowired
    IOwnerService service ;

    @Bean
    public CompanyDto getCompany(){
        log.info("get Company");
        Optional<CompanyDto> company = companyService.getById("4");
        return company.get();
    }

    @Bean
    public OwnerDto getOwner(){
        log.info("get Owner");
        Optional<OwnerDto> owner = service.getOwnerById("3");
        return owner.get();
    }

}
