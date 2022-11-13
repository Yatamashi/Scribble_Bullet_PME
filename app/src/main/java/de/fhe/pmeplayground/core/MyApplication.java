package de.fhe.pmeplayground.core;

import android.app.Application;
import android.util.Log;

import java.util.List;
import java.util.Random;

import de.fhe.pmeplayground.model.Beer;
import de.fhe.pmeplayground.model.Contact;
import de.fhe.pmeplayground.storage.Repository;

public class MyApplication extends Application {

    private static final String LOG_TAG = "MyApp";

    private SettingsHandler settingsHandler;

    @Override
    public void onCreate() {
        super.onCreate();

        this.settingsHandler = new SettingsHandler(this);

        testDatabase();

        Log.i( LOG_TAG, "On Create finished.");
    }

    /*
        Simple DB Test - will be removed in next version
     */
    private void testDatabase() {

        // Create Repo instance - which in turn will init the Contact DB
        Repository contactRepository = new Repository( this );

        // Query all beers
        List<Beer> beers = contactRepository.getBeers();
        Log.i(LOG_TAG, beers.toString() );



        // Query all contacts and log them
        List<Contact> allContacts = contactRepository.getContacts();
        Log.i(LOG_TAG, allContacts.toString() );

        // Do we have any contacts at all?
        // During first app run, 10 contacts will be created. However, this happens asynchronous so
        // we might end up here before the DB got filled. So, better check it.
        if( allContacts.size() > 0 ) {

            // Ok, lets get all contacts sorted by lastname
            allContacts = contactRepository.getContactsSortedByLastname();
            Log.i(LOG_TAG, allContacts.toString() );

            // Get the latest added contact, e.g. the one with the highest primary key value
            Contact lastContact = contactRepository.getLastContact();
            Log.i(LOG_TAG, "" + lastContact);

            // Change its lastname to something random
            lastContact.setLastname("Lastname " + new Random().nextInt(1000));
            contactRepository.update(lastContact);

            // Wait for the async update operation to finish....
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Re-query the latest contact and check if the change was written successfully
            lastContact = contactRepository.getLastContact();
            Log.i(LOG_TAG, lastContact.toString());

            // Get all contacts with lastname like 'Last*'
            List<Contact> nameSearchResult = contactRepository.getContactsForLastname( "Last%" );
            Log.i(LOG_TAG, nameSearchResult.toString() );
        }
    }

    public SettingsHandler getSettingsHandler() {
        return settingsHandler;
    }
}
