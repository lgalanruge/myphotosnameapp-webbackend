package co.com.myphotosnameapp.myphotoswebbackend.services.implementation;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.CompanyDto;
import co.com.myphotosnameapp.myphotoswebbackend.dtos.ImageDto;
import co.com.myphotosnameapp.myphotoswebbackend.dtos.OwnerDto;
import co.com.myphotosnameapp.myphotoswebbackend.entities.CompanyEntity;
import co.com.myphotosnameapp.myphotoswebbackend.entities.ImageEntity;
import co.com.myphotosnameapp.myphotoswebbackend.entities.OwnerEntity;
import co.com.myphotosnameapp.myphotoswebbackend.repositories.ImageRepository;
import co.com.myphotosnameapp.myphotoswebbackend.services.IImageService;
import co.com.myphotosnameapp.myphotoswebbackend.utilities.IUtilityMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private CompanyDto getCompany ;

    @Autowired
    private OwnerDto getOwner ;



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
        List<ImageEntity> image = repository.findByNameAndCompany(name, mapper.toEntity(getCompany));
        if(image.isEmpty())
            return Optional.empty();
        List<ImageDto> dto = image
                .stream()
                .map(value -> mapper.toDto(value))
                .collect(Collectors.toList());
        return Optional.of(dto);
    }

    @Override
    public Optional<List<ImageDto>> getByStatus(String status) {
        List<ImageEntity> image = repository.findByStatusAndCompany (status, mapper.toEntity(getCompany));
        if(image.isEmpty())
            return Optional.empty();
        List<ImageDto> dto = image
                .stream()
                .map(value -> mapper.toDto(value))
                .collect(Collectors.toList());
        return Optional.of(dto);
    }

    @Override
    public Optional<List<ImageDto>> getByOwner(String owner) {
        OwnerEntity own = new OwnerEntity();
        own.setId(owner);
        List<ImageEntity> image = repository.findByOwner(own);
        if(image.isEmpty())
            return Optional.empty();
        List<ImageDto> dto = image
                .stream()
                .map(value -> mapper.toDto(value))
                .collect(Collectors.toList());
        return Optional.of(dto);
    }

    @Override
    public Optional<List<ImageDto>> getByCompany(String company) {
        CompanyEntity comp = new CompanyEntity();
        comp.setId(company);
        List<ImageEntity> image = repository.findByCompany(comp);
        if(image.isEmpty())
            return Optional.empty();
        List<ImageDto> dto = image
                .stream()
                .map(value -> mapper.toDto(value))
                .collect(Collectors.toList());
        return Optional.of(dto);
    }

    @Override
    public Optional<List<ImageDto>> getBySourceDirectory(String sourceDirectory) {
        List<ImageEntity> image = repository.findByCompanyAndSourceDirectory (mapper.toEntity(getCompany), sourceDirectory);
        if(image.isEmpty())
            return Optional.empty();
        List<ImageDto> dto = image
                .stream()
                .map(value -> mapper.toDto(value))
                .collect(Collectors.toList());
        return Optional.of(dto);
    }



}
