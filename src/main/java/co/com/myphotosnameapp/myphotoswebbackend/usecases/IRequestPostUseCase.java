package co.com.myphotosnameapp.myphotoswebbackend.usecases;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.RequestDto;
import org.springframework.http.ResponseEntity;

public interface IRequestPostUseCase {

    ResponseEntity<String> post(RequestDto request);


}
