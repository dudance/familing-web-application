package com.project.project.mappers;

import com.project.project.dto.DiscussionDto;
import com.project.project.models.Discussion;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring", uses = {ReferenceMapper.class} )
public interface DiscussionMapper extends MapperInterface<Discussion, DiscussionDto> {
}
