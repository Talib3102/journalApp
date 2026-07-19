package com.talib.journalApp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

@Document(collection = "journal_entries")
@Data //this annotaion include all getter setter and more annotations
@NoArgsConstructor//we use lomboks @Date annotation in this there is an annotaion that called @RequiredArgsConstructor that applied so that means there is no @NoArgsConstructor so we have to put that noArgConstructor this is required for de-Searialization means Convert JSON to POJO thats why we have to use @NoArgsConstructor
public class JournalEntry {


    @Id
    private ObjectId id;
    private LocalDateTime date;
    @NonNull
    private String title;
    private String content;

}
