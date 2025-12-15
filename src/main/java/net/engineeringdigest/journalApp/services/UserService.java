package net.engineeringdigest.journalApp.services;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repositry.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserEntryRepository userEntryRepository;

    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public boolean saveNewUser(User user){

        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userEntryRepository.save(user);
            return true;
        }catch (Exception e){
            log.error("hahhhaahha ");
            return false;
        }

    }

    public void saveUser(User user){
        userEntryRepository.save(user);
    }

    public List<User> getAll(){
        return userEntryRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userEntryRepository.findById(id);
    }


    public void deleteById(ObjectId id){
        userEntryRepository.deleteById(id);
    }

    public User findByUserName(String userName){
       return userEntryRepository.findByUserName(userName);
    }


    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("ADMIN"));
        userEntryRepository.save(user);
    }
}
