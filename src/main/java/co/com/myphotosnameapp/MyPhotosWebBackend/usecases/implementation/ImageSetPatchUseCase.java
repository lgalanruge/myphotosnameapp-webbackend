package co.com.myphotosnameapp.MyPhotosWebBackend.usecases.implementation;

import co.com.myphotosnameapp.MyPhotosWebBackend.dtos.ImageSetDto;
import co.com.myphotosnameapp.MyPhotosWebBackend.services.IImageSetService;
import co.com.myphotosnameapp.MyPhotosWebBackend.usecases.IImageSetPatchUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
public class ImageSetPatchUseCase implements IImageSetPatchUseCase {

    @Autowired
    IImageSetService service ;

    @Override
    public ResponseEntity<ImageSetDto> patch(String id, ImageSetDto imageSet){
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
