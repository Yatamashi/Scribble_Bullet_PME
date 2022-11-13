package de.fhe.pmeplayground.storage;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import de.fhe.pmeplayground.model.Beer;
import de.fhe.pmeplayground.model.Contact;

public class Repository {

    public static final String LOG_TAG = "ContactRepository";

    private ContactDao contactDao;
    private BeerDao beerDao;

    public Repository( Context context ) {
        ContactDatabase db = ContactDatabase.getDatabase( context );
        this.contactDao = db.contactDao();
        this.beerDao = db.beerDao();
    }

    public List<Beer> getBeers() {
        return this.query( () -> this.beerDao.getBeers() );
    }

    public List<Contact> getContacts()
    {
        return this.query( () -> this.contactDao.getContacts() );
    }

    public List<Contact> getContactsForLastname(String search )
    {
        return this.query( () -> this.contactDao.getContactsForLastname( search ) );
    }

    public List<Contact> getContactsSortedByLastname()
    {
        return this.query( () -> this.contactDao.getContactSortedByLastname() );
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

    public Contact getLastContact() {
        try {
            return ContactDatabase.query( this.contactDao::getLastEntry );
        }
        catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return new Contact("", "");
    }

    public void update(Contact contact) {
        contact.setModified( System.currentTimeMillis() );
        contact.setVersion( contact.getVersion() + 1 );

        ContactDatabase.execute( () -> contactDao.update( contact ) );
    }

    public void insert(Contact contact) {
        contact.setCreated( System.currentTimeMillis() );
        contact.setModified( contact.getCreated() );
        contact.setVersion( 1 );

        ContactDatabase.execute( () -> contactDao.insert( contact ) );
    }
}

