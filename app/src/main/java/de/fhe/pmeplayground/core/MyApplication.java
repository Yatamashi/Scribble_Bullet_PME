package de.fhe.pmeplayground.core;

import android.app.Application;
import android.util.Log;

import java.util.List;
import java.util.Random;

import de.fhe.pmeplayground.model.ToDo;
import de.fhe.pmeplayground.storage.Repository;

public class MyApplication extends Application {

    private static final String LOG_TAG = "Scribble Bullet";

    private SettingsHandler settingsHandler;

    @Override
    public void onCreate() {
        super.onCreate();

        this.settingsHandler = new SettingsHandler(this);

        //testDatabase();

        Log.i( LOG_TAG, "On Create finished.");
    }

    /*
        Simple DB Test - will be removed in next version
     */

   /* private void testDatabase() {

        // Create Repo instance - which in turn will init the ToDo DB
        Repository toDoRepository = new Repository( this );

        // Query all toDos and log them
        List<ToDo> allToDos = toDoRepository.getToDos();
        Log.i(LOG_TAG, allToDos.toString() );


        // Do we have any toDos at all?
        // During first app run, 10 toDos will be created. However, this happens asynchronous so
        // we might end up here before the DB got filled. So, better check it.
        if( allToDos.size() > 0 ) {

            // Ok, lets get all toDos sorted by toDo
            allToDos = toDoRepository.getToDosSortedByToDo();
            Log.i(LOG_TAG, allToDos.toString() );

            // Get the latest added toDo, e.g. the one with the highest primary key value
            ToDo lastToDo = toDoRepository.getLastToDo();
            Log.i(LOG_TAG, "" + lastToDo);

            // Change its toDo to something random
            lastToDo.setToDoTitle("ToDo " + new Random().nextInt(1000));
            toDoRepository.update(lastToDo);

            // Wait for the async update operation to finish....
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Re-query the latest toDo and check if the change was written successfully
            lastToDo = toDoRepository.getLastToDo();
            Log.i(LOG_TAG, lastToDo.toString());

            // Get all toDos with toDo like 'Last*'
            List<ToDo> nameSearchResult = toDoRepository.getToDosForToDo( "Last%" );
            Log.i(LOG_TAG, nameSearchResult.toString() );
        }
    }


*/
   public SettingsHandler getSettingsHandler() {
       return settingsHandler;
   }



}
