package co.com.myphotosnameapp.myphotoswebbackend.usecases;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.ImageSetDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IImageSetGetUseCase {

    ResponseEntity<List<ImageSetDto>> get(Map<String, String> allParams);


}
