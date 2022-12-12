package de.fhe.pmeplayground.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * class for ToDos with parameters, setters getters, generates tables
 */
@Entity
public class ToDo {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "toDoId")
    private int toDoId;

    @NonNull
    @ColumnInfo(name = "toDoTitle")
    private String toDoTitle;

    @ColumnInfo(name = "toDoDone")
    private boolean toDoDone;

    @NonNull
    @ColumnInfo(name = "description")
    private String description;

    @NonNull
    @ColumnInfo(name = "category")
    private String category;

    @NonNull
    @ColumnInfo(name = "created")
    private long created;

    @NonNull
    @ColumnInfo(name = "modified")
    private long modified;

    @NonNull
    @ColumnInfo(name = "deadline")
    private String deadline;

    @NonNull
    @ColumnInfo(name = "version")
    private int version = 0;

    public ToDo(@NonNull String toDoTitle, @NonNull String description, @NonNull String category, @NonNull String deadline) //Konstruktor für die Klasse
    {
        this.toDoTitle = toDoTitle;
        this.description = description;
        this.category = category;
        this.deadline = deadline;
        this.created = ( System.currentTimeMillis() );
        this.modified = ( System.currentTimeMillis() );
       // this.toDoDone = false;
    }

    public int getToDoId() {
        return toDoId;
    }

    public void setToDoId(int toDoId) {
        this.toDoId = toDoId;
    }

    @NonNull
    public String getDescription() {
        return toDoTitle;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @NonNull
    public String getCategory()
    {
        return category;
    }

    @NonNull
    public void setCategory(@NonNull String category)
    {
        this.category = category;
    }

    @NonNull
    public String getToDoTitle() {
        return toDoTitle;
    }

    public void setToDoTitle(@NonNull String toDoTitle) {
        this.toDoTitle = toDoTitle;
    }


    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getModified() {
        return modified;
    }

    public void setModified(long modified) {
        this.modified = modified;
    }

    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public boolean isToDoDone() {
        return toDoDone;
    }

    public void setToDoDone(boolean toDoDone)
    {
        this.toDoDone = toDoDone;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("{")
                .append(toDoTitle).append(" ").append(description)
                .append("(").append(toDoId).append(")")
                .append( " v").append(version).append("}").toString();
    }
}