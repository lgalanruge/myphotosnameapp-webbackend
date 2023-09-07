package co.com.myphotosnameapp.myphotoswebbackend.dtos;

import co.com.myphotosnameapp.myphotoswebbackend.utilities.ImageProcessStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class AssignImageDto implements Serializable {

    private String imageId ;

    private String requestId ;

    private ImageProcessStatus status ;

    private String imageSetId ;

}
