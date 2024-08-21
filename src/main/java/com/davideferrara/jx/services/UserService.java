package com.davideferrara.jx.services;

import com.davideferrara.jx.JxApplication;
import com.davideferrara.jx.exceptions.UserException;
import com.davideferrara.jx.models.Profile;
import com.davideferrara.jx.models.User;
import com.davideferrara.jx.repositories.PersonRepository;
import com.davideferrara.jx.repositories.ProfileRepository;
import com.davideferrara.jx.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserService extends PersonService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final static Logger logger = JxApplication.getLogger();

    public UserService(PersonRepository personRepository, UserRepository userRepository, ProfileRepository profileRepository) {
        super(personRepository);
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            logger.log(Level.SEVERE, "User with id {0} not found", id);
            throw new UserException("User not found!");
        }

        logger.log(Level.INFO, "User with id {0} found", id);
        return user.get();
    }

    /**
    * Aggiunge un User al database e crea un profilo di default
    * */
    @Async
    @Transactional
    public CompletableFuture<User> addNewUser(User user) throws UserException {
        // Validazioni
        User.validateEmail(user.getEmail());
        User.validateName(user.getFirstName());
        User.validateName(user.getLastName());
        User.validateGender(user.getGender());

        // Controlla se l'utente esiste già
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            logger.warning("User already exists, throwing UserExeption!");
            throw new UserException("User with email: " + user.getEmail()  +" already exists!");
        }

        logger.info("User will be added by Thread: " + Thread.currentThread().getName());

        // Crea l'utente
        User savedUser = userRepository.save(user);
        logger.log(Level.INFO, "New user created: " + savedUser.toString());

        // Creazione e associazione del profilo
        Profile profile = new Profile("Default description", 0, 0, 0);
        profile.setUser(savedUser);
        profileRepository.save(profile);

        // Associazione del profilo all'utente
        savedUser.setProfile(profile);
        logger.log(Level.INFO, "Profile associated to the userId: " + savedUser.getId());

        // Restituisce il risultato come CompletableFuture
        return CompletableFuture.completedFuture(savedUser);
    }

    /**
     * Elimina un utente via ID
    */
    public void deleteUser(Long personId) {
        try {
            super.deletePerson(personId);
        }
        catch (UserException e) {
            logger.log(Level.SEVERE, "Can't delete user with id {0} since is not found", personId);
        }
    }

    /**
    * Aggiorna i dati dell'utente
    */
    public void updateUser(User UpdatedUser) throws UserException {
        Optional<User> optionalUser = userRepository.findById(UpdatedUser.getId());

        if (optionalUser.isEmpty()) {
            logger.log(Level.SEVERE, "User with id {0} not found", UpdatedUser.getId());
            throw new UserException("User not found or object is invalid!");
        }

        User user = optionalUser.get();

        logger.log(Level.INFO, "User with id {0} found, updating user info...", UpdatedUser.getId());
        user.setFirstName(UpdatedUser.getFirstName());
        user.setLastName(UpdatedUser.getLastName());
        user.setEmail(UpdatedUser.getEmail());
        user.setGender(UpdatedUser.getGender());
        user.setAddress(UpdatedUser.getAddress());
        user.setCountry(UpdatedUser.getCountry());
        user.setUserName(UpdatedUser.getUserName());

        if(UpdatedUser.getDob() != null){
            user.setDob(UpdatedUser.getDob());
        }

        logger.log(Level.INFO, "User with id {0} updated!", UpdatedUser.getId());
        userRepository.save(user);
    }
}


