package de.fhe.pmeplayground.view.detailview;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import de.fhe.pmeplayground.model.ToDo;
import de.fhe.pmeplayground.storage.Repository;


/**
 * Viewmodel separates the database from fragment
 */
public class DetailViewViewModel extends AndroidViewModel {
    private final Repository repository;

    public DetailViewViewModel(Application application)
    {
        super(application);
        this.repository = Repository.getRepository(application);
    }

    public LiveData<ToDo> getToDo(long toDoId)
    {
        return this.repository.getToDoByIdAsLiveData(toDoId);
    }
}