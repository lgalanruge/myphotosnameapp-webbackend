package co.com.myphotosnameapp.myphotoswebbackend.usecases.implementation;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.ImageSetDto;
import co.com.myphotosnameapp.myphotoswebbackend.services.IImageSetService;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.IImageSetPutUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ImageSetPutUseCase implements IImageSetPutUseCase {

    @Autowired
    IImageSetService service ;

    @Override
    public ResponseEntity<Void>put(List<ImageSetDto> imageSet){
        try {
            service.updateAll(imageSet);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
