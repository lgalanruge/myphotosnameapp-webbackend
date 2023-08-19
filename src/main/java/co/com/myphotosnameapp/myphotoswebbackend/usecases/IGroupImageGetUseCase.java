package co.com.myphotosnameapp.myphotoswebbackend.usecases;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.GroupImageDto;
import co.com.myphotosnameapp.myphotoswebbackend.dtos.ImageSetDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IGroupImageGetUseCase {

    ResponseEntity<Set<GroupImageDto>> get(Map<String, String> allParams);


}
