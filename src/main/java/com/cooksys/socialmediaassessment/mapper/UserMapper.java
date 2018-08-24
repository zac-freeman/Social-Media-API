package com.cooksys.socialmediaassessment.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.cooksys.socialmediaassessment.dto.UserRequestDTO;
import com.cooksys.socialmediaassessment.dto.UserResponseDTO;
import com.cooksys.socialmediaassessment.entity.User;


@Mapper(componentModel = "spring")
public interface UserMapper {

	//UserResponseDTO
	@Mappings({
		@Mapping(target = "joined", expression = "java(user.getJoined().getTime())"),
		@Mapping(source = "credentials.username", target = "username")
	})
	UserResponseDTO toResponseDTO(User user);
	Collection<UserResponseDTO> toResponseDTOs(Collection<User> users);

	//UserRequestDTO
	User fromRequestDTO(UserRequestDTO userRequestDTO);
	Collection<User> fromRequestDTOs(Collection<UserRequestDTO> userRequestDTOs);

}
