package co.com.myphotosnameapp.myphotoswebbackend.services;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.CeremonyDto;
import co.com.myphotosnameapp.myphotoswebbackend.dtos.RequestDto;
import jakarta.transaction.TransactionalException;

import java.util.List;
import java.util.Optional;

public interface ICeremonyService {

    public List<CeremonyDto> read(CeremonyDto ceremonyDto);

    public Optional<CeremonyDto> read(String id);

    public String create(CeremonyDto ceremonyDto) throws TransactionalException;

    public String update(CeremonyDto ceremonyDto) throws TransactionalException;

}
