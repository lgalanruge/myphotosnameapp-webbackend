package co.com.myphotosnameapp.MyPhotosWebBackend.services;

import co.com.myphotosnameapp.MyPhotosWebBackend.dtos.ImageSetDto;
import jakarta.transaction.TransactionalException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IImageSetService {

    List<ImageSetDto> read(String id);

    Map<ImageSetDto, String> create(List<ImageSetDto> imageList) throws TransactionalException;

    void update(String id, ImageSetDto input) throws TransactionalException;

    void updateAll(List<ImageSetDto> inputList) throws TransactionalException;


}
