package co.com.myphotosnameapp.myphotoswebbackend.services.implementation;

import co.com.myphotosnameapp.myphotoswebbackend.dtos.RequestDto;
import co.com.myphotosnameapp.myphotoswebbackend.entities.CeremonyEntity;
import co.com.myphotosnameapp.myphotoswebbackend.entities.RequestEntity;
import co.com.myphotosnameapp.myphotoswebbackend.repositories.RequestRepository;
import co.com.myphotosnameapp.myphotoswebbackend.services.IRequestService;
import co.com.myphotosnameapp.myphotoswebbackend.utilities.IUtilityMapper;
import co.com.myphotosnameapp.myphotoswebbackend.utilities.Utilities;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
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
public class RequestService implements IRequestService {

    @Autowired
    RequestRepository repository ;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    IUtilityMapper mapper ;

    @Override
    public List<RequestDto> read(RequestDto requestDto) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RequestEntity> query = criteriaBuilder.createQuery(RequestEntity.class);
        Root<RequestEntity> root = query.from(RequestEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        if (requestDto.getId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("id"), requestDto.getId()));
        }

        if (requestDto.getStatus() != null) {
            predicates.add(criteriaBuilder.equal(root.get("status"), requestDto.getStatus()));
        }

        if (requestDto.getModifiedDate() != null) {
            LocalDateTime finishDate = requestDto.getModifiedDate().plusDays(1);
            predicates.add(criteriaBuilder.between(root.get("modifiedDate"), requestDto.getModifiedDate(), finishDate));
        }

        if (requestDto.getModifiedBy() != null) {
            predicates.add(criteriaBuilder.equal(root.get("modifiedBy"), requestDto.getModifiedBy()));
        }

        if (requestDto.getCreatedDate() != null) {
            LocalDateTime finishDate = requestDto.getCreatedDate().plusDays(1);
            predicates.add(criteriaBuilder.between(root.get("createdDate"), requestDto.getCreatedDate(), finishDate));
        }

        if (requestDto.getProviderId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("providerId"), requestDto.getProviderId()));
        }

        if (requestDto.getCustomerId() != null) {
            predicates.add(criteriaBuilder.equal(root.get("customerId"), requestDto.getCustomerId()));
        }

        if (requestDto.getCeremonyId() != null){
            Join<RequestEntity, CeremonyEntity> ceremony = root.join("ceremonyId");

            if(requestDto.getCeremonyId().getId() != null){
                predicates.add(criteriaBuilder.equal(ceremony.get("id"), requestDto.getCeremonyId().getId()));
            }

            if(requestDto.getCeremonyId().getEventDate() != null){
                predicates.add(criteriaBuilder.equal(ceremony.get("eventDate"), requestDto.getCeremonyId().getEventDate()));
            }

        }

        query.select(root).where(predicates.toArray(new Predicate[0]));
        return entityManager
                .createQuery(query)
                .getResultList()
                .stream()
                .map(value ->  mapper.toDto(value))
                .toList();
    }

    @Transactional
    @Override
    public String create(RequestDto requestDto) throws TransactionalException {
        try {
            if (requestDto.getId() != null) {
                Optional<RequestEntity> isRequest = repository.findById(requestDto.getId());
                if (isRequest.isPresent()) {
                    throw new IllegalArgumentException("Request Id Exist!");
                }
            }

            RequestEntity entity = mapper.toEntity(requestDto);
            String id = Utilities.getId() ;
            entity.setId(id);
            entity.setCreatedDate(LocalDateTime.now());

            repository.save(entity);

            return id ;

        }catch (Exception e){
            throw new TransactionalException(e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public String update(RequestDto requestDto) throws TransactionalException {
        try {
            if (requestDto.getId() != null) {
                Optional<RequestEntity> isRequest = repository.findById(requestDto.getId());
                if (!isRequest.isPresent()) {
                    throw new IllegalArgumentException("Request id does not  Exist!");
                }
                RequestEntity entity = mapper.toEntity(requestDto);
                entity.setCreatedBy(isRequest.get().getCreatedBy());
                entity.setCreatedDate(isRequest.get().getCreatedDate());
                entity.setModifiedDate(LocalDateTime.now());
                repository.save(entity);
                return entity.getId() ;
            }
            throw new IllegalArgumentException("Request Id is null!");
        }catch (Exception e){
            throw new TransactionalException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<RequestDto> read(String id) {
        Optional<RequestEntity> entity = repository.findById(id);
        if(entity.isPresent())
            return Optional.of(mapper.toDto(entity.get()));
        return Optional.empty();
    }
}
