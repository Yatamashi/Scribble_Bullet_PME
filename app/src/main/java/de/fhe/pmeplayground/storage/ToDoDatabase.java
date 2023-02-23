package de.fhe.pmeplayground.storage;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
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
    Used to add some initial data for testing and to try out functionalities
    */
    private static final RoomDatabase.Callback createCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            Log.i( LOG_TAG, "onCreate() called" );

            execute(() -> {
                ToDoDao dao = INSTANCE.toDoDao();

                ToDo toDo = new ToDo("Abwasch", "mit Seife", "Wichtig", "05.07.2023");
                toDo.setCreated( System.currentTimeMillis() );
                toDo.setModified( toDo.getCreated() );
                toDo.setVersion( 1 );
                toDo.setToDoDone(true);
                dao.insert(toDo);

                ToDo toDo1 = new ToDo("Putzen", "das Haus", "Haushalt", "05.07.2023");
                toDo1.setCreated( System.currentTimeMillis() );
                toDo1.setModified( toDo1.getCreated() );
                toDo1.setVersion( 1 );
                toDo1.setToDoDone(false);
                dao.insert(toDo1);

                ToDo toDo2 = new ToDo("Einkaufen", "Milch und Brot", "Haushalt", "05.07.2023");
                toDo2.setCreated( System.currentTimeMillis() );
                toDo2.setModified( toDo2.getCreated() );
                toDo2.setVersion( 1 );
                toDo2.setToDoDone(true);
                dao.insert(toDo2);

                ToDo toDo3 = new ToDo("Auto waschen", "im Waschsalon", "Haushalt", "05.07.2023");
                toDo3.setCreated( System.currentTimeMillis() );
                toDo3.setModified( toDo3.getCreated() );
                toDo3.setVersion( 1 );
                dao.insert(toDo3);

                ToDo toDo4 = new ToDo("Hausaufgaben erledigen", "Mathe und Deutsch", "Schule", "05.07.2023");
                toDo4.setCreated( System.currentTimeMillis() );
                toDo4.setModified( toDo4.getCreated() );
                toDo4.setVersion( 1 );
                dao.insert(toDo4);

                ToDo toDo5 = new ToDo("Geburtstagsgeschenk kaufen", "für Maria", "Wichtig", "05.07.2023");
                toDo5.setCreated( System.currentTimeMillis() );
                toDo5.setModified( toDo5.getCreated() );
                toDo5.setVersion( 1 );
                dao.insert(toDo5);

                ToDo toDo6 = new ToDo("Zahnarzttermin wahrnehmen", "um 14:00 Uhr", "Termine", "26.02.2023");
                toDo6.setCreated( System.currentTimeMillis() );
                toDo6.setModified( toDo6.getCreated() );
                toDo6.setVersion( 1 );
                toDo6.setToDoDone(true);
                dao.insert(toDo6);

                ToDo toDo7 = new ToDo("Kleidung für die Arbeit aussuchen", "keine Jeans", "Arbeit", "05.07.2023");
                toDo7.setCreated( System.currentTimeMillis() );
                toDo7.setModified( toDo7.getCreated() );
                toDo7.setVersion( 1 );
                dao.insert(toDo7);

                ToDo toDo8 = new ToDo("Rechnungen bezahlen", "Strom und Gas", "Termine", "05.07.2023");
                toDo8.setCreated( System.currentTimeMillis() );
                toDo8.setModified( toDo8.getCreated() );
                toDo8.setVersion( 1 );
                dao.insert(toDo8);

                ToDo toDo9 = new ToDo("Einkaufsliste erstellen", "Lebensmittel und Haushaltsartikel", "Haushalt", "26.02.2023");
                toDo9.setCreated( System.currentTimeMillis() );
                toDo9.setModified( toDo9.getCreated() );
                toDo9.setVersion( 1 );
                dao.insert(toDo9);

                ToDo toDo10 = new ToDo("Autoreifen wechseln", "Vorder- und Hinterreifen", "Wichtig", "26.02.2023");
                toDo10.setCreated( System.currentTimeMillis() );
                toDo10.setModified( toDo10.getCreated() );
                toDo10.setVersion( 1 );
                dao.insert(toDo10);

                ToDo toDo11 = new ToDo("Krankenversicherung prüfen", "Beiträge und Leistungen", "Termine", "26.02.2023");
                toDo11.setCreated( System.currentTimeMillis() );
                toDo11.setModified( toDo11.getCreated() );
                toDo11.setVersion( 1 );
                toDo11.setToDoDone(true);
                dao.insert(toDo11);

                ToDo toDo12 = new ToDo("Hausaufgaben machen", "Mathe und Deutsch", "Schule", "25.02.2023");
                toDo12.setCreated( System.currentTimeMillis() );
                toDo12.setModified( toDo12.getCreated() );
                toDo12.setVersion( 1 );
                dao.insert(toDo12);

                ToDo toDo13 = new ToDo("Gartenarbeit", "Unkraut jäten und Rasen mähen", "Haushalt", "27.02.2023");
                toDo13.setCreated(System.currentTimeMillis());
                toDo13.setModified(toDo13.getCreated());
                toDo13.setVersion(1);
                dao.insert(toDo13);

                ToDo toDo14 = new ToDo("Staubsaugen und Wischen", "Wohnzimmer und Schlafzimmer", "Haushalt", "28.02.2023");
                toDo14.setCreated(System.currentTimeMillis());
                toDo14.setModified(toDo14.getCreated());
                toDo14.setVersion(1);
                dao.insert(toDo14);

                ToDo toDo15 = new ToDo("Meeting vorbereiten", "Präsentation und Handouts", "Arbeit", "07.03.2023");
                toDo15.setCreated(System.currentTimeMillis());
                toDo15.setModified(toDo15.getCreated());
                toDo15.setVersion(1);
                dao.insert(toDo15);

                ToDo toDo16 = new ToDo("Kundentermine", "Anrufe und E-Mails", "Arbeit", "08.03.2023");
                toDo16.setCreated(System.currentTimeMillis());
                toDo16.setModified(toDo16.getCreated());
                toDo16.setVersion(1);
                dao.insert(toDo16);

                ToDo toDo17 = new ToDo("Fahrradreifen flicken", "Vorder- und Hinterreifen", "Wichtig", "10.03.2023");
                toDo17.setCreated(System.currentTimeMillis());
                toDo17.setModified(toDo17.getCreated());
                toDo17.setVersion(1);
                dao.insert(toDo17);

                ToDo toDo18 = new ToDo("Steuererklärung", "Papierkram sortieren und Formulare ausfüllen", "Termine", "12.03.2023");
                toDo18.setCreated(System.currentTimeMillis());
                toDo18.setModified(toDo18.getCreated());
                toDo18.setVersion(1);
                dao.insert(toDo18);

                ToDo toDo19 = new ToDo("Rezept ausprobieren", "Pasta mit Pilz-Rahmsauce", "Haushalt", "15.03.2023");
                toDo19.setCreated(System.currentTimeMillis());
                toDo19.setModified(toDo19.getCreated());
                toDo19.setVersion(1);
                dao.insert(toDo19);

                ToDo toDo20 = new ToDo("Bücher ausleihen", "Thriller und Krimis", "Freizeit", "20.03.2023");
                toDo20.setCreated(System.currentTimeMillis());
                toDo20.setModified(toDo20.getCreated());
                toDo20.setVersion(1);
                dao.insert(toDo20);

                ToDo toDo21 = new ToDo("Besprechung mit dem Chef", "Projektstatus besprechen", "Arbeit", "03.03.2023");
                toDo21.setCreated(System.currentTimeMillis());
                toDo21.setModified(toDo21.getCreated());
                toDo21.setVersion(1);
                dao.insert(toDo21);

                ToDo toDo22 = new ToDo("Kundenpräsentation vorbereiten", "Folien erstellen", "Arbeit", "06.03.2023");
                toDo22.setCreated(System.currentTimeMillis());
                toDo22.setModified(toDo22.getCreated());
                toDo22.setVersion(1);
                dao.insert(toDo22);

                ToDo toDo23 = new ToDo("Teammeeting organisieren", "Termin und Ort festlegen", "Arbeit", "09.03.2023");
                toDo23.setCreated(System.currentTimeMillis());
                toDo23.setModified(toDo23.getCreated());
                toDo23.setVersion(1);
                dao.insert(toDo23);

                ToDo toDo24 = new ToDo("Dokumentation aktualisieren", "Neue Funktionen einfügen", "Arbeit", "12.03.2023");
                toDo24.setCreated(System.currentTimeMillis());
                toDo24.setModified(toDo24.getCreated());
                toDo24.setVersion(1);
                dao.insert(toDo24);

                ToDo toDo25 = new ToDo("Praktikum bewerben", "Bewerbungsunterlagen erstellen", "Arbeit", "15.03.2023");
                toDo25.setCreated(System.currentTimeMillis());
                toDo25.setModified(toDo25.getCreated());
                toDo25.setVersion(1);
                dao.insert(toDo25);
            });
        }
    };
}


