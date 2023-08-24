package co.com.myphotosnameapp.myphotoswebbackend.usecases.implementation;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.ImageSetDto;
import co.com.myphotosnameapp.myphotoswebbackend.services.IImageSetService;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.IImageSetPatchUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ImageSetPatchUseCase implements IImageSetPatchUseCase {

    @Autowired
    IImageSetService service ;

    @Override
    public ResponseEntity<ImageSetDto> patch(String id, ImageSetDto imageSet){
        try{
            service.update(id, imageSet);

            // HttpHeaders responseHeaders = new HttpHeaders();
            // responseHeaders.set("Access-Control-Allow-Origin", "*");

            return ResponseEntity
                    .accepted()
                    // .headers(responseHeaders)
                    .build();

        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

}
