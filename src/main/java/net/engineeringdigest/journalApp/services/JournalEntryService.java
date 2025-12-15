package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repositry.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
       try {
           User user = userService.findByUserName(userName);
           journalEntry.setDate(LocalDateTime.now());
           JournalEntry saved = journalEntryRepository.save(journalEntry);
           user.getJournalEntries().add(saved);

           userService.saveUser(user);
       }catch (Exception e){
           System.out.println(e);
           throw new RuntimeException("An error occur saving entry ", e);
       }
    }

    public void saveEntry(JournalEntry journalEntry){
       journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
       return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }


    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed=false;
       try {
           User users = userService.findByUserName(userName);
           removed = users.getJournalEntries().removeIf(s -> s.getId().equals(id));
           if (removed) {
               userService.saveUser(users);
               journalEntryRepository.deleteById(id);
           }
       }catch (Exception e){
           System.out.println(e);
           throw new RuntimeException("An error while deleted the entry ",e);
       }
       return removed;
    }

    public List<JournalEntry> findByUserName(String userName){
        return null;
    }
}
