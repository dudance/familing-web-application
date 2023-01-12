package com.project.project.mappers;

import com.project.project.dto.InvitationDto;
import com.project.project.models.Invitation;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring", uses = {ReferenceMapper.class} )
public interface InvitationMapper extends MapperInterface<Invitation, InvitationDto> {
}
