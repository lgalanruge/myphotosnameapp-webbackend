package co.com.myphotosnameapp.MyPhotosWebBackend.usecases;

import co.com.myphotosnameapp.MyPhotosWebBackend.dtos.ImageSetDto;
import org.springframework.http.ResponseEntity;

public interface IImageSetPutUseCase {
    ResponseEntity<ImageSetDto> put(String id, ImageSetDto imageSet);

}
