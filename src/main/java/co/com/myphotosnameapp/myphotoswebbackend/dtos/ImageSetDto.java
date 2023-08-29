package co.com.myphotosnameapp.myphotoswebbackend.dtos;

import co.com.myphotosnameapp.myphotoswebbackend.utilities.ImageProcessStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import org.springframework.data.annotation.Transient;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
public class ImageSetDto {

    private String id;

    private String imageSourceId;

    private String imageDestinationId;

    private String name;

    private ImageProcessStatus status;

    private String createdBy;


    @JsonIgnore
    private LocalDateTime createdDate;

    @Transient
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private java.util.Date creationDate;

    public Date getCreationDate(){
        if(createdDate == null)
            return new Date();

        ZoneId systemTimeZone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = createdDate.atZone(systemTimeZone) ;
        creationDate = Date.from(zonedDateTime.toInstant());
        return this.creationDate;
    }

    private String modifiedBy;

    @JsonIgnore
    private LocalDateTime modifiedDate;


}
