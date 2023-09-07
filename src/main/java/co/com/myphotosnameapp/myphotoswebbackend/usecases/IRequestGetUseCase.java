package co.com.myphotosnameapp.myphotoswebbackend.usecases;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.RequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IRequestGetUseCase {

    ResponseEntity<List<RequestDto>> get(Map<String, String> allParams);


}
