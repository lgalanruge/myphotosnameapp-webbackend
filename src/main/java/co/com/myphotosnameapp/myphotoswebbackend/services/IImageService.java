package co.com.myphotosnameapp.myphotoswebbackend.services;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.ImageDto;

import java.util.List;
import java.util.Optional;

public interface IImageService {

    public Optional<ImageDto> getById(String id);

    public Optional<List<ImageDto>> getByName(String name);

    public Optional<List<ImageDto>> getByStatus(String status);

    public Optional<List<ImageDto>> getByOwner(String owner);

    public Optional<List<ImageDto>> getByCompany(String company);

    public Optional<List<ImageDto>> getBySourceDirectory(String sourceDirectory);



}
