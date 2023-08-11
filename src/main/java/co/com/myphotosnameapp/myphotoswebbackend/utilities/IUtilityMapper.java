package co.com.myphotosnameapp.myphotoswebbackend.utilities;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.*;
import co.com.myphotosnameapp.myphotoswebbackend.entities.*;

public interface IUtilityMapper {

    CompanyDto toDto(CompanyEntity entity);
    ImageDto toDto(ImageEntity entity);
    ImageSetDto toDto(ImageSetEntity entity);
    OwnerDto toDto(OwnerEntity entity);
    PersonDto toDto(PersonEntity entity);

    CompanyEntity  toEntity(CompanyDto dto);
    ImageEntity  toEntity(ImageDto dto);
    ImageSetEntity  toEntity(ImageSetDto dto);
    OwnerEntity  toEntity(OwnerDto dto);
    PersonEntity  toEntity(PersonDto dto);

}
