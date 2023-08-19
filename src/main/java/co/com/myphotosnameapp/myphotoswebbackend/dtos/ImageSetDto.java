package co.com.myphotosnameapp.myphotoswebbackend.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import java.text.SimpleDateFormat;
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

    private String status;

    private String createdBy;


    @JsonIgnore
    private LocalDateTime createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private transient java.util.Date creationDate;

    public Date getCreationDate(){
        ZoneId systemTimeZone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = createdDate.atZone(systemTimeZone) ;
        creationDate = Date.from(zonedDateTime.toInstant());
        return this.creationDate;
    }

    private String modifiedBy;

    @JsonIgnore
    private LocalDateTime modifiedDate;


}
