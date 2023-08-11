package co.com.myphotosnameapp.myphotoswebbackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImageSetDto {

    private String id;

    private String imageSourceId;

    private String imageDestinationId;

    private String name;

    private String status;

    private String createdBy;

    @JsonIgnore
    private LocalDateTime createdDate;

    private String modifiedBy;

    @JsonIgnore
    private LocalDateTime modifiedDate;


}
