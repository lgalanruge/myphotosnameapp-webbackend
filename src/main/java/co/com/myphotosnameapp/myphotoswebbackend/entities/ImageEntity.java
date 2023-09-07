package co.com.myphotosnameapp.myphotoswebbackend.entities;

import co.com.myphotosnameapp.myphotoswebbackend.utilities.ImageStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "IMAGE")
public class ImageEntity implements Serializable {
    @Id
    @Column(name = "ID", length = 30)
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PATH", length = 300)
    private String path;

    @Column(name = "SOURCE_DIRECTORY", length = 200)
    private String sourceDirectory;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 50)
    private ImageStatus status;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdDate;

    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    @Column(name = "MODIFIED_DATE")
    private LocalDateTime modifiedDate;

    @Column(name = "provider_id", length = 30)
    private String providerId;

    @Column(name = "customer_id", length = 30)
    private String customerId;

    @Column(name = "image_date", length = 30)
    private LocalDate imageDate ;


}

