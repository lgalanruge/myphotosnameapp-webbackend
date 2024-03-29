package co.com.myphotosnameapp.myphotoswebbackend.controllers;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.ImageSetDto;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.IImageSetGetUseCase;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.IImageSetPatchUseCase;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.IImageSetPostUseCase;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.IImageSetPutUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("imageset")
public class ImageSetController {

    @Autowired
    IImageSetGetUseCase getUseCase ;

    @Autowired
    IImageSetPutUseCase putUseCase ;

    @Autowired
    IImageSetPostUseCase postUseCase ;

    @Autowired
    IImageSetPatchUseCase patchUseCase ;

    @GetMapping("")
    public ResponseEntity<List<ImageSetDto>> get(@RequestParam Map<String, String> allParams){
        return getUseCase.get(allParams);
    }

    @PostMapping()
    public ResponseEntity<Map<ImageSetDto, String>> post(@RequestBody List<ImageSetDto> imageList){
        return postUseCase.post(imageList);
    }

    @PutMapping("")
    public ResponseEntity<Void> put(@RequestBody List<ImageSetDto> imageSet){
        return putUseCase.put(imageSet);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ImageSetDto> patch(@PathVariable String id, @RequestBody ImageSetDto imageSet){
        return patchUseCase.patch(id, imageSet);
    }






}
