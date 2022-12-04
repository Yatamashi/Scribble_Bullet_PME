package de.fhe.pmeplayground.storage;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import de.fhe.pmeplayground.model.ToDo;

public class Repository {

    public static final String LOG_TAG = "ToDoRepository";

    private ToDoDao toDoDao;

    public Repository( Context context )  // was heist Context?
    {
        ToDoDatabase db = ToDoDatabase.getDatabase( context );  //ToDo: Tododatabase rein
        this.toDoDao = db.toDoDao(); //ToDo database ändern

    }


    public List<ToDo> getToDos()
    {
        return this.query( () -> this.toDoDao.getToDos() );
    }

    // public List<ToDo> ge

    public List<ToDo> getToDosForToDo(String search )
    {
        return this.query( () -> this.toDoDao.getToDosForToDo( search ) );
    }

    public List<ToDo> getToDosSortedByToDo()
    {
        return this.query( () -> this.toDoDao.getToDosSortedByToDo() );
    }

    private <T> List<T> query( Callable<List<T>> query )
    {
        try {
            return ToDoDatabase.query( query );
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public ToDo getLastToDo() {
        try {
            return ToDoDatabase.query( this.toDoDao::getLastEntry );
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return new ToDo("", "", "", 2000);
    }

    public void update(ToDo toDo) {
        toDo.setModified( System.currentTimeMillis() );
        toDo.setVersion( toDo.getVersion() + 1 );

        ToDoDatabase.execute( () -> toDoDao.update( toDo ) );
    }

    public void insert(ToDo toDo) {
        toDo.setCreated( System.currentTimeMillis() );
        toDo.setModified( toDo.getCreated() );
        toDo.setVersion( 1 );

        ToDoDatabase.execute( () -> toDoDao.insert( toDo ) );
    }


}


/*
public class Repository {

    public static final String LOG_TAG = "ToDoRepository";

    private ContactDao toDoDao;
    private BeerDao beerDao;

    public Repository( Context context ) {
        ContactDatabase db = ContactDatabase.getDatabase( context );
        this.toDoDao = db.toDoDao();
        this.beerDao = db.beerDao();
    }

    public List<Beer> getBeers() {
        return this.query( () -> this.beerDao.getBeers() );
    }

    public List<ToDo> getToDos()
    {
        return this.query( () -> this.toDoDao.getToDos() );
    }

    public List<ToDo> getToDosForToDo(String search )
    {
        return this.query( () -> this.toDoDao.getToDosForToDo( search ) );
    }

    public List<ToDo> getToDosSortedByToDo()
    {
        return this.query( () -> this.toDoDao.getContactSortedByLastname() );
    }

    private <T> List<T> query( Callable<List<T>> query )
    {
        try {
            return ContactDatabase.query( query );
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public ToDo getLastContact() {
        try {
            return ContactDatabase.query( this.toDoDao::getLastEntry );
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return new ToDo("", "");
    }

    public void update(ToDo toDo) {
        toDo.setModified( System.currentTimeMillis() );
        toDo.setVersion( toDo.getVersion() + 1 );

        ContactDatabase.execute( () -> toDoDao.update( toDo ) );
    }

    public void insert(ToDo toDo) {
        toDo.setCreated( System.currentTimeMillis() );
        toDo.setModified( toDo.getCreated() );
        toDo.setVersion( 1 );

        ContactDatabase.execute( () -> toDoDao.insert( toDo ) );
    }
}

*/