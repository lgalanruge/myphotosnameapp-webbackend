package co.com.myphotosnameapp.myphotoswebbackend.dtos;

import co.com.myphotosnameapp.myphotoswebbackend.utilities.ImageStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class ImageDto implements Serializable {

    private String id;

    private String name;

    private String path;

    private String sourceDirectory;

    private ImageStatus status;

    @JsonIgnore
    private String createdBy;

    @JsonIgnore
    private LocalDateTime createdDate;

    @JsonIgnore
    private String modifiedBy;

    @JsonIgnore
    private LocalDateTime modifiedDate;

    @Column(name = "provider_id", length = 30)
    private String providerId;

    @Column(name = "customer_id", length = 30)
    private String customerId;

    @Column(name = "image_date", length = 30)
    private LocalDate imageDate ;


}
