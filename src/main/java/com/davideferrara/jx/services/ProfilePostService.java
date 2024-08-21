package com.davideferrara.jx.services;

import com.davideferrara.jx.JxApplication;
import com.davideferrara.jx.dto.ProfileAddNewPostRequest;
import com.davideferrara.jx.exceptions.ProfilePostException;
import com.davideferrara.jx.models.Profile;
import com.davideferrara.jx.models.ProfilePost;
import com.davideferrara.jx.repositories.ProfilePostRepository;
import com.davideferrara.jx.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ProfilePostService {
    private final ProfilePostRepository profilePostRepository;
    private final ProfileRepository profileRepository;
    private final static Logger logger = JxApplication.getLogger();

    @Autowired
    public ProfilePostService(ProfilePostRepository profilePostRepository, ProfileRepository profileRepository) {
        this.profilePostRepository = profilePostRepository;
        this.profileRepository = profileRepository;
    }

    // Aggiunge un post al profilo
    public void addProfilePost(ProfileAddNewPostRequest profileAddNewPostRequest){
        Date now = new Date();

        Optional<Profile> profileOptional = profileRepository.findById(profileAddNewPostRequest.getProfileId());

        logger.info("Searching for profile with id: " + profileAddNewPostRequest.getProfileId());

        if(profileOptional.isEmpty()){
            logger.warning("Profile with id: " + profileAddNewPostRequest.getProfileId() + " not found");
            throw new IllegalArgumentException("Profile id not found");
        }

        ProfilePost newProfilePost = new ProfilePost(profileAddNewPostRequest.getDescription(), now);
        newProfilePost.setProfile(profileOptional.get());

        logger.info("Adding new profile post: " + profileAddNewPostRequest.getDescription());
        profilePostRepository.save(newProfilePost);
    }

    @GetMapping
    public ProfilePost getProfilePostById(@RequestParam(name = "post_id") Long postId){
        Optional <ProfilePost> profilePost = profilePostRepository.getProfilePostById(postId);

        if(profilePost.isEmpty()){
            throw new ProfilePostException("Profile id not found");
        }

        return profilePost.get();
    }

}
