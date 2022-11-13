package de.fhe.pmeplayground.storage;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import de.fhe.pmeplayground.model.Contact;

@Dao
public interface ContactDao {
    @Insert
    void insert(Contact... contacts);

    @Update
    void update(Contact... contacts);

    @Delete
    void delete(Contact... contacts);

    @Query("DELETE FROM Contact")
    void deleteAll();

    @Query("SELECT count(*) FROM Contact")
    int count();

    @Query("SELECT * from Contact")
    List<Contact> getContacts();

    @Query("SELECT * from Contact ORDER BY lastname ASC")
    List<Contact> getContactSortedByLastname();

    @Query("SELECT * from Contact ORDER BY id DESC LIMIT 1")
    Contact getLastEntry();

    @Query("SELECT * FROM Contact WHERE lastname LIKE :search")
    List<Contact> getContactsForLastname(String search);
}
