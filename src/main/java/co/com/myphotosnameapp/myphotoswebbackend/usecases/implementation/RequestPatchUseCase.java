package co.com.myphotosnameapp.myphotoswebbackend.usecases.implementation;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.CeremonyDto;
import co.com.myphotosnameapp.myphotoswebbackend.dtos.RequestDto;
import co.com.myphotosnameapp.myphotoswebbackend.services.IRequestService;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.IRequestPatchUseCase;
import co.com.myphotosnameapp.myphotoswebbackend.utilities.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class RequestPatchUseCase implements IRequestPatchUseCase {

    @Autowired
    IRequestService service ;

    String idProvider = Utilities.PROVIDER_ID ;

    @Override
    public ResponseEntity<Void> patch(String id, RequestDto request) {
        try{

            Optional<RequestDto> requestOpt = service.read(id);

            if (!requestOpt.isPresent()){
                throw new IllegalArgumentException("ceremony does not exist!");
            }
            RequestDto old = requestOpt.get();
            old.setCustomerId(this.realValue(old.getCustomerId(), request.getCustomerId()));
            if(request.getStatus()!=null)
                old.setStatus(request.getStatus());

            if(request.getCeremonyId() != null){
                if(!request.getCeremonyId().getId().equals(old.getCeremonyId().getId())){
                    old.setCeremonyId(request.getCeremonyId());
                }
            }
            old.setModifiedBy(request.getModifiedBy()!= null ? request.getModifiedBy() : idProvider);
            old.setModifiedDate(LocalDateTime.now());

            service.update(old);

            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    private String realValue(String oldValue, String newValue){
        if(newValue == null || newValue.isEmpty())
            return oldValue;
        return newValue;
    }
}
