package co.com.myphotosnameapp.myphotoswebbackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class OwnerDto implements Serializable {

    private String id;

    private String status;

    @JsonIgnore
    private String createdBy;

    @JsonIgnore
    private LocalDateTime createdDate;

    @JsonIgnore
    private String modifiedBy;

    @JsonIgnore
    private LocalDateTime modifiedDate;


    private PersonDto personId;

}
