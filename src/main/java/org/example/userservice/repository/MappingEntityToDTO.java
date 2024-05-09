package org.example.userservice.repository;
import java.util.function.Function;


@FunctionalInterface
public interface MappingEntityToDTO <T,R>{
    Function<T, R> dtoEntity();
}
