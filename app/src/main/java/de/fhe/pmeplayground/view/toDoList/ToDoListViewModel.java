package de.fhe.pmeplayground.view.toDoList;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import de.fhe.pmeplayground.model.ToDo;
import de.fhe.pmeplayground.storage.Repository;

public class ToDoListViewModel extends AndroidViewModel {

    private final Repository repository;

    public ToDoListViewModel(Application application) {
        super(application);
        this.repository = Repository.getRepository(application);
    }

    public LiveData<List<ToDo>> getToDos() {
        return this.repository.getToDosLiveData();
    }


    //uses function in repository to change data
    public void setToDoDone( long toDoId, boolean toDoDone)
    {
        repository.setToDoDone(toDoId, toDoDone);
    }

    public List<String> getListOfCategories() {
        return this.repository.getListOfCategories();
    }
}
