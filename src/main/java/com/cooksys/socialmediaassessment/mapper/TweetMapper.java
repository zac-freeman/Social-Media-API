package com.cooksys.socialmediaassessment.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.cooksys.socialmediaassessment.dto.TweetRequestDTO;
import com.cooksys.socialmediaassessment.dto.TweetResponseDTO;
import com.cooksys.socialmediaassessment.dto.UserResponseDTO;
import com.cooksys.socialmediaassessment.entity.Tweet;
import com.cooksys.socialmediaassessment.entity.User;

//TODO: MOVE BROKEN MAPPING LOGIC TO TweetService
@Mapper(componentModel = "spring")
public interface TweetMapper {

	// TweetResponseDTO
	@Mapping(target = "posted", expression = "java(tweet.getPosted().getTime())")
	TweetResponseDTO toResponseDTO(Tweet tweet);

	List<TweetResponseDTO> toResponseDTOs(List<Tweet> tweets);

	@Mapping(source = "credentials", target = "author.credentials")
	Tweet fromRequestDTO(TweetRequestDTO tweetRequestDTO);

	// UserResponseDTO
	@Mappings({ @Mapping(target = "joined", expression = "java(user.getJoined().getTime())"),
			@Mapping(source = "credentials.username", target = "username") })
	UserResponseDTO toResponseDTO(User user);
}
