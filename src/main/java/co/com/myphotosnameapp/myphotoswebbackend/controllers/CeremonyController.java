package co.com.myphotosnameapp.myphotoswebbackend.controllers;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.CeremonyDto;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.ICeremonyGetUseCase;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.ICeremonyPatchUseCase;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.ICeremonyPostUseCase;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.ICeremonyPutUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("ceremony")
public class CeremonyController {

    @Autowired
    ICeremonyGetUseCase getUseCase ;

    @Autowired
    ICeremonyPatchUseCase patchUseCase ;

    @Autowired
    ICeremonyPostUseCase postUseCase ;

    @Autowired
    ICeremonyPutUseCase putUseCase ;

    @GetMapping("")
    public ResponseEntity<List<CeremonyDto>> get(@RequestParam Map<String, String> allParams) {
        return getUseCase.get(allParams);
    }

    @PostMapping("")
    public ResponseEntity<String> createCeremony(@RequestBody CeremonyDto ceremonyDTO) {
        return postUseCase.post(ceremonyDTO);
    }

    @PutMapping("")
    public ResponseEntity<Void> updateCeremony(@RequestBody CeremonyDto ceremonyDTO) {
        return putUseCase.put(ceremonyDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> partialUpdateCeremony(@PathVariable String id, @RequestBody CeremonyDto ceremonyDTO) {
        return patchUseCase.patch(id, ceremonyDTO);
    }
}
