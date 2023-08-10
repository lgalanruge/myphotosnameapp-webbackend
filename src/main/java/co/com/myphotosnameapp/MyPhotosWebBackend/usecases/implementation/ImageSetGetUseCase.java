package co.com.myphotosnameapp.MyPhotosWebBackend.usecases.implementation;

import co.com.myphotosnameapp.MyPhotosWebBackend.dtos.ImageSetDto;
import co.com.myphotosnameapp.MyPhotosWebBackend.services.IImageSetService;
import co.com.myphotosnameapp.MyPhotosWebBackend.usecases.IImageSetGetUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ImageSetGetUseCase implements IImageSetGetUseCase {

    @Autowired
    IImageSetService service ;

    @Override
    public ResponseEntity<List<ImageSetDto>> get(String id) {
        try{
            List<ImageSetDto> list = service.read(id);
            if(!list.isEmpty())
                return ResponseEntity.ok(list);
            else
                return ResponseEntity.notFound().build();
        }catch (Exception e){

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}
