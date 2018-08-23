package com.cooksys.socialmediaassessment.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.cooksys.socialmediaassessment.dto.UsersRequestDTO;
import com.cooksys.socialmediaassessment.dto.UsersResponseDTO;
import com.cooksys.socialmediaassessment.entity.Users;


@Mapper(componentModel = "spring")
public interface UsersMapper {

	//UsersResponseDTO
	@Mappings({
		@Mapping(target = "joined", expression = "java(user.getJoined().getTime())"),
		@Mapping(source = "credentials.username", target = "username")
	})
	UsersResponseDTO toResponseDTO(Users user);
	Collection<UsersResponseDTO> toResponseDTOs(Collection<Users> users);

	//UsersRequestDTO
	Users fromRequestDTO(UsersRequestDTO userRequestDTO);
	Collection<Users> fromRequestDTOs(Collection<UsersRequestDTO> userRequestDTOs);

}
