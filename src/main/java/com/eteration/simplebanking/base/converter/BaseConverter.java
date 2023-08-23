package com.eteration.simplebanking.base.converter;

import java.util.List;

public interface BaseConverter<E,D> {
    D toDto(E e);
    E toEntities(D d);
    List<E> toEntityList(List<D> d);
    List<D> toDtoList(List<E> e);
}
