package de.fhe.pmeplayground.view.toDolist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import de.fhe.pmeplayground.model.ToDo;
import de.fhe.pmeplayground.storage.Repository;

public class ToDoListViewModel extends AndroidViewModel {

    private final Repository repository;

    public ToDoListViewModel(Application application) {
        super(application);
        this.repository = Repository.getRepository(application);
    }

    public List<ToDo> getToDos() {
        return this.repository.getToDos();
    }
}
