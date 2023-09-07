package co.com.myphotosnameapp.myphotoswebbackend.services.implementation;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.ImageDto;
import co.com.myphotosnameapp.myphotoswebbackend.entities.ImageEntity;
import co.com.myphotosnameapp.myphotoswebbackend.repositories.ImageRepository;
import co.com.myphotosnameapp.myphotoswebbackend.services.IImageService;
import co.com.myphotosnameapp.myphotoswebbackend.utilities.IUtilityMapper;
import co.com.myphotosnameapp.myphotoswebbackend.utilities.Utilities;
import jakarta.transaction.Transactional;
import jakarta.transaction.TransactionalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ImageService implements IImageService {

    @Autowired
    ImageRepository repository ;

    @Autowired
    IUtilityMapper mapper ;

    private String providerId = Utilities.PROVIDER_ID;

    @Override
    public Optional<ImageDto> getById(String id) {
        Optional<ImageEntity> image = repository.findById(id);
        if(image.isEmpty())
            return Optional.empty();
        ImageDto dto = mapper.toDto(image.get());
        return Optional.of(dto);
    }

    @Override
    public Optional<List<ImageDto>> getByName(String name) {
        List<ImageEntity> image = repository.findByNameAndProviderId(name, providerId);
        if(image.isEmpty())
            return Optional.empty();
        List<ImageDto> dto = image
                .stream()
                .map(value -> mapper.toDto(value))
                .toList();
        return Optional.of(dto);
    }

    @Override
    public Optional<List<ImageDto>> getByStatus(String status) {
        List<ImageEntity> image = repository.findByStatusAndProviderId (status, providerId);
        if(image.isEmpty())
            return Optional.empty();
        List<ImageDto> dto = image
                .stream()
                .map(value -> mapper.toDto(value))
                .toList();
        return Optional.of(dto);
    }

    @Override
    public Optional<List<ImageDto>> getByCustomerId(String customerId) {

        List<ImageEntity> image = repository.findByCustomerId(customerId);
        if(image.isEmpty())
            return Optional.empty();
        List<ImageDto> dto = image
                .stream()
                .map(value -> mapper.toDto(value))
                .toList();
        return Optional.of(dto);
    }

    @Override
    public Optional<List<ImageDto>> getByProviderId(String providerId) {

        List<ImageEntity> image = repository.findByProviderId(providerId);
        if(image.isEmpty())
            return Optional.empty();
        List<ImageDto> dto = image
                .stream()
                .map(value -> mapper.toDto(value))
                .toList();
        return Optional.of(dto);
    }

    @Override
    public Optional<List<ImageDto>> getBySourceDirectory(String sourceDirectory) {
        List<ImageEntity> image = repository.findByProviderIdAndSourceDirectory (providerId, sourceDirectory);
        if(image.isEmpty())
            return Optional.empty();
        List<ImageDto> dto = image
                .stream()
                .map(value -> mapper.toDto(value))
                .toList();
        return Optional.of(dto);
    }

    @Transactional
    @Override
    public void update(ImageDto imageDto)  throws TransactionalException {
        try {
            ImageEntity entity = mapper.toEntity(imageDto);
            repository.save(entity);
        }catch (Exception e){
            throw new TransactionalException(e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public void updateAll(List<ImageDto> images) throws TransactionalException {
        try {
            List<ImageEntity> imageEntities = images
                    .stream()
                    .map(value -> mapper.toEntity(value))
                    .toList();

            log.info("image list: {}", images);
            repository.saveAll(imageEntities);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new TransactionalException(e.getMessage(), e);
        }

    }


}
