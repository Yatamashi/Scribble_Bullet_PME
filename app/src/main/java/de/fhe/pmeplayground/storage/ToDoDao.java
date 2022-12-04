package de.fhe.pmeplayground.storage;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import de.fhe.pmeplayground.model.ToDo;

@Dao
public interface ToDoDao {
    @Insert
    void insert(ToDo... toDos);

    @Update
    void update(ToDo... toDos);

    @Delete
    void delete(ToDo... toDos);

    @Query("DELETE FROM ToDo")
    void deleteAll();

    @Query("SELECT count(*) FROM ToDo")
    int count();

    @Query("SELECT * from ToDo")
    List<ToDo> getToDos();

    @Query("SELECT * from ToDo ORDER BY toDo ASC")
    List<ToDo> getToDosSortedByToDo();

    @Query("SELECT * from ToDo ORDER BY id DESC LIMIT 1")
    ToDo getLastEntry();

    @Query("SELECT * FROM ToDo WHERE toDo LIKE :search")
    List<ToDo> getToDosForToDo(String search);
}
