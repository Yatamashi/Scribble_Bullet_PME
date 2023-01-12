package de.fhe.pmeplayground.storage;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

import de.fhe.pmeplayground.model.ToDo;

/**
 * interface to work on the Database with SQL statements
 */
@Dao
public interface ToDoDao {

    @Insert
    long insert(ToDo toDo);

    @Insert
    List<Long> insert(ToDo... toDos);

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

    @Query("SELECT * FROM ToDo")
    LiveData<List<ToDo>> getToDosLiveDataList();

    @Query("SELECT * FROM ToDo")
    LiveData<ToDo> getToDosLiveDataNotList();

    @Query("SELECT * from ToDo ORDER BY toDoTitle ASC")
    List<ToDo> getToDosSortedByToDo();

    @Query("SELECT * from ToDo ORDER BY toDoId DESC LIMIT 1")
    ToDo getLastEntry();

    @Query("SELECT * FROM ToDo WHERE toDoTitle LIKE :search")
    List<ToDo> getToDosForToDo(String search);

    @Query("SELECT category FROM ToDo group by category")
    List<String> getListOfCategories();

    @Query("SELECT * FROM ToDo WHERE toDoId = :toDoId")
    LiveData<ToDo> getToDoById(long toDoId);

    @Query("UPDATE ToDo SET toDoDone = :toDoDone WHERE toDoId = :toDoId")
    void setToDoDone(long toDoId, boolean toDoDone);
}
