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

    @GetMapping("/{id}")
    public ResponseEntity<List<ImageSetDto>> get(@PathVariable String id){
        return getUseCase.get(id);
    }

    @PostMapping()
    public ResponseEntity<Map<ImageSetDto, String>> post(@RequestBody List<ImageSetDto> imageList){
        return postUseCase.post(imageList);
    }

    @PutMapping("")
    public ResponseEntity<ImageSetDto> put(@RequestBody List<ImageSetDto> imageSet){
        return putUseCase.put(imageSet);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ImageSetDto> patch(@PathVariable String id, @RequestBody ImageSetDto imageSet){
        return patchUseCase.patch(id, imageSet);
    }






}
