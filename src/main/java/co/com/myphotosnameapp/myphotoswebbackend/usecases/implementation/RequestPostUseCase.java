package co.com.myphotosnameapp.myphotoswebbackend.usecases.implementation;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.RequestDto;
import co.com.myphotosnameapp.myphotoswebbackend.services.IRequestService;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.IRequestPostUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RequestPostUseCase implements IRequestPostUseCase {

    @Autowired
    IRequestService service ;

    @Override
    public ResponseEntity<String> post(RequestDto request) {
        try{
            return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }
}
