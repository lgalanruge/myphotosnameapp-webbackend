package co.com.myphotosnameapp.myphotoswebbackend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
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

    @Column(name = "STATUS", length = 50)
    private String status;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdDate;

    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    @Column(name = "MODIFIED_DATE")
    private LocalDateTime modifiedDate;


    @ManyToOne
    @JoinColumn(name = "COMPANY_ID", foreignKey = @ForeignKey(name = "FK_IMAGE_COMPANY"))
    private CompanyEntity company;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID", foreignKey = @ForeignKey(name = "FK_IMAGE_OWNER"))
    private OwnerEntity owner;


}

