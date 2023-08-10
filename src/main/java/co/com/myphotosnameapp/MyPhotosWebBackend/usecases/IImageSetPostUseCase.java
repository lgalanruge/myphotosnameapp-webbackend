package co.com.myphotosnameapp.MyPhotosWebBackend.usecases;

import co.com.myphotosnameapp.MyPhotosWebBackend.dtos.ImageSetDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IImageSetPostUseCase {

    ResponseEntity<Map<String, String>> post(List<ImageSetDto> imageList);

}
