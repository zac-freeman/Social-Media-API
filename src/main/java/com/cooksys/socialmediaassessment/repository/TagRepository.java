package com.cooksys.socialmediaassessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.socialmediaassessment.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

}