package co.com.myphotosnameapp.myphotoswebbackend.controllers;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.GroupImageDto;
import co.com.myphotosnameapp.myphotoswebbackend.dtos.ImageSetDto;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.IGroupImageGetUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("groupimage")
public class GroupImageController {

    @Autowired
    IGroupImageGetUseCase useCase ;

    @GetMapping("")
    public ResponseEntity<Set<GroupImageDto>> get(@RequestParam Map<String, String> allParams){
        return useCase.get(allParams);
    }

}
