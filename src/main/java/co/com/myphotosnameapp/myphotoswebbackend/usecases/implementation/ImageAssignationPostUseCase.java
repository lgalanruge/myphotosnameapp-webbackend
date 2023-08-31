package co.com.myphotosnameapp.myphotoswebbackend.usecases.implementation;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.AssignImageDto;
import co.com.myphotosnameapp.myphotoswebbackend.dtos.ImageDto;
import co.com.myphotosnameapp.myphotoswebbackend.dtos.ImageSetDto;
import co.com.myphotosnameapp.myphotoswebbackend.dtos.RequestDto;
import co.com.myphotosnameapp.myphotoswebbackend.services.IImageService;
import co.com.myphotosnameapp.myphotoswebbackend.services.IImageSetService;
import co.com.myphotosnameapp.myphotoswebbackend.services.IRequestService;
import co.com.myphotosnameapp.myphotoswebbackend.services.implementation.ImageSetService;
import co.com.myphotosnameapp.myphotoswebbackend.usecases.IImageAssignationPostUseCase;
import co.com.myphotosnameapp.myphotoswebbackend.utilities.CeremonyStatus;
import co.com.myphotosnameapp.myphotoswebbackend.utilities.ImageProcessStatus;
import co.com.myphotosnameapp.myphotoswebbackend.utilities.ImageStatus;
import co.com.myphotosnameapp.myphotoswebbackend.utilities.Utilities;
import jakarta.transaction.Transactional;
import jakarta.transaction.TransactionalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@Service
public class ImageAssignationPostUseCase implements IImageAssignationPostUseCase {

    @Autowired
    IImageService imageService;

    @Autowired
    IImageSetService imageSetService;

    @Autowired
    IRequestService requestService;

    String providerId = Utilities.PROVIDER_ID;

    Consumer<ImageSetDto> updateImageSetConsumerFunctional =
            value -> {
                value.setStatus(ImageProcessStatus.ASSIGNED);
                value.setModifiedDate(LocalDateTime.now());
                value.setModifiedBy(providerId);
            };

    BiFunction<ImageSetDto, RequestDto, ImageDto> convertImageSetToImageFunction =
            (imageSet, requestDto) -> {
                ImageDto newImage = new ImageDto();
                Optional<ImageDto> imageOpt = imageService.getById(imageSet.getImageDestinationId());
                if (imageOpt.isPresent()) {
                    newImage = imageOpt.get();
                } else {
                    newImage.setId(imageSet.getId());
                    newImage.setImageDate(LocalDate.now());
                    newImage.setCreatedDate(LocalDateTime.now());
                    newImage.setCreatedBy(providerId);
                }
                newImage.setCustomerId(requestDto.getCustomerId());
                newImage.setStatus(ImageStatus.ASSIGNED);
                newImage.setModifiedDate(LocalDateTime.now());
                newImage.setModifiedBy(providerId);
                return newImage;

            };

    @Override
    public ResponseEntity<Void> makeImageAssignation(AssignImageDto image) {
        try {
            makeAssignation(image);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Transactional
    private void makeAssignation(AssignImageDto assignedImage) throws TransactionalException {

        try {

            log.info("Start process: {}", assignedImage);
            Optional<ImageDto> isImage = imageService.getById(assignedImage.getImageId());
            if (!isImage.isPresent()) {
                throw new IllegalArgumentException("Image does not exist!");
            }
            log.info("isImage: {}", isImage.get());

            Optional<RequestDto> isRequest = requestService.read(assignedImage.getRequestId());
            if (!isRequest.isPresent()) {
                throw new IllegalArgumentException("Request does not exist!");
            }
            log.info("isRequest: {}", isRequest.get());

            log.info("search image association. ");
            ImageSetDto imageSetDto = new ImageSetDto();
            imageSetDto.setImageSourceId(assignedImage.getImageId());
            imageSetDto.setStatus(ImageProcessStatus.ACCEPTED);

            List<ImageSetDto> imageSetDtoList = imageSetService.searchImageSetDtos(imageSetDto);
            log.info("these are image association: {} ", imageSetDtoList);
            imageSetDtoList
                    .stream()
                    .forEach(updateImageSetConsumerFunctional);

            log.info("these are new image association {} the new state is {}", imageSetDtoList, ImageProcessStatus.ASSIGNED);
            imageSetService.updateAll(imageSetDtoList);


            List<ImageDto> images = imageSetDtoList
                    .stream()
                    .map(value -> convertImageSetToImageFunction.apply(value, isRequest.get()))
                    .toList();

            log.info("image list: {} without parent ", images);
            ImageDto parentImage = getAcceptedImage(isImage.get(), isRequest.get());

            log.info("update parent Image {} ", parentImage);
            imageService.update(parentImage);

            log.info("update children images. list: {} ", images);
            imageService.updateAll(images);

            log.info("Updating request");
            RequestDto requestDto = isRequest.get();
            requestDto.setStatus(CeremonyStatus.DONE);
            requestDto.setModifiedDate(LocalDateTime.now());
            requestDto.setModifiedBy(providerId);
            log.info("Ready request: {}", requestDto);
            requestService.update(requestDto);

            // Search Orphan images to clean because they were proceded
            log.info("Clean orphan images");
            List<ImageSetDto> cleanImageList = new ArrayList<>();
            imageSetDtoList
                    .stream()
                    .forEach(value -> {
                        List<ImageSetDto> assignationList = imageSetService.read(value.getImageDestinationId());
                        assignationList.forEach(cleanImageList::add);
                    });
            log.info("Orphan images: {} ", imageSetDtoList);

            cleanImageList
                    .stream()
                    .forEach(updateImageSetConsumerFunctional);

            log.info("these are new image association {} the new state is {}", imageSetDtoList, ImageProcessStatus.ASSIGNED);
            imageSetService.updateAll(cleanImageList);


        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw new TransactionalException(e.getMessage(), e);
        }

    }

    private ImageDto getAcceptedImage(ImageDto imageDto, RequestDto requestDto) {
        ImageDto parentImage = new ImageDto();
        parentImage.setImageDate(imageDto.getImageDate());
        parentImage.setId(imageDto.getId());
        parentImage.setCreatedDate(imageDto.getCreatedDate());
        parentImage.setCreatedBy(imageDto.getCreatedBy());
        parentImage.setName(imageDto.getName());
        parentImage.setPath(imageDto.getPath());
        parentImage.setProviderId(imageDto.getProviderId());
        parentImage.setSourceDirectory(imageDto.getSourceDirectory());
        parentImage.setStatus(ImageStatus.ASSIGNED);
        parentImage.setCustomerId(requestDto.getCustomerId());
        parentImage.setModifiedDate(LocalDateTime.now());
        parentImage.setModifiedBy(providerId);
        return parentImage;
    }


}
