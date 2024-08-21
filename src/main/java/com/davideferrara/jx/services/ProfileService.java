package com.davideferrara.jx.services;

import com.davideferrara.jx.classes.Utils;
import com.davideferrara.jx.exceptions.ProfileException;
import com.davideferrara.jx.models.Profile;
import com.davideferrara.jx.models.User;
import com.davideferrara.jx.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile getProfileById(Long profileId){
        Optional<Profile> profile = profileRepository.getProfileById(profileId);
        return Utils.getValueFromOptional(profile, "Profile ID is no valid!");
    }

    public Map<String, String> getProfileDescriptionById(Long profileId){
        String description = Utils.getValueFromOptional(profileRepository.getProfileDescriptionById(profileId), "Description is empty or id non valid!");
        return Utils.mapFromValues(List.of("description", description));
    }

    public Map<String, String> getProfileNumOfPostsById(Long profileId) {
        String numOfPosts = Utils.getValueFromOptional(profileRepository.getProfileNumOfPostsById(profileId), "Number of posts is empty or id non valid!");
        return Utils.mapFromValues(List.of("numOfPosts", numOfPosts));
    }

    public Map<String, String> getProfileNumOfFollowersById(Long profileId) {
        String numOfFollowers =  Utils.getValueFromOptional(profileRepository.getProfileNumOfFollowersById(profileId), "Number of followers is empty or id non valid!");
        return Utils.mapFromValues(List.of("numOfFollowers", numOfFollowers));
    }

    public Map<String, String> getProfileNumOfFollowingById(Long profileId) {
        String numOfFollowing =  Utils.getValueFromOptional(profileRepository.getProfileNumOfFollowingById(profileId), "Number of following is empty or id non valid!");
        return Utils.mapFromValues(List.of("numOfFollowing", numOfFollowing));
    }

    public void updateProfile(Profile updatedProfile) {
        Optional<Profile> profileOptional = profileRepository.getProfileById(updatedProfile.getId());

        if (profileOptional.isEmpty()) {
            throw new ProfileException("Cannot find profile with id " + updatedProfile.getId());
        }

        Profile profile = profileOptional.get();
        profile.setDescription(updatedProfile.getDescription());

        profileRepository.save(profile);
    }

    @Async
    public void deleteProfileById(Long profileId) {
        profileRepository.deleteById(profileId);
    }

}
