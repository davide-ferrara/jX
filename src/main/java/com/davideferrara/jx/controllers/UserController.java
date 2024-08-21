package com.davideferrara.jx.controllers;

import com.davideferrara.jx.JxApplication;
import com.davideferrara.jx.exceptions.PersonException;
import com.davideferrara.jx.exceptions.UserException;
import com.davideferrara.jx.models.User;
import com.davideferrara.jx.repositories.UserRepository;
import com.davideferrara.jx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;
    private final static Logger logger = JxApplication.getLogger();
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    @PostMapping("/add-new-users")
    public ResponseEntity<Map<String, String>> addNewUsers(@RequestBody List<User> users) {
        Map<String, String> response = new HashMap<>();
        List<CompletableFuture<User>> futures = new ArrayList<>();

        logger.log(Level.INFO, "Received request to add " + users.size() + " users");

        for (User user : users) {
            CompletableFuture<User> futureUser = userService.addNewUser(user);
            futures.add(futureUser);
        }

        // Join
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        try {
            allFutures.get();
            String msg = "All users have been added!";
            response.put("response", msg);
            logger.log(Level.INFO, "All users have been added!");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (InterruptedException | ExecutionException e) {
            String msg = "Error occurred while adding users!";
            response.put("response", msg);
            logger.log(Level.INFO, msg, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Ritorna un oggetto di tipo User tramite id
     */
    @GetMapping("/")
    public ResponseEntity<Map<String, User>> getUserById(@RequestParam Long user_id) {
        Map<String, User> response = new HashMap<>();
        try {
            User user = userService.getUserById(user_id);
            response.put("response", user);
            return ResponseEntity.ok(response);
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Aggiunge un utente nel database, è richiesto nel body un ogegtto di tipo User
    */
    @PostMapping(path = "/add-new-user")
    public ResponseEntity<Map<String, String>> addNewUser(@RequestBody User user){
        Map<String, String> response = new HashMap<>();
        try {
            // Attendo il completamento del CompletableFuture
            User savedUser = userService.addNewUser(user).get(); // Chiamata bloccante
            response.put("response", "New user " + savedUser.getUserName() + " added successfully!");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof UserException | cause instanceof PersonException) {
                logger.log(Level.WARNING, cause.getMessage());
                response.put("error", cause.getMessage());
                return ResponseEntity.badRequest().body(response);
            } else {
                logger.log(Level.SEVERE, cause.getMessage());
                response.put("error", "An unexpected error occurred: " + cause.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            response.put("error", "Thread was interrupted");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Elimina un User dal database tramite ID
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, String>> deleteUser(@RequestParam("user_id") Long userId){
        Map<String, String> response = new HashMap<>();
        try{
            userService.deleteUser(userId);
            response.put("response", "User with id " + userId + " deleted successfully!");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (PersonException e) {
            response.put("response", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    /**
     * Aggiorna le informazioni dell'utente
     */
    @PutMapping("/update")
    public ResponseEntity<Map<String,String>> updateUser(@RequestBody User UpdatedUser) {
        Map<String, String> response = new HashMap<>();
        try{
            userService.updateUser(UpdatedUser);
            response.put("response", "User with id " + UpdatedUser.getId() + " updated successfully!");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (UserException e) {
            response.put("response", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
