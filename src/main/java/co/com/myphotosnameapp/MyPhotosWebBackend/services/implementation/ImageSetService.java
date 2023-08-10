package co.com.myphotosnameapp.MyPhotosWebBackend.services.implementation;

import co.com.myphotosnameapp.MyPhotosWebBackend.dtos.ImageSetDto;
import co.com.myphotosnameapp.MyPhotosWebBackend.entities.ImageSetEntity;
import co.com.myphotosnameapp.MyPhotosWebBackend.repositories.ImageSetRepository;
import co.com.myphotosnameapp.MyPhotosWebBackend.services.IImageSetService;
import jakarta.transaction.TransactionalException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ImageSetService implements IImageSetService {

    @Autowired
    ImageSetRepository repository ;

    @Autowired
    ModelMapper modelMapper ;


    @Override
    public List<ImageSetDto> read(String id) {
        List<ImageSetEntity> setList = repository.findByImageSourceId(id);
        if(setList.isEmpty())
            return Collections.emptyList();
        return setList
                .stream()
                .map(value -> toDto(value))
                .collect(Collectors.toList());

    }

    @Override
    public Map<ImageSetDto, String> create(List<ImageSetDto> input) throws TransactionalException {
        try {
            List<ImageSetEntity> entList = input
                    .stream()
                    .map(value -> {
                        ImageSetEntity entity = toEntity(value);
                        entity.setId("");
                        entity.setCreatedDate(LocalDateTime.now());
                        return entity;
                    })
                    .collect(Collectors.toList());

            repository.saveAll(entList);

            return entList
                    .stream()
                    .map(value -> toDto(value))
                    .collect(Collectors.toMap(Function.identity(),
                            value -> value.getId()));
        }catch (Exception e){
            throw new TransactionalException(e.getMessage(), e);
        }


    }

    @Override
    public void update(String id, ImageSetDto input) throws TransactionalException {

        Optional<ImageSetEntity> entityOptional = repository.findById(id);

        if(!entityOptional.isPresent()){
            String message = "Resource {0} not found!";
            MessageFormat mf = new MessageFormat(message);
            String output = mf.format(id);
            throw new TransactionalException(output, new IllegalArgumentException(output));
        }

        try {
            ImageSetEntity ent = toEntity(input);
            ent.setId(id);
            ent.setModifiedDate(LocalDateTime.now());
            repository.save(ent);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new TransactionalException(e.getMessage(), e);
        }

    }

    @Override
    public void updateAll(List<ImageSetDto> inputList) throws TransactionalException {

        try {
            List<ImageSetEntity> entList = inputList
                    .stream()
                    .map(value -> {
                        ImageSetEntity entity = toEntity(value);
                        entity.setId("");
                        entity.setCreatedDate(LocalDateTime.now());
                        return entity;
                    })
                    .collect(Collectors.toList());

            repository.saveAll(entList);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new TransactionalException(e.getMessage(), e);
        }

    }

    private ImageSetDto toDto(ImageSetEntity image){
        return modelMapper.map(image, ImageSetDto.class);
    }

    private ImageSetEntity toEntity(ImageSetDto image){
        return modelMapper.map(image, ImageSetEntity.class);
    }


}
