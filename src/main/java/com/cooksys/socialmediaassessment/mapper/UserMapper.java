package com.cooksys.socialmediaassessment.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.cooksys.socialmediaassessment.dto.UserRequestDTO;
import com.cooksys.socialmediaassessment.dto.UserResponseDTO;
import com.cooksys.socialmediaassessment.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	// UserResponseDTO
	@Mappings({ @Mapping(target = "joined", expression = "java(user.getJoined().getTime())"),
			@Mapping(source = "credentials.username", target = "username") })
	UserResponseDTO toResponseDTO(User user);

	List<UserResponseDTO> toResponseDTOs(List<User> users);

	// UserRequestDTO
	User fromRequestDTO(UserRequestDTO userRequestDTO);

	List<User> fromRequestDTOs(List<UserRequestDTO> userRequestDTOs);

}
