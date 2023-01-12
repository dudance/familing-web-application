package com.project.project.mappers;

import java.util.List;
import java.util.Set;

public interface MapperInterface<T, DTO> {

    DTO objectToDto(T object);
    List<DTO> objectToDto(List<T> objectList);
    Set<DTO> objectToDto(Set<T> objectSet);
    T dtoToObject(DTO object);
    List<T> dtoToObject(List<DTO> objectList);
    Set<T> dtoToObject(Set<DTO> objectSet);

}
