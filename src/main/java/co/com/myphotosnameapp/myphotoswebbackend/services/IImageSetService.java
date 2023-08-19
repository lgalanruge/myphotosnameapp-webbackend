package co.com.myphotosnameapp.myphotoswebbackend.services;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.ImageSetDto;
import co.com.myphotosnameapp.myphotoswebbackend.entities.ImageSetEntity;
import jakarta.transaction.TransactionalException;

import java.util.List;
import java.util.Map;

public interface IImageSetService {

    List<ImageSetDto> read(String id);

    Map<ImageSetDto, String> create(List<ImageSetDto> imageList) throws TransactionalException;

    void update(String id, ImageSetDto input) throws TransactionalException;

    void updateAll(List<ImageSetDto> inputList) throws TransactionalException;

    List<ImageSetDto> searchImageSetDtos(ImageSetDto image);

}
