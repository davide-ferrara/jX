package com.davideferrara.jx.repositories;

import com.davideferrara.jx.models.ProfilePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfilePostRepository extends JpaRepository<ProfilePost, Long> {
    @Query("SELECT p FROM ProfilePost p WHERE p.postId = ?1")
    Optional<ProfilePost> getProfilePostById(Long postId);
}
