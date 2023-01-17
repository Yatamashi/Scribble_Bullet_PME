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

/**
 * Database for the application.
 * Also contains some Testdata
 */
@Database( entities = {ToDo.class}, version = 1 )
public abstract class ToDoDatabase extends RoomDatabase {

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


                ToDo toDo = new ToDo("Abwasch", "mit Seife", "Wichtig", "20230401");
                toDo.setCreated( System.currentTimeMillis() );
                toDo.setModified( toDo.getCreated() );
                toDo.setVersion( 1 );
                toDo.setToDoDone(true);
                dao.insert(toDo);


                ToDo toDo1 = new ToDo("Putzen", "das Haus", "Termine", "20230401");
                toDo1.setCreated( System.currentTimeMillis() );
                toDo1.setModified( toDo1.getCreated() );
                toDo1.setVersion( 1 );
                toDo1.setToDoDone(false);
                dao.insert(toDo1);

                ToDo toDo2 = new ToDo("Einkaufen", "Milch und Brot", "Haushalt", "20230401");
                toDo2.setCreated( System.currentTimeMillis() );
                toDo2.setModified( toDo2.getCreated() );
                toDo2.setVersion( 1 );
                dao.insert(toDo2);

                ToDo toDo3 = new ToDo("Auto waschen", "im Waschsalon", "Haushalt", "20230401");
                toDo3.setCreated( System.currentTimeMillis() );
                toDo3.setModified( toDo3.getCreated() );
                toDo3.setVersion( 1 );
                dao.insert(toDo3);

                ToDo toDo4 = new ToDo("Hausaufgaben erledigen", "Mathe und Deutsch", "Schule", "20230401");
                toDo4.setCreated( System.currentTimeMillis() );
                toDo4.setModified( toDo4.getCreated() );
                toDo4.setVersion( 1 );
                dao.insert(toDo4);

                ToDo toDo5 = new ToDo("Geburtstagsgeschenk kaufen", "für Maria", "Wichtig", "20230401");
                toDo5.setCreated( System.currentTimeMillis() );
                toDo5.setModified( toDo5.getCreated() );
                toDo5.setVersion( 1 );
                dao.insert(toDo5);

                ToDo toDo6 = new ToDo("Zahnarzttermin wahrnehmen", "um 14:00 Uhr", "Wichtig", "20230401");
                toDo6.setCreated( System.currentTimeMillis() );
                toDo6.setModified( toDo6.getCreated() );
                toDo6.setVersion( 1 );
                dao.insert(toDo6);

                ToDo toDo7 = new ToDo("Kleidung für die Arbeit aussuchen", "keine Jeans", "Arbeit", "20230401");
                toDo7.setCreated( System.currentTimeMillis() );
                toDo7.setModified( toDo7.getCreated() );
                toDo7.setVersion( 1 );
                dao.insert(toDo7);

                ToDo toDo8 = new ToDo("Rechnungen bezahlen", "Strom und Gas", "Termine", "20230401");
                toDo8.setCreated( System.currentTimeMillis() );
                toDo8.setModified( toDo8.getCreated() );
                toDo8.setVersion( 1 );
                dao.insert(toDo8);

                ToDo toDo9 = new ToDo("Einkaufsliste erstellen", "Lebensmittel und Haushaltsartikel", "Haushalt", "20230402");
                toDo9.setCreated( System.currentTimeMillis() );
                toDo9.setModified( toDo9.getCreated() );
                toDo9.setVersion( 1 );
                dao.insert(toDo9);

                ToDo toDo10 = new ToDo("Autoreifen wechseln", "Vorder- und Hinterreifen", "Wichtig", "20230403");
                toDo10.setCreated( System.currentTimeMillis() );
                toDo10.setModified( toDo10.getCreated() );
                toDo10.setVersion( 1 );
                dao.insert(toDo10);

                ToDo toDo11 = new ToDo("Krankenversicherung prüfen", "Beiträge und Leistungen", "Termine", "26.02.2023");
                toDo11.setCreated( System.currentTimeMillis() );
                toDo11.setModified( toDo11.getCreated() );
                toDo11.setVersion( 1 );
                dao.insert(toDo11);

                ToDo toDo12 = new ToDo("Hausaufgaben machen", "Mathe und Deutsch", "Wichtig", "25.02.2023");
                toDo12.setCreated( System.currentTimeMillis() );
                toDo12.setModified( toDo12.getCreated() );
                toDo12.setVersion( 1 );
                dao.insert(toDo12);


            });
        }
    };


}


