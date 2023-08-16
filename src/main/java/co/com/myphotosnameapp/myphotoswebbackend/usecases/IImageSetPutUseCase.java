package co.com.myphotosnameapp.myphotoswebbackend.usecases;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.ImageSetDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IImageSetPutUseCase {
    ResponseEntity<ImageSetDto> put(List<ImageSetDto> imageSet);

}
