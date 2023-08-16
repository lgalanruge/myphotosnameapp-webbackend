package co.com.myphotosnameapp.myphotoswebbackend.usecases;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.ImageSetDto;
import org.springframework.http.ResponseEntity;

public interface IImageSetPatchUseCase {

    ResponseEntity<ImageSetDto> patch(String id, ImageSetDto imageSet);

}
