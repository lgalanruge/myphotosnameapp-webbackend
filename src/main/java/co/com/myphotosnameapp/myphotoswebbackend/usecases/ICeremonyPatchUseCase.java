package co.com.myphotosnameapp.myphotoswebbackend.usecases;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.CeremonyDto;
import org.springframework.http.ResponseEntity;

public interface ICeremonyPatchUseCase {

    ResponseEntity<Void> patch (String id, CeremonyDto ceremonyDto);

}
