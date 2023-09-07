package co.com.myphotosnameapp.myphotoswebbackend.usecases.implementation;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.ImageSetDto;
import co.com.myphotosnameapp.myphotoswebbackend.services.IImageSetService;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.IImageSetGetUseCase;
import co.com.myphotosnameapp.myphotoswebbackend.utilities.ImageProcessStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ImageSetGetUseCase implements IImageSetGetUseCase {

    @Autowired
    IImageSetService service ;

    @Autowired
    SimpleDateFormat formatDate ;

    private ResponseEntity<List<ImageSetDto>> get(String id) {
        try{
            List<ImageSetDto> list = service.read(id);
            if(!list.isEmpty())
                return ResponseEntity.ok(list);
            else
                return ResponseEntity.notFound().build();
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<List<ImageSetDto>> get(Map<String, String> allParams) {
        if(allParams == null || allParams.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(allParams.containsKey("id")){
            return get(allParams.get("id"));
        }

        ImageSetDto dto = new ImageSetDto();
        allParams.forEach((key, value) -> {
            switch (key){
                case "id":
                    dto.setId(value);
                    break;
                case "imageSourceId":
                    dto.setImageSourceId(value);
                    break;
                case "imageDestinationId":
                    dto.setImageDestinationId(value);
                    break;
                case "name":
                    dto.setName(value);
                    break;
                case "status":
                    dto.setStatus(ImageProcessStatus.valueOf(value));
                    break;
                case "createdBy":
                    dto.setCreatedBy(value);
                    break;
                case "creationDate":
                    try {
                        log.info("value: {}" , value);
                        java.util.Date creationDate = formatDate.parse(value);

                        log.info("creationDate: {}" , creationDate);
                        LocalDateTime dateTime = creationDate.toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime();
                        log.info("dateTime: {}" , dateTime);
                        dto.setCreatedDate(dateTime);
                    }catch (Exception e){
                        log.error(e.getMessage(),e);
                    }
                    break;
                default:
                    break;
            }
        });
        log.info("params: {}" , dto);

        List<ImageSetDto> list = service.searchImageSetDtos(dto);
        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return ResponseEntity.ok(list);
    }
}
