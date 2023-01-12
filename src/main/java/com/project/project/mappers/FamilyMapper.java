package com.project.project.mappers;

import com.project.project.dto.FamilyDto;
import com.project.project.models.Family;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring", uses = {ReferenceMapper.class} )
public interface FamilyMapper extends MapperInterface<Family, FamilyDto> {
}
