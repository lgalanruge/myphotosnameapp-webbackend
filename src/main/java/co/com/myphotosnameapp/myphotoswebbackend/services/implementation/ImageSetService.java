package co.com.myphotosnameapp.myphotoswebbackend.services.implementation;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.ImageSetDto;
import co.com.myphotosnameapp.myphotoswebbackend.entities.ImageSetEntity;
import co.com.myphotosnameapp.myphotoswebbackend.repositories.ImageSetRepository;
import co.com.myphotosnameapp.myphotoswebbackend.services.IImageSetService;
import co.com.myphotosnameapp.myphotoswebbackend.utilities.IUtilityMapper;
import co.com.myphotosnameapp.myphotoswebbackend.utilities.Utilities;
import jakarta.transaction.Transactional;
import jakarta.transaction.TransactionalException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    IUtilityMapper mapper ;


    @Override
    public List<ImageSetDto> read(String id) {
        List<ImageSetEntity> setList = repository.findByImageSourceId(id);
        if(setList.isEmpty())
            return Collections.emptyList();
        return setList
                .stream()
                .map(value -> mapper.toDto(value))
                .collect(Collectors.toList());

    }

    @Transactional
    @Override
    public Map<ImageSetDto, String> create(List<ImageSetDto> input) throws TransactionalException {
        try {
            log.info("Input list {}", input);
            List<ImageSetEntity> entList = input
                    .stream()
                    .map(value -> {
                        ImageSetEntity entity = mapper.toEntity(value);
                        entity.setId(Utilities.getId());
                        entity.setCreatedDate(LocalDateTime.now());
                        return entity;
                    })
                    .collect(Collectors.toList());

            log.info("Entity list {}", entList);
            repository.saveAll(entList);

            Map<ImageSetDto, String> mapOutput = entList
                    .stream()
                    .map(value -> mapper.toDto(value))
                    .collect(Collectors.toMap(Function.identity(),
                            value -> value.getId()));
            log.info("DTO list {}", mapOutput);

            return mapOutput;
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new TransactionalException(e.getMessage(), e);
        }


    }

    @Transactional
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
            ImageSetEntity newEntity = mapper.toEntity(input);
            ImageSetEntity previousEntity = entityOptional.get();

            previousEntity.setModifiedDate(LocalDateTime.now());

            previousEntity.setName(getRealStringValue(previousEntity.getName(), newEntity.getName()));
            previousEntity.setStatus(getRealStringValue(previousEntity.getStatus(), newEntity.getStatus()));
            previousEntity.setImageSourceId(getRealStringValue(previousEntity.getImageSourceId(), newEntity.getImageSourceId()));
            previousEntity.setImageDestinationId(getRealStringValue(previousEntity.getImageDestinationId(), newEntity.getImageDestinationId()));

            repository.save(previousEntity);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new TransactionalException(e.getMessage(), e);
        }

    }

    @Transactional
    @Override
    public void updateAll(List<ImageSetDto> inputList) throws TransactionalException {

        try {
            List<ImageSetEntity> entList = inputList
                    .stream()
                    .map(value -> {
                        ImageSetEntity entity = mapper.toEntity(value);
                        entity.setCreatedDate(LocalDateTime.now());
                        entity.setModifiedDate(LocalDateTime.now());
                        return entity;
                    })
                    .collect(Collectors.toList());

            repository.saveAll(entList);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new TransactionalException(e.getMessage(), e);
        }

    }

    private String getRealStringValue(String previousValue, String newValue){
        if(newValue == null){
            return previousValue;
        }
        if(newValue.trim().isEmpty()){
            return previousValue;
        }
        return newValue;
    }


}
