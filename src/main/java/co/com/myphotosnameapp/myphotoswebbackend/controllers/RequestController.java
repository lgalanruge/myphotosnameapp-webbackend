package co.com.myphotosnameapp.myphotoswebbackend.controllers;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.RequestDto;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.IRequestGetUseCase;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.IRequestPatchUseCase;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.IRequestPostUseCase;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.IRequestPutUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("request")
public class RequestController {

    @Autowired
    IRequestGetUseCase getUseCase ;

    @Autowired
    IRequestPatchUseCase patchUseCase ;

    @Autowired
    IRequestPostUseCase postUseCase ;

    @Autowired
    IRequestPutUseCase putUseCase ;

    @GetMapping("")
    public ResponseEntity<List<RequestDto>>  get(@RequestParam Map<String, String> allParams) {
        return getUseCase.get(allParams);
    }

    @PostMapping("")
    public ResponseEntity<String> createRequest(@RequestBody RequestDto requestDTO) {
        return postUseCase.post(requestDTO);
    }

    @PutMapping()
    public ResponseEntity<Void> updateRequest(@RequestBody RequestDto requestDTO) {
        return putUseCase.put(requestDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> partialUpdateRequest(@PathVariable String id , @RequestBody RequestDto requestDTO) {
        return patchUseCase.patch(id, requestDTO);
    }
}

