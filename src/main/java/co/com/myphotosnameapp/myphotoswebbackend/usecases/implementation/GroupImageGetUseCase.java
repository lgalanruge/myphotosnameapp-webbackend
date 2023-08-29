package co.com.myphotosnameapp.myphotoswebbackend.usecases.implementation;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.GroupImageDto;
import co.com.myphotosnameapp.myphotoswebbackend.dtos.ImageSetDto;
import co.com.myphotosnameapp.myphotoswebbackend.services.IImageSetService;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.IGroupImageGetUseCase;
import co.com.myphotosnameapp.myphotoswebbackend.utilities.ImageProcessStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GroupImageGetUseCase implements IGroupImageGetUseCase {


    @Autowired
    IImageSetService service ;

    @Autowired
    SimpleDateFormat formatDate ;

    @Override
    public ResponseEntity<Set<GroupImageDto>> get(Map<String, String> allParams) {
        if(allParams == null || allParams.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

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
        else{
            Set<GroupImageDto> listMap =
                    list
                            .stream()
                            .map(value -> {
                                GroupImageDto groupImageDto = new GroupImageDto();
                                groupImageDto.setImageSourceId(value.getImageSourceId());
                                groupImageDto.setName(value.getName());
                                groupImageDto.setStatus(value.getStatus());
                                return groupImageDto;
                            })
                            .collect( Collectors.toSet() ) ;
            return ResponseEntity.ok(listMap);
        }

    }
}
