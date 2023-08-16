package co.com.myphotosnameapp.myphotoswebbackend.services;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.CompanyDto;


import java.util.Optional;

public interface ICompanyService {

    public Optional<CompanyDto> getById(String id);


}
