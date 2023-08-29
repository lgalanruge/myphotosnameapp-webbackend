package co.com.myphotosnameapp.myphotoswebbackend.services;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.ImageDto;

import java.util.List;
import java.util.Optional;

public interface IImageService {

    Optional<ImageDto> getById(String id);

    Optional<List<ImageDto>> getByName(String name);

    Optional<List<ImageDto>> getByStatus(String status);

    Optional<List<ImageDto>> getByCustomerId(String customerId);

    Optional<List<ImageDto>> getByProviderId(String providerId);

    Optional<List<ImageDto>> getBySourceDirectory(String sourceDirectory);



}
