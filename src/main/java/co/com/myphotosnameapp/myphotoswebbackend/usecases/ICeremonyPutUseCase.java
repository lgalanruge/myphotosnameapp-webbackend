package co.com.myphotosnameapp.myphotoswebbackend.usecases;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.CeremonyDto;
import org.springframework.http.ResponseEntity;

public interface ICeremonyPutUseCase {

    ResponseEntity<String> put(CeremonyDto ceremonyDto);

}
