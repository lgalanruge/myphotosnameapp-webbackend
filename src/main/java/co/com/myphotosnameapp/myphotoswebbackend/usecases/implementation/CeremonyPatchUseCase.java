package co.com.myphotosnameapp.myphotoswebbackend.usecases.implementation;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.CeremonyDto;
import co.com.myphotosnameapp.myphotoswebbackend.services.ICeremonyService;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.ICeremonyPatchUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class CeremonyPatchUseCase implements ICeremonyPatchUseCase {

    @Autowired
    ICeremonyService service ;

    @Override
    public ResponseEntity patch(String id, CeremonyDto ceremonyDto) {

        try{

            Optional<CeremonyDto> ceremony = service.read(id);

            if (!ceremony.isPresent()){
                throw new IllegalArgumentException("ceremony does not exist!");
            }
            CeremonyDto old = ceremony.get();
            old.setCustomerId(this.realValue(old.getCustomerId(), ceremonyDto.getCustomerId()));
            old.setStatus(this.realValue(old.getStatus(), ceremonyDto.getStatus()));
            old.setTitle(this.realValue(old.getTitle(), ceremonyDto.getTitle()));
            old.setModifiedBy(ceremonyDto.getModifiedBy());
            old.setModifiedDate(LocalDateTime.now());
            old.setDescription(this.realValue(old.getDescription(), ceremonyDto.getDescription()));

            service.update(ceremonyDto);

            return new ResponseEntity(HttpStatus.ACCEPTED);
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }

    private String realValue(String oldValue, String newValue){
        if(newValue == null || newValue.isEmpty())
            return oldValue;
        return newValue;
    }
}
