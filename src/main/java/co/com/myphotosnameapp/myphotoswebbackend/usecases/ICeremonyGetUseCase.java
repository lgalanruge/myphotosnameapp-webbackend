package co.com.myphotosnameapp.myphotoswebbackend.usecases;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.CeremonyDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ICeremonyGetUseCase {

    ResponseEntity<List<CeremonyDto>> get(Map<String, String> allParams);

}
