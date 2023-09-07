package co.com.myphotosnameapp.myphotoswebbackend.dtos;

import co.com.myphotosnameapp.myphotoswebbackend.utilities.CeremonyStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class CeremonyDto {

    private String id;

    private String title;

    private String description;

    private CeremonyStatus status;

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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDate ;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime eventStartTime ;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime eventFinishTime ;

}

