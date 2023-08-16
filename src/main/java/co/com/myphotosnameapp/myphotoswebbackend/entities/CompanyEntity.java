package co.com.myphotosnameapp.myphotoswebbackend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "COMPANY")
public class CompanyEntity implements Serializable {
    @Id
    @Column(name = "ID", length = 30)
    private String id;

    @Column(name = "STATUS", length = 50)
    private String status;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    @Column(name = "MODIFIED_DATE")
    private LocalDateTime modifiedDate;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID", foreignKey = @ForeignKey(name = "FK_IMAGE_OWNER"))
    private PersonEntity personEntityId;

}
