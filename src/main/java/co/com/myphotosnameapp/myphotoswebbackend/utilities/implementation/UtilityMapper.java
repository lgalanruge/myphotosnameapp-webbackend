package co.com.myphotosnameapp.myphotoswebbackend.utilities.implementation;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.*;
import co.com.myphotosnameapp.myphotoswebbackend.entities.*;
import co.com.myphotosnameapp.myphotoswebbackend.utilities.IUtilityMapper;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Component;

@Component
public class UtilityMapper implements IUtilityMapper {

    private static final ModelMapper MODEL_MAPPER  = new ModelMapper();

    @Override
    public CompanyDto toDto(CompanyEntity entity) {
        return MODEL_MAPPER.map(entity, CompanyDto.class);
    }

    @Override
    public ImageDto toDto(ImageEntity entity) {
        return MODEL_MAPPER.map(entity, ImageDto.class);
    }

    @Override
    public ImageSetDto toDto(ImageSetEntity entity) {
        return MODEL_MAPPER.map(entity, ImageSetDto.class);
    }

    @Override
    public OwnerDto toDto(OwnerEntity entity) {
        return MODEL_MAPPER.map(entity, OwnerDto.class);
    }

    @Override
    public PersonDto toDto(PersonEntity entity) {
        return MODEL_MAPPER.map(entity, PersonDto.class);
    }

    @Override
    public CompanyEntity toEntity(CompanyDto dto) {
        return MODEL_MAPPER.map(dto, CompanyEntity.class);
    }

    @Override
    public ImageEntity toEntity(ImageDto dto) {
        return MODEL_MAPPER.map(dto, ImageEntity.class);
    }

    @Override
    public ImageSetEntity toEntity(ImageSetDto dto) {
        return MODEL_MAPPER.map(dto, ImageSetEntity.class);
    }

    @Override
    public OwnerEntity toEntity(OwnerDto dto) {
        return MODEL_MAPPER.map(dto, OwnerEntity.class);
    }

    @Override
    public PersonEntity toEntity(PersonDto dto) {
        return MODEL_MAPPER.map(dto, PersonEntity.class);
    }

}
