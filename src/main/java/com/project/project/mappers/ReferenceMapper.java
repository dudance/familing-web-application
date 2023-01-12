package com.project.project.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.TargetType;

import java.lang.reflect.InvocationTargetException;

@Mapper(componentModel = "spring")
public class ReferenceMapper {

    public <T extends IdInterface> T convertToClass(Long reference, @TargetType Class<T> entityClass) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        T result = entityClass.getDeclaredConstructor().newInstance();
        result.setId(reference);
        return reference != null ? result : null;
    }

    public Long convertToLong(IdInterface entity) {
        return entity != null ? entity.getId() : null;
    }
}
