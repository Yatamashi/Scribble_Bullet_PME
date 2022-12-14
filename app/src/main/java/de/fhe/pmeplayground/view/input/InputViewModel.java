package de.fhe.pmeplayground.view.input;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import de.fhe.pmeplayground.model.ToDo;
import de.fhe.pmeplayground.storage.Repository;

public class InputViewModel extends AndroidViewModel {

    private final Repository toDoRepository;

    public InputViewModel(Application application)
    {
        super(application);
        this.toDoRepository = Repository.getRepository(application);
    }

    public String saveToDo( ToDo toDo )
    {
        long newToDoId = this.toDoRepository.insertAndWait( toDo );
        return "Contact saved - id: " + newToDoId;
    }
}

