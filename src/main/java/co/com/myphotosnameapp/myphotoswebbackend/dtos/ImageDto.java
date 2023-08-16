package co.com.myphotosnameapp.myphotoswebbackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class ImageDto implements Serializable {

    private String id;

    private String name;

    private String path;

    private String sourceDirectory;

    private String status;

    @JsonIgnore
    private String createdBy;

    @JsonIgnore
    private LocalDateTime createdDate;

    @JsonIgnore
    private String modifiedBy;

    @JsonIgnore
    private LocalDateTime modifiedDate;

    @JsonIgnore
    private CompanyDto company;

    @JsonIgnore
    private OwnerDto owner;


}
