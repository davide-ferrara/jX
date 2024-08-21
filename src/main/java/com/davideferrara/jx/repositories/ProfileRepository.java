package com.davideferrara.jx.repositories;

import com.davideferrara.jx.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query("SELECT p FROM Profile p WHERE p.id=?1")
    Optional<Profile> getProfileById(Long id);

    @Query("SELECT p.description FROM Profile p WHERE p.id=?1")
    Optional<String> getProfileDescriptionById(Long id);

    @Query("SELECT p.numOfPosts FROM Profile p WHERE p.id=?1")
    Optional<String> getProfileNumOfPostsById(Long id);

    @Query("SELECT p.numOfFollowers FROM Profile p WHERE p.id=?1")
    Optional<String> getProfileNumOfFollowersById(Long id);

    @Query("SELECT p.numOfFollowing FROM Profile p WHERE p.id=?1")
    Optional<String> getProfileNumOfFollowingById(Long id);

}
