package net.engineeringdigest.journalApp.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NotNull
    private String userName;

    private String email;
    private boolean sentimentAnalysis;

    @NotNull
    private String password;

    @DBRef
    private List<JournalEntry> journalEntries=new ArrayList<>();
    private List<String> roles;
}
