package co.com.myphotosnameapp.myphotoswebbackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class PersonDto implements Serializable {

    private String id;

    private String account;

    private String firstName;

    private String lastName;

    private String phone;

    private String address;

    @JsonIgnore
    private String createdBy;

    @JsonIgnore
    private LocalDateTime createdDate;

    @JsonIgnore
    private String modifiedBy;

    @JsonIgnore
    private LocalDateTime modifiedDate;

    private String status;

}
