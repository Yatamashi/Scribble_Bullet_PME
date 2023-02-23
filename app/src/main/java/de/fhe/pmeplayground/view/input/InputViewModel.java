package de.fhe.pmeplayground.view.input;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.AndroidViewModel;
import java.util.List;
import de.fhe.pmeplayground.model.ToDo;
import de.fhe.pmeplayground.storage.Repository;

/**
 *This is the InputViewModel class, which is responsible for handling user input and providing data to the InputFragment.
 *It extends the AndroidViewModel class and provides methods for saving a new To Do item and getting a list of categories from the repository.
 *The repository is obtained through a static method call to the Repository class.
 */
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
        return "ToDo saved - id: " + newToDoId;
    }

    public List<String> getListOfCategories()
    {
        Log.i("EventCallbacks", "InputViewModel calls getListOfCategories ");
        return this.toDoRepository.getListOfCategories();

    }

}

