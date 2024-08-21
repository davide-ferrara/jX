package com.davideferrara.jx.controllers;

import com.davideferrara.jx.JxApplication;
import com.davideferrara.jx.exceptions.ProfileException;
import com.davideferrara.jx.models.Profile;
import com.davideferrara.jx.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;
    private final static Logger logger = JxApplication.getLogger();

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    /**
     * Ritorna la descrizione del profilo via ID
     */
    @GetMapping("/description")
    public ResponseEntity<Map<String, String>> descriptionById(@RequestParam(value="profile_id") Long profileId) {
        return ResponseEntity.ok(profileService.getProfileDescriptionById(profileId));
    }

    /**
     * Ritorna il numero di posts del profilo via ID
     */
    @GetMapping("/num-of-posts")
    public ResponseEntity<Map<String, String>> numOfPostById(@RequestParam(value="profile_id") Long profileId) {
        return ResponseEntity.ok(profileService.getProfileNumOfPostsById(profileId));
    }

    /**
     * Ritorno il numero dei followers tramite ID
     * */
    @GetMapping("/num-of-followers")
    public ResponseEntity<Map<String, String>> getProfileNumOfFollowersById(@RequestParam(value="profile_id") Long profileId) {
        return ResponseEntity.ok(profileService.getProfileNumOfFollowersById(profileId));
    }

    /**
     * Ritorno il numero dei following tramite ID
     * */
    @GetMapping("/num-of-following")
    public ResponseEntity<Map<String, String>> getProfileNumOfFollowingById(@RequestParam(value="profile_id") Long profileId) {
        return ResponseEntity.ok(profileService.getProfileNumOfFollowingById(profileId));
    }

    /**
     * Ritorno l'oggetto Profile tramite ID
     * */
    @GetMapping(path = "{profile_id}")
    public ResponseEntity<Map<String, Profile>> getProfileById(@PathVariable("profile_id") Long profileId) {
        Map<String, Profile> response = new HashMap<>();
        try {
            Profile profile = profileService.getProfileById(profileId);
            response.put("response", profile);
            logger.info("Updated profile: " + profile.toString());
            return ResponseEntity.ok(response);
        }
        catch (ProfileException e) {
            logger.warning(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    /**
     * Aggiorna i dati del profilo
     * */
    @PutMapping
    public ResponseEntity<Map<String, String>> updateProfile(@RequestBody Profile profile) {
        Map<String, String> response = new HashMap<>();
        try{
            response.put("response", "Profile updated successfully");
            profileService.updateProfile(profile);
            return ResponseEntity.ok(response);
        }
        catch (ProfileException e){
            response.put("response", e.getMessage());
            logger.warning(e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteProfileById(@RequestParam(value="profile_id") Long profileId) {
        Map<String, String> response = new HashMap<>();
        try {
            response.put("response", "Profile deleted successfully");
            profileService.deleteProfileById(profileId);
            return ResponseEntity.ok(response);
        }
        catch (ProfileException e){
            response.put("response", e.getMessage());
            logger.warning(e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

}
