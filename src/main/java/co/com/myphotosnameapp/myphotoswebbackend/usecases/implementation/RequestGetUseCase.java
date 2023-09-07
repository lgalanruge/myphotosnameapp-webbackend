package co.com.myphotosnameapp.myphotoswebbackend.usecases.implementation;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.CeremonyDto;
import co.com.myphotosnameapp.myphotoswebbackend.dtos.RequestDto;
import co.com.myphotosnameapp.myphotoswebbackend.services.IRequestService;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.IRequestGetUseCase;
import co.com.myphotosnameapp.myphotoswebbackend.utilities.CeremonyStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RequestGetUseCase implements IRequestGetUseCase{

    @Autowired
    IRequestService service ;

    @Autowired
    DateTimeFormatter formatter ;

    @Override
    public ResponseEntity<List<RequestDto>> get(Map<String, String> allParams) {
        RequestDto requestDto = new RequestDto();
        if(allParams.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        requestDto.setCeremonyId(new CeremonyDto());

        allParams
                .forEach(
                        (key, value) -> {
                            switch (key) {
                                case ("id"):
                                    requestDto.setId(value);
                                    break;
                                case ("status"):
                                    requestDto.setStatus(CeremonyStatus.valueOf(value));
                                    break;
                                case ("createdBy"):
                                    requestDto.setCreatedBy(value);
                                    break;
                                case ("createdDate"):
                                    requestDto.setCreatedDate(LocalDateTime.now());
                                    break;
                                case ("providerId"):
                                    requestDto.setProviderId(value);
                                    break;
                                case ("customerId"):
                                    requestDto.setCustomerId(value);
                                    break;
                                case("page"):
                                    requestDto.setPage(Integer.valueOf(value));
                                    break;
                                case("size"):
                                    requestDto.setSize(Integer.valueOf(value));
                                    break;
                                case("ceremonyId"):
                                    requestDto.getCeremonyId().setId(value);
                                    break;
                                case("eventDate"):
                                    LocalDate ld = LocalDate.parse(value, formatter);
                                    requestDto.getCeremonyId().setEventDate(ld);
                                    break;
                                default:
                                    break;

                            }

                        }
                );

        List<RequestDto> list = service.read(requestDto);
        if(!list.isEmpty())
            return new ResponseEntity<>(list, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
