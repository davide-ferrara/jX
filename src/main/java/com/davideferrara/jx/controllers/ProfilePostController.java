package com.davideferrara.jx.controllers;

import com.davideferrara.jx.dto.ProfileAddNewPostRequest;
import com.davideferrara.jx.exceptions.ProfileException;
import com.davideferrara.jx.exceptions.ProfilePostException;
import com.davideferrara.jx.models.ProfilePost;
import com.davideferrara.jx.services.ProfilePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(path = "api/v1/profile-post")
public class ProfilePostController {

    private final ProfilePostService profilePostService;

    @Autowired
    public ProfilePostController(ProfilePostService profilePostService) {
        this.profilePostService = profilePostService;
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addNewPost(@RequestBody  ProfileAddNewPostRequest profileAddNewPostRequest) {
        Map<String, String> response = new HashMap<>();
        try {
            profilePostService.addProfilePost(profileAddNewPostRequest);
            response.put("response", "New post with id: " + profileAddNewPostRequest.getDescription() + " has been added!");
            return ResponseEntity.ok(response);
        }
        catch (ProfilePostException e){
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ProfilePost> getPostById(@RequestParam("post_id") long postId) {
        return ResponseEntity.ok(profilePostService.getProfilePostById(postId));
    }

    

}
