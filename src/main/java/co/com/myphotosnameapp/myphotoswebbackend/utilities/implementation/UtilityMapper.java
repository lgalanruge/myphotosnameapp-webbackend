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
    public ImageDto toDto(ImageEntity entity) {
        return MODEL_MAPPER.map(entity, ImageDto.class);
    }

    @Override
    public ImageSetDto toDto(ImageSetEntity entity) {
        return MODEL_MAPPER.map(entity, ImageSetDto.class);
    }


    @Override
    public PersonDto toDto(PersonEntity entity) {
        return MODEL_MAPPER.map(entity, PersonDto.class);
    }

    @Override
    public CeremonyDto toDto(CeremonyEntity entity) {
        return MODEL_MAPPER.map(entity, CeremonyDto.class);
    }

    @Override
    public RequestDto toDto(RequestEntity entity) {
        return MODEL_MAPPER.map(entity, RequestDto.class);
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
    public PersonEntity toEntity(PersonDto dto) {
        return MODEL_MAPPER.map(dto, PersonEntity.class);
    }

    @Override
    public CeremonyEntity toEntity(CeremonyDto dto) {
        return MODEL_MAPPER.map(dto, CeremonyEntity.class);
    }

    @Override
    public RequestEntity toEntity(RequestDto dto) {
        return MODEL_MAPPER.map(dto, RequestEntity.class);
    }

}
