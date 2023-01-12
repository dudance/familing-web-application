package com.project.project.repositories;

import com.project.project.models.Discussion;
import com.project.project.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    List<Post> findAllByDiscussionId(Discussion discussionId);

}
