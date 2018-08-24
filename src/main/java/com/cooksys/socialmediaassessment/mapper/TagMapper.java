package com.cooksys.socialmediaassessment.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.socialmediaassessment.dto.TagResponseDTO;
import com.cooksys.socialmediaassessment.entity.Tag;

@Mapper(componentModel = "spring")
public interface TagMapper {

	public TagResponseDTO toResponseDTOs(Tag tag);
	public List<TagResponseDTO> toResponseDTOs(List<Tag> tags);

}
