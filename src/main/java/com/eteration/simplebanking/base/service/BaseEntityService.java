package com.eteration.simplebanking.base.service;

import com.eteration.simplebanking.base.model.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseEntityService <E extends BaseEntity, R extends JpaRepository<E,Long>>{
    private final R repo;

    public List<E> findAll() {
        return repo.findAll();
    }

    public Optional<E> findById(Long id) {
        return repo.findById(id);
    }

    public E save(E entity){
        return (E) repo.save(entity);
    }

    public E update(E entity){
        return repo.save(entity);
    }

    public void delete(E entities){
        repo.delete(entities);
    }


    public E getByIdWithControl(Long id) {
        Optional<E> optionalById = findById(id);

        E entity;
        if (optionalById.isPresent()) {
            entity = optionalById.get();
        }else {
            throw new RuntimeException("NOT FOUND");
        }

        return entity;
    }

    public R getRepo(){
        return repo;
    }
}
