package co.com.myphotosnameapp.myphotoswebbackend.usecases.implementation;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.ImageSetDto;
import co.com.myphotosnameapp.myphotoswebbackend.services.IImageSetService;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.IImageSetPostUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ImageSetPostUseCase implements IImageSetPostUseCase {

    @Autowired
    IImageSetService service ;

    @Override
    public ResponseEntity<Map<ImageSetDto, String>> post(List<ImageSetDto> imageList){
        try {
            Map<ImageSetDto, String> maps = service.create(imageList);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

}
