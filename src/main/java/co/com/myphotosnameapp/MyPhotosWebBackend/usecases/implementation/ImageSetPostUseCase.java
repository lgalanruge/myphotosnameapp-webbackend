package co.com.myphotosnameapp.MyPhotosWebBackend.usecases.implementation;

import co.com.myphotosnameapp.MyPhotosWebBackend.dtos.ImageSetDto;
import co.com.myphotosnameapp.MyPhotosWebBackend.services.IImageSetService;
import co.com.myphotosnameapp.MyPhotosWebBackend.usecases.IImageSetPostUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ImageSetPostUseCase implements IImageSetPostUseCase {

    @Autowired
    IImageSetService service ;

    @Override
    public ResponseEntity<Map<String, String>> post(List<ImageSetDto> imageList){
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
