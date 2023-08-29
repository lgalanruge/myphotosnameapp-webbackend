package co.com.myphotosnameapp.myphotoswebbackend.entities;


import co.com.myphotosnameapp.myphotoswebbackend.utilities.CeremonyStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "request")
public class RequestEntity {

    @Id
    @Column(name = "id", length = 30, nullable = false)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50)
    private CeremonyStatus status;

    @Column(name = "ceremony_id", length = 30)
    private String ceremonyId;

    @Column(name = "created_by", length = 100, nullable = false)
    private String createdBy;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "modified_by", length = 100)
    private String modifiedBy;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "provider_id", length = 30)
    private String providerId;

    @Column(name = "customer_id", length = 30)
    private String customerId;



}
