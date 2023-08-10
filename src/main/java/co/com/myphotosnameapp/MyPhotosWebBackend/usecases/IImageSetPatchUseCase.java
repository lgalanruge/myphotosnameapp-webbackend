package co.com.myphotosnameapp.MyPhotosWebBackend.usecases;

import co.com.myphotosnameapp.MyPhotosWebBackend.dtos.ImageSetDto;
import org.springframework.http.ResponseEntity;

public interface IImageSetPatchUseCase {

    ResponseEntity<ImageSetDto> patch(String id, ImageSetDto imageSet);

}
