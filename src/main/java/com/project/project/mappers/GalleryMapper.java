package com.project.project.mappers;

import com.project.project.dto.GalleryDto;
import com.project.project.models.Gallery;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring", uses = {ReferenceMapper.class})
public interface GalleryMapper extends MapperInterface<Gallery, GalleryDto> {

}
