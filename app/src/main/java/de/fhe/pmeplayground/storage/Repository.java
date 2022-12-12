package de.fhe.pmeplayground.storage;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import de.fhe.pmeplayground.model.ToDo;

public class Repository {

    public static final String LOG_TAG = "ToDoRepository";

    private ToDoDao toDoDao;

    LiveData<List<ToDo>> allToDos;

    private static Repository INSTANCE;

    public static Repository getRepository( Application application )
    {
        if( INSTANCE == null ) {
            synchronized ( Repository.class ) {
                if( INSTANCE == null ) {
                    INSTANCE = new Repository( application );
                }
            }
        }

        return INSTANCE;
    }


    public Repository( Context context )  // was heist Context?
    {
        ToDoDatabase db = ToDoDatabase.getDatabase( context );
        this.toDoDao = db.toDoDao();
        this.getToDosLiveData();

    }

    public List<ToDo> getToDos()
    {
        return this.query( this.toDoDao::getToDos );
    }

    public List<ToDo> getToDosForToDo(String search )
    {
        return this.query( () -> this.toDoDao.getToDosForToDo( search ) );
    }

    public LiveData<List<ToDo>> getToDosLiveData()
    {
        if( this.allToDos == null )
            this.allToDos = this.toDoDao.getToDosLiveDataList();  //TODO: Maybe Wrong

        return this.allToDos;
    }

    public LiveData<ToDo> getToDoByIdAsLiveData(long toDoId)
    {
        return this.queryLiveData(() -> this.toDoDao.getToDoById(toDoId));
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


    private LiveData<ToDo> queryLiveData(Callable<LiveData<ToDo>> query)
    {
        try {
            return ToDoDatabase.executeWithReturn(query);
        }
        catch (ExecutionException | InterruptedException e)
        {
            e.printStackTrace();
        }

        return new MutableLiveData<>();
    }

    public ToDo getLastToDo() {
        try {
            return ToDoDatabase.query( this.toDoDao::getLastEntry );
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return new ToDo("", "", "", "");
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

    public long insertAndWait (ToDo toDo)
    {
        try {
            return ToDoDatabase.executeWithReturn( () -> toDoDao.insert( this.prepareToDoForWriting(toDo)));
        }
        catch (ExecutionException | InterruptedException e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    private ToDo prepareToDoForWriting(ToDo toDo)
    {
        if(toDo.getCreated() < 0)
            toDo.setCreated(System.currentTimeMillis());

        toDo.setModified(toDo.getCreated());
        toDo.setVersion(toDo.getVersion() + 1);

        return toDo;

    }



}
