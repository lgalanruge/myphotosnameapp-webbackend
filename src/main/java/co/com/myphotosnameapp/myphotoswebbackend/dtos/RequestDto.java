package co.com.myphotosnameapp.myphotoswebbackend.dtos;

import co.com.myphotosnameapp.myphotoswebbackend.utilities.GenericStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.time.LocalDateTime;

@Data
public class RequestDto {

    private String id;

    private GenericStatus status;

    private String ceremonyId;

    private String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    private String modifiedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedDate;

    private String providerId;

    private String customerId;

    @Transient
    @JsonIgnore
    private int page ;

    @Transient
    @JsonIgnore
    private int size ;

}
