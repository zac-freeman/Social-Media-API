package com.cooksys.socialmediaassessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.socialmediaassessment.entity.Tags;

@Repository
public interface TagsRepository extends JpaRepository<Tags, Integer> {

}