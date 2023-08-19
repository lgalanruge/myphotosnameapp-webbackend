package co.com.myphotosnameapp.myphotoswebbackend.dtos;

import lombok.Data;

@Data
public class GroupImageDto {

    private String id ;

    private String imageSourceId;

    private String name;

    private String status;

}
