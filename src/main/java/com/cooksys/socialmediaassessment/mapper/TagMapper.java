package com.cooksys.socialmediaassessment.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;

import com.cooksys.socialmediaassessment.dto.TagResponseDTO;
import com.cooksys.socialmediaassessment.entity.Tag;

@Mapper(componentModel = "spring")
public interface TagMapper {

	public TagResponseDTO toResponseDTOs(Tag tag);
	public Collection<TagResponseDTO> toResponseDTOs(Collection<Tag> tags);

}
