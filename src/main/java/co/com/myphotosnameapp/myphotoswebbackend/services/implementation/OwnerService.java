package co.com.myphotosnameapp.myphotoswebbackend.services.implementation;


import co.com.myphotosnameapp.myphotoswebbackend.dtos.OwnerDto;
import co.com.myphotosnameapp.myphotoswebbackend.entities.OwnerEntity;
import co.com.myphotosnameapp.myphotoswebbackend.repositories.OwnerRepository;
import co.com.myphotosnameapp.myphotoswebbackend.services.IOwnerService;
import co.com.myphotosnameapp.myphotoswebbackend.utilities.IUtilityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerService implements IOwnerService {

    @Autowired
    OwnerRepository repository ;

    @Autowired
    IUtilityMapper mapper ;

    @Override
    public Optional<OwnerDto> getOwnerById(String id) {
        Optional<OwnerEntity> owner = repository.findById(id);
        if(owner.isPresent())
            return Optional.of(mapper.toDto(owner.get()));
        return Optional.empty();
    }
}
