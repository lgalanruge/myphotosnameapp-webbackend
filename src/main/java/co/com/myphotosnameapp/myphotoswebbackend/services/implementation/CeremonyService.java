package co.com.myphotosnameapp.myphotoswebbackend.services.implementation;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.CeremonyDto;
import co.com.myphotosnameapp.myphotoswebbackend.entities.CeremonyEntity;
import co.com.myphotosnameapp.myphotoswebbackend.repositories.CeremonyRepository;
import co.com.myphotosnameapp.myphotoswebbackend.services.ICeremonyService;
import co.com.myphotosnameapp.myphotoswebbackend.utilities.GenericStatus;
import co.com.myphotosnameapp.myphotoswebbackend.utilities.IUtilityMapper;
import co.com.myphotosnameapp.myphotoswebbackend.utilities.Utilities;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.TransactionalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CeremonyService implements ICeremonyService {

    @Autowired
    CeremonyRepository repository ;

    @Autowired
    IUtilityMapper mapper ;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<CeremonyDto> read(CeremonyDto ceremonyDto) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CeremonyEntity> query = criteriaBuilder.createQuery(CeremonyEntity.class);
        Root<CeremonyEntity> root = query.from(CeremonyEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        if (ceremonyDto.getId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("id"), ceremonyDto.getId()));
        }

        if (ceremonyDto.getStatus() != null) {
            predicates.add(criteriaBuilder.equal(root.get("status"), ceremonyDto.getStatus()));
        }

        if (ceremonyDto.getModifiedDate() != null) {
            LocalDateTime finishDate = ceremonyDto.getModifiedDate().plusDays(1);
            predicates.add(criteriaBuilder.between(root.get("modifiedDate"), ceremonyDto.getModifiedDate(), finishDate));
        }

        if (ceremonyDto.getModifiedBy() != null) {
            predicates.add(criteriaBuilder.equal(root.get("modifiedBy"), ceremonyDto.getModifiedBy()));
        }

        if (ceremonyDto.getCreatedDate() != null) {
            LocalDateTime finishDate = ceremonyDto.getCreatedDate().plusDays(1);
            predicates.add(criteriaBuilder.between(root.get("createdDate"), ceremonyDto.getCreatedDate(), finishDate));
        }

        query.select(root).where(predicates.toArray(new Predicate[0]));
        return entityManager
                .createQuery(query)
                .getResultList()
                .stream()
                .map(value ->  mapper.toDto(value))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CeremonyDto> read(String id) {
        Optional<CeremonyEntity> entity = repository.findById(id);
        if(entity.isPresent())
            return Optional.of(mapper.toDto(entity.get()));
        return Optional.empty();
    }

    @Override
    public String create(CeremonyDto ceremonyDto) throws TransactionalException {

        try {
            if (ceremonyDto.getId() != null) {
                Optional<CeremonyEntity> isCeremony = repository.findById(ceremonyDto.getId());
                if (isCeremony.isPresent()) {
                    throw new IllegalArgumentException("Ceremony Id Exist!");
                }
            }

            CeremonyEntity entity = mapper.toEntity(ceremonyDto);
            String id = Utilities.getId() ;
            entity.setId(id);
            entity.setCreatedDate(LocalDateTime.now());

            repository.save(entity);

            return id ;

        }catch (Exception e){
            throw new TransactionalException(e.getMessage(), e);
        }
    }

    @Override
    public String update(CeremonyDto ceremonyDto) throws TransactionalException {
        try {
            if (ceremonyDto.getId() != null) {
                Optional<CeremonyEntity> isCeremony = repository.findById(ceremonyDto.getId());
                if (!isCeremony.isPresent()) {
                    throw new IllegalArgumentException("Ceremony id does not  Exist!");
                }
            }
            CeremonyEntity entity = mapper.toEntity(ceremonyDto);
            entity.setModifiedDate(LocalDateTime.now());
            repository.save(entity);
            return entity.getId() ;
        }catch (Exception e){
            throw new TransactionalException(e.getMessage(), e);
        }
    }
}
