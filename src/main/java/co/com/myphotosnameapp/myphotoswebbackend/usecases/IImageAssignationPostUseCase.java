package co.com.myphotosnameapp.myphotoswebbackend.usecases;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.AssignImageDto;
import org.springframework.http.ResponseEntity;

public interface IImageAssignationPostUseCase {


    ResponseEntity<Void> makeImageAssignation(AssignImageDto image );

}
