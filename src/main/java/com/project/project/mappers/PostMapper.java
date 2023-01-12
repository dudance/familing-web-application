package com.project.project.mappers;

import com.project.project.dto.PostDto;
import com.project.project.models.Post;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring", uses = {ReferenceMapper.class} )
public interface PostMapper extends MapperInterface<Post, PostDto> {
}
