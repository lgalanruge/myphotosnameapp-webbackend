package co.com.myphotosnameapp.MyPhotosWebBackend.usecases;

import co.com.myphotosnameapp.MyPhotosWebBackend.dtos.ImageSetDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IImageSetGetUseCase {

    ResponseEntity<List<ImageSetDto>> get(String id);


}
