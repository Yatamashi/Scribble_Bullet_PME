package de.fhe.pmeplayground.storage;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.github.javafaker.Faker;

import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.fhe.pmeplayground.model.ToDo;

@Database( entities = {ToDo.class}, version = 1 )
public abstract class ToDoDatabase extends RoomDatabase {  //TODO was geht nicht mit Schema Export???

    private static final String LOG_TAG = "ToDoDB";

    /*
         DAO reference, will be filled by Android
     */
    public abstract ToDoDao toDoDao();


    /*
      Executor service to perform database operations asynchronous and independent from UI thread
     */
    private static final int NUMBER_OF_THREADS = 4;
    private static final ExecutorService databaseExecutor =
            Executors.newFixedThreadPool( NUMBER_OF_THREADS );

    /*
        Singleton Instance
     */
    private static volatile ToDoDatabase INSTANCE;

    /*
        Helper methods to ease external usage of ExecutorService
        e.g. perform async database operations
     */
    public static <T> T query(Callable<T> task)
            throws ExecutionException, InterruptedException
    {
        return databaseExecutor.invokeAny(Collections.singletonList( task ) );
    }

    public static void execute( Runnable runnable )
    {
        databaseExecutor.execute( runnable );
    }

    public static <T> T executeWithReturn(Callable<T> task)
        throws ExecutionException, InterruptedException
    {
        return databaseExecutor.invokeAny(Collections.singletonList(task));
    }

    /*
        Singleton 'getInstance' method to create database instance thereby opening and, if not
        already done, init the database. Note the 'createCallback'.
     */
    static ToDoDatabase getDatabase(final Context context) {
        Log.i( LOG_TAG, "getDatabase() called" );
        if (INSTANCE == null) {
            synchronized (ToDoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ToDoDatabase.class, "toDo_db")
                            .addCallback(createCallback) // See below
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    /*
    Create DB Callback
    Used to add some initial data
 */
    private static final RoomDatabase.Callback createCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            Log.i( LOG_TAG, "onCreate() called" );

            execute(() -> {
                ToDoDao dao = INSTANCE.toDoDao();

               /* Faker faker = Faker.instance();
                for (int i = 0; i < 10; i++)
                {
                    ToDo toDo = new ToDo(faker.name().lastName(), faker.name().firstName(), faker.name().bloodGroup(), faker.name().username());
                    toDo.setCreated( System.currentTimeMillis() );
                    toDo.setModified( toDo.getCreated() );
                    toDo.setVersion( 1 );
                    dao.insert(toDo);
                }
                Log.i(LOG_TAG, "Inserted 10 toDos to DB");
                */
                ToDo toDo = new ToDo("Abwasch", "mit Seife", "Wichtig", "20230401");
                toDo.setCreated( System.currentTimeMillis() );
                toDo.setModified( toDo.getCreated() );
                toDo.setVersion( 1 );
                dao.insert(toDo);

                ToDo toDo1 = new ToDo("Putzen", "das Haus", "bis morgen", "20230401");
                toDo1.setCreated( System.currentTimeMillis() );
                toDo1.setModified( toDo1.getCreated() );
                toDo1.setVersion( 1 );
                dao.insert(toDo1);

                ToDo toDo2 = new ToDo("Einkaufen", "Milch und Brot", "heute Nachmittag", "20230401");
                toDo2.setCreated( System.currentTimeMillis() );
                toDo2.setModified( toDo2.getCreated() );
                toDo2.setVersion( 1 );
                dao.insert(toDo2);

                ToDo toDo3 = new ToDo("Auto waschen", "im Waschsalon", "bis Samstag", "20230401");
                toDo3.setCreated( System.currentTimeMillis() );
                toDo3.setModified( toDo3.getCreated() );
                toDo3.setVersion( 1 );
                dao.insert(toDo3);

                ToDo toDo4 = new ToDo("Hausaufgaben erledigen", "Mathe und Deutsch", "bis Montag", "20230401");
                toDo4.setCreated( System.currentTimeMillis() );
                toDo4.setModified( toDo4.getCreated() );
                toDo4.setVersion( 1 );
                dao.insert(toDo4);

                ToDo toDo5 = new ToDo("Geburtstagsgeschenk kaufen", "für Maria", "bis Freitag", "20230401");
                toDo5.setCreated( System.currentTimeMillis() );
                toDo5.setModified( toDo5.getCreated() );
                toDo5.setVersion( 1 );
                dao.insert(toDo5);

                ToDo toDo6 = new ToDo("Zahnarzttermin wahrnehmen", "um 14:00 Uhr", "Wichtig", "20230401");
                toDo6.setCreated( System.currentTimeMillis() );
                toDo6.setModified( toDo6.getCreated() );
                toDo6.setVersion( 1 );
                dao.insert(toDo6);

                ToDo toDo7 = new ToDo("Kleidung für die Arbeit aussuchen", "keine Jeans", "bis morgen früh", "20230401");
                toDo7.setCreated( System.currentTimeMillis() );
                toDo7.setModified( toDo7.getCreated() );
                toDo7.setVersion( 1 );
                dao.insert(toDo7);

                ToDo toDo8 = new ToDo("Rechnungen bezahlen", "Strom und Gas", "bis zum Ende des Monats", "20230401");
                toDo8.setCreated( System.currentTimeMillis() );
                toDo8.setModified( toDo8.getCreated() );
                toDo8.setVersion( 1 );
                dao.insert(toDo8);


            });
        }
    };

    /*
        Create DB Callback
        Used to add some initial data
     */

}


