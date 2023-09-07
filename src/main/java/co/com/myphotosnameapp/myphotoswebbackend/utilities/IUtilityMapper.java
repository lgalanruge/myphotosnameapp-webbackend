package co.com.myphotosnameapp.myphotoswebbackend.utilities;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.*;
import co.com.myphotosnameapp.myphotoswebbackend.entities.*;

public interface IUtilityMapper {


    ImageDto toDto(ImageEntity entity);
    ImageSetDto toDto(ImageSetEntity entity);

    PersonDto toDto(PersonEntity entity);

    CeremonyDto toDto(CeremonyEntity entity);

    RequestDto toDto(RequestEntity entity);

    ImageEntity  toEntity(ImageDto dto);
    ImageSetEntity  toEntity(ImageSetDto dto);

    PersonEntity  toEntity(PersonDto dto);

    CeremonyEntity  toEntity(CeremonyDto dto);

    RequestEntity  toEntity(RequestDto dto);


}
