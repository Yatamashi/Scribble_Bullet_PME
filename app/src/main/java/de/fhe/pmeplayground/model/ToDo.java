package de.fhe.pmeplayground.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity
public class ToDo {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "toDo")
    private String toDo;

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
    private long deadline;

    @NonNull
    @ColumnInfo(name = "version")
    private int version = 0;

    public ToDo(@NonNull String toDo, @NonNull String description, @NonNull String category, @NonNull long deadline) //Konstruktor für die Klasse
    {
        this.toDo = toDo;
        this.description = description;
        this.category = category;
        this.deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getDescription() {
        return toDo;
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
    public String getToDo() {
        return toDo;
    }

    public void setToDo(@NonNull String toDo) {
        this.toDo = toDo;
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

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }


    @Override
    public String toString() {
        return new StringBuilder().append("{")
                .append(toDo).append(" ").append(description)
                .append("(").append(id).append(")")
                .append( " v").append(version).append("}").toString();
    }
}