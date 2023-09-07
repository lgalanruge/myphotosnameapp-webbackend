package co.com.myphotosnameapp.myphotoswebbackend.usecases.implementation;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.CeremonyDto;
import co.com.myphotosnameapp.myphotoswebbackend.services.ICeremonyService;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.ICeremonyPutUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class CeremonyPutUseCase implements ICeremonyPutUseCase {

    @Autowired
    ICeremonyService service ;

    @Override
    public ResponseEntity put(CeremonyDto ceremonyDto) {
        try{

            Optional<CeremonyDto> ceremony = service.read(ceremonyDto.getId());

            if (!ceremony.isPresent()){
                throw new IllegalArgumentException("ceremony does not exist!");
            }
            service.update(ceremonyDto);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }
}
