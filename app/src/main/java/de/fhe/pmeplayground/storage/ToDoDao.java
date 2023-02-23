package de.fhe.pmeplayground.storage;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
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

    @Update
    void update(ToDo... toDos);

    @Query("SELECT * from ToDo")
    List<ToDo> getToDos();

    @Query("SELECT * from ToDo WHERE category = :search")
    List<ToDo> getToDosForCategory(String search);

    @Query("SELECT * FROM ToDo")
    LiveData<List<ToDo>> getToDosLiveDataList();

    @Query("SELECT * from ToDo ORDER BY toDoTitle ASC")
    List<ToDo> getToDosSortedByToDo();

    @Query("SELECT * from ToDo ORDER BY toDoId DESC LIMIT 1")
    ToDo getLastEntry();

    @Query("SELECT * FROM ToDo WHERE toDoTitle LIKE :search")
    List<ToDo> getToDosForToDo(String search);

    /**
     * @return list of all categories grouped by category, the list contains every category on time
     */
    @Query("SELECT category FROM ToDo group by category")
    List<String> getListOfCategories();

    @Query("SELECT * FROM ToDo WHERE toDoId = :toDoId")
    LiveData<ToDo> getToDoById(long toDoId);

    @Query("UPDATE ToDo SET toDoDone = :toDoDone WHERE toDoId = :toDoId")
    void setToDoDone(long toDoId, boolean toDoDone);
}
