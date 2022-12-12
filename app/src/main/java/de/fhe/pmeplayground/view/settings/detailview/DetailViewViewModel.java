package de.fhe.pmeplayground.view.settings.detailview;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.javafaker.App;

import de.fhe.pmeplayground.model.ToDo;
import de.fhe.pmeplayground.storage.Repository;

public class DetailViewViewModel extends AndroidViewModel {
    private final Repository repository;

    public DetailViewViewModel(Application application)
    {
        super(application); // Was bedeutet dieses Super?
        this.repository = Repository.getRepository(application);
    }

    public LiveData<ToDo> getToDo(long toDoId)
    {
        return this.repository.getToDoByIdAsLiveData(toDoId);
    }
}