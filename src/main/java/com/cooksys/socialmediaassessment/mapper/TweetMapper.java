package com.cooksys.socialmediaassessment.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.cooksys.socialmediaassessment.dto.TweetRequestDTO;
import com.cooksys.socialmediaassessment.dto.TweetResponseDTO;
import com.cooksys.socialmediaassessment.entity.Tweet;

@Mapper(componentModel = "spring")
public interface TweetMapper {

	//TweetResponseDTO
	@Mappings ({
		@Mapping(target = "posted", expression = "java(tweet.getPosted().getTime())"),
		@Mapping(target = "author", expression = "java(UserMapper.toResponseDTO(tweet.getAuthor()))")	//TODO: find out right way to convert interior entities to DTOs
	})
	TweetResponseDTO toResponseDTO(Tweet tweet);
	Collection<TweetResponseDTO> toResponseDTOs(Collection<Tweet> tweets);

	@Mapping(target = "author", expression = "java(UserRepository.findUserFromCredentials(tweetRequestDTO.getCredentials()))")	//TODO: find out right way to find necessary objects
	Tweet fromRequestDTO(TweetRequestDTO tweetRequestDTO);
}
