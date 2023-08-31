package co.com.myphotosnameapp.myphotoswebbackend.services.implementation;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.ImageSetDto;
import co.com.myphotosnameapp.myphotoswebbackend.entities.ImageSetEntity;
import co.com.myphotosnameapp.myphotoswebbackend.repositories.ImageSetRepository;
import co.com.myphotosnameapp.myphotoswebbackend.services.IImageSetService;
import co.com.myphotosnameapp.myphotoswebbackend.utilities.IUtilityMapper;
import co.com.myphotosnameapp.myphotoswebbackend.utilities.Utilities;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import jakarta.transaction.TransactionalException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

import java.util.stream.Collectors;

@Slf4j
@Service
public class ImageSetService implements IImageSetService {

    @Autowired
    ImageSetRepository repository ;

    @Autowired
    IUtilityMapper mapper ;

    @Autowired
    private EntityManager entityManager;


    @Override
    public List<ImageSetDto> read(String id) {
        List<ImageSetEntity> setList = repository.findByImageSourceId(id);
        if(setList.isEmpty())
            return Collections.emptyList();
        return setList
                .stream()
                .map(value -> mapper.toDto(value))
                .toList();

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
                    .toList();

            log.info("Entity list {}", entList);
            repository.saveAll(entList);

            Map<ImageSetDto, String> mapOutput = entList
                    .stream()
                    .map(value -> mapper.toDto(value))
                    .collect(Collectors.toMap(Function.identity(),
                            ImageSetDto::getId));
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
            previousEntity.setStatus(newEntity.getStatus());
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
                    .toList();

            repository.saveAll(entList);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new TransactionalException(e.getMessage(), e);
        }

    }

    @Override
    public List<ImageSetDto> searchImageSetDtos(ImageSetDto image) {
        ImageSetEntity entity = mapper.toEntity(image);
        List<ImageSetEntity> list = searchImageSetEntities(entity);
        if (list.isEmpty())
            return Collections.emptyList();
        return list
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    private List<ImageSetEntity> searchImageSetEntities(ImageSetEntity image) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ImageSetEntity> query = criteriaBuilder.createQuery(ImageSetEntity.class);
        Root<ImageSetEntity> root = query.from(ImageSetEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        if (image.getId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("id"), image.getId()));
        }
        if (image.getImageSourceId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("imageSourceId"), image.getImageSourceId()));
        }
        if (image.getImageDestinationId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("imageDestinationId"), image.getImageDestinationId()));
        }
        if (image.getStatus() != null) {
            predicates.add(criteriaBuilder.equal(root.get("status"), image.getStatus()));
        }
        if (image.getCreatedBy() != null) {
            predicates.add(criteriaBuilder.equal(root.get("createdBy"), image.getCreatedBy()));
        }
        if (image.getCreatedDate() != null) {
            LocalDateTime finishDate = image.getCreatedDate().plusDays(1);
            predicates.add(criteriaBuilder.between(root.get("createdDate"), image.getCreatedDate(), finishDate));
        }
        if (image.getModifiedBy() != null) {
            predicates.add(criteriaBuilder.equal(root.get("modifiedBy"), image.getModifiedBy()));
        }
        if (image.getModifiedDate()  != null ) {
            predicates.add(criteriaBuilder.between(root.get("modifiedDate"), image.getModifiedDate(), LocalDateTime.now()));
        }
        if (image.getName() != null) {
            predicates.add(criteriaBuilder.equal(root.get("name"), image.getName()));
        }

        query.select(root).where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(query).getResultList();
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
