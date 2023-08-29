package co.com.myphotosnameapp.myphotoswebbackend.usecases;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.RequestDto;
import org.springframework.http.ResponseEntity;

public interface IRequestPutUseCase {

    ResponseEntity put(RequestDto request);


}
