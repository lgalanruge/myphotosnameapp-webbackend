package co.com.myphotosnameapp.myphotoswebbackend.usecases.implementation;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.CeremonyDto;
import co.com.myphotosnameapp.myphotoswebbackend.services.ICeremonyService;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.ICeremonyPostUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CeremonyPostUseCase implements ICeremonyPostUseCase {

    @Autowired
    ICeremonyService service ;

    @Override
    public ResponseEntity<String> post(CeremonyDto ceremonyDto) {
        try{
            return new ResponseEntity<>(service.create(ceremonyDto), HttpStatus.CREATED);
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
