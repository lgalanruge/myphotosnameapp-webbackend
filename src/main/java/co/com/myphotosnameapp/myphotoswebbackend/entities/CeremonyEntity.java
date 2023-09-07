package co.com.myphotosnameapp.myphotoswebbackend.entities;


import co.com.myphotosnameapp.myphotoswebbackend.utilities.CeremonyStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "ceremony")
public class CeremonyEntity {

    @Id
    @Column(name = "id", length = 30, nullable = false)
    private String id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "description", length = 200)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50)
    private CeremonyStatus status;

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

    @Column(name = "event_date")
    private LocalDate eventDate ;

    @Column(name = "event_start_time")
    private LocalTime eventStartTime ;

    @Column(name = "event_finish_time")
    private LocalTime eventFinishTime ;


}
