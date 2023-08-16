package co.com.myphotosnameapp.myphotoswebbackend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "image_set",
        indexes = {
            @Index(name = "inx_uk_image_set",
                    columnList = "image_source_id, image_destination_id, status")
})
public class ImageSetEntity {

    @Id
    @Column(name = "id", length = 30)
    private String id;

    @Column(name = "image_source_id", length = 30, nullable = false)
    private String imageSourceId;

    @Column(name = "image_destination_id", length = 30)
    private String imageDestinationId;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "created_by", length = 100, nullable = false)
    private String createdBy;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "modified_by", length = 100)
    private String modifiedBy;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "name", length = 30)
    private String name;

}

