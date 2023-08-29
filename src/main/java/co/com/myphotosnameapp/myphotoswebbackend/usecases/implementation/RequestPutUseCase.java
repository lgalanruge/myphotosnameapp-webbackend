package co.com.myphotosnameapp.myphotoswebbackend.usecases.implementation;


import co.com.myphotosnameapp.myphotoswebbackend.dtos.RequestDto;
import co.com.myphotosnameapp.myphotoswebbackend.services.IRequestService;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.IRequestPutUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class RequestPutUseCase implements IRequestPutUseCase {


    @Autowired
    IRequestService service ;

    @Override
    public ResponseEntity put(RequestDto request) {
        try{

            Optional<RequestDto> ceremony = service.read(request.getId());

            if (!ceremony.isPresent()){
                throw new IllegalArgumentException("request does not exist!");
            }
            service.update(request);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }
}
