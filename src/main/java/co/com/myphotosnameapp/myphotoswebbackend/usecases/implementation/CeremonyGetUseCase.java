package co.com.myphotosnameapp.myphotoswebbackend.usecases.implementation;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.CeremonyDto;
import co.com.myphotosnameapp.myphotoswebbackend.services.ICeremonyService;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.ICeremonyGetUseCase;
import co.com.myphotosnameapp.myphotoswebbackend.utilities.CeremonyStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CeremonyGetUseCase implements ICeremonyGetUseCase {

    @Autowired
    ICeremonyService service;

    @Override
    public ResponseEntity<List<CeremonyDto>> get(Map<String, String> allParams) {
        CeremonyDto ceremonyDto = new CeremonyDto();
        allParams
                .forEach(
                        (key, value) -> {
                            switch (key) {
                                case ("id"):
                                    ceremonyDto.setId(value);
                                    break;
                                case ("title"):
                                    ceremonyDto.setTitle(value);
                                    break;
                                case ("status"):
                                    ceremonyDto.setStatus(CeremonyStatus.valueOf(value));
                                    break;
                                case ("createdBy"):
                                    ceremonyDto.setCreatedBy(value);
                                    break;
                                case ("createdDate"):
                                    ceremonyDto.setCreatedDate(LocalDateTime.now());
                                    break;
                                case ("provider_id"):
                                    ceremonyDto.setProviderId(value);
                                    break;
                                case ("customer_id"):
                                    ceremonyDto.setCustomerId(value);
                                    break;
                                case("page"):
                                    ceremonyDto.setPage(Integer.valueOf(value));
                                    break;
                                case("size"):
                                    ceremonyDto.setSize(Integer.valueOf(value));
                                    break;
                                default:
                                    break;

                            }

                        }
                );

        List<CeremonyDto> list = service.read(ceremonyDto);
        if(!list.isEmpty())
            return new ResponseEntity<>(list, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
