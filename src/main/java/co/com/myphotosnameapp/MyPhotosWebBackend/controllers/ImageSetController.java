package co.com.myphotosnameapp.MyPhotosWebBackend.controllers;

import co.com.myphotosnameapp.MyPhotosWebBackend.dtos.ImageSetDto;
import co.com.myphotosnameapp.MyPhotosWebBackend.usecases.IImageSetGetUseCase;
import co.com.myphotosnameapp.MyPhotosWebBackend.usecases.IImageSetPatchUseCase;
import co.com.myphotosnameapp.MyPhotosWebBackend.usecases.IImageSetPostUseCase;
import co.com.myphotosnameapp.MyPhotosWebBackend.usecases.IImageSetPutUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<Map<String, String>> post(@RequestBody List<ImageSetDto> imageList){
        return postUseCase.post(imageList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImageSetDto> put(@PathVariable String id, @RequestBody ImageSetDto imageSet){
        return putUseCase.put(id, imageSet);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ImageSetDto> patch(@PathVariable String id, @RequestBody ImageSetDto imageSet){
        return patchUseCase.patch(id, imageSet);
    }






}
