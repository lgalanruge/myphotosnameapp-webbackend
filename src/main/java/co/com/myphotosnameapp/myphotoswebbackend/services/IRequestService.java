package co.com.myphotosnameapp.myphotoswebbackend.services;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.RequestDto;
import jakarta.transaction.TransactionalException;

import java.util.List;
import java.util.Optional;

public interface IRequestService {

    public List<RequestDto> read(RequestDto requestDto);

    public String create(RequestDto requestDto) throws TransactionalException;

    public String update(RequestDto requestDto) throws TransactionalException;

    public Optional<RequestDto> read(String id);
}
