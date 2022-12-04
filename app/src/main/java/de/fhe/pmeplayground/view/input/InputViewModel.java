package de.fhe.pmeplayground.view.input;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import de.fhe.pmeplayground.model.Contact;
import de.fhe.pmeplayground.storage.Repository;

public class InputViewModel extends AndroidViewModel {

    private final Repository contactRepository;

    public InputViewModel(Application application)
    {
        super(application);
        this.contactRepository = Repository.getRepository(application);
    }

    public String saveContact( Contact contact )
    {
        long newContactId = this.contactRepository.insertAndWait( contact );
        return "Contact saved - id: " + newContactId;
    }
}

