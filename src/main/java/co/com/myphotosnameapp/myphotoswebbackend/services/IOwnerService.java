package co.com.myphotosnameapp.myphotoswebbackend.services;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.OwnerDto;


import java.util.Optional;

public interface IOwnerService {

    public Optional<OwnerDto> getOwnerById(String id);


}
