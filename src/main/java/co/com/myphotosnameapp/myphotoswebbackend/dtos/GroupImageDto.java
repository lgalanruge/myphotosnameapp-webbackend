package co.com.myphotosnameapp.myphotoswebbackend.dtos;

import co.com.myphotosnameapp.myphotoswebbackend.utilities.ImageProcessStatus;
import lombok.Data;

@Data
public class GroupImageDto {

    private String id ;

    private String imageSourceId;

    private String name;

    private ImageProcessStatus status;

}
