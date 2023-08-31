package co.com.myphotosnameapp.myphotoswebbackend.controllers;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.AssignImageDto;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.IImageAssignationPostUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("assign")
public class ImageAssignationController {

    @Autowired
    IImageAssignationPostUseCase postUseCase ;

    @PostMapping("")
    public ResponseEntity<Void> post(@RequestBody AssignImageDto assing){
        return postUseCase.makeImageAssignation(assing);
    }

}
