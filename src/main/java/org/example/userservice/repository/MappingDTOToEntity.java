package org.example.userservice.repository;

import java.util.function.Function;

@FunctionalInterface
public interface MappingDTOToEntity<T, R> {
    Function<T, R> entityDTO();
}

