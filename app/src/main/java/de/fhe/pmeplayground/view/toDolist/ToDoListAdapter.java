package de.fhe.pmeplayground.view.toDolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.fhe.pmeplayground.R;
import de.fhe.pmeplayground.model.ToDo;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ToDoViewHolder> {

    // Klicken vom Titel wird ausgewertet
    public interface ToDoTitleClickListener
    {
    void onClick(long toDoId);
    }

    public interface ToDoCheckBoxClickListener
    {
        void onClick(long toDoId, boolean checked);
    }

    private final ToDoTitleClickListener toDoTitleClickListener;

    private final ToDoCheckBoxClickListener toDoCheckboxClickListener;



    static int counter = 0; //counter für Kinder

    //Definition vom Holder
    static class ToDoViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView toDoTitle;
        private final CheckBox toDoDone;

        private long currentToDoId = -1;
        private ToDoViewHolder(View itemView, ToDoTitleClickListener toDoTitleClickListener, ToDoCheckBoxClickListener toDoCheckBoxClickListener)
        {
            super(itemView);
            this.toDoTitle = itemView.findViewById(R.id.list_item_todo_title);
            this.toDoDone = itemView.findViewById(R.id.list_item_todo_done);
            toDoTitle.setOnClickListener(v -> {
                toDoTitleClickListener.onClick( this.currentToDoId);
            });
            toDoDone.setOnClickListener(v -> {
                toDoCheckBoxClickListener.onClick(this.currentToDoId, toDoDone.isChecked());
            });

        }

    }

    private final LayoutInflater inflater;
    private List<ToDo> toDoList; //Cached copy of Todos

    public ToDoListAdapter(Context context, ToDoTitleClickListener toDoTitleClickListener, ToDoCheckBoxClickListener toDoCheckBoxClickListener)
    {
        this.inflater = LayoutInflater.from(context);
        this.toDoTitleClickListener = toDoTitleClickListener;
        this.toDoCheckboxClickListener = toDoCheckBoxClickListener;
    }

    //gibt einen holder
    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = this.inflater.inflate(R.layout.list_item_todo, parent, false);
        return new ToDoViewHolder(itemView, this.toDoTitleClickListener, this.toDoCheckboxClickListener);

    }

    //Methode setzt vom holder die Parameter jenachdem was die Parameter in der Datenbank für einen Wert haben
    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position)
    {

        if( this.toDoList != null && !this.toDoList.isEmpty())
        {
        ToDo current = this.toDoList.get(position);
        holder.currentToDoId = current.getToDoId();
        holder.toDoTitle.setText(String.format("%s", current.getToDoTitle()));
        holder.toDoDone.setChecked(current.getToDoDone());
        }
        else
        {
            holder.toDoTitle.setText("#leider nix da du...#");
        }
    }

    @Override
    public int getItemCount()
    {
        if(this.toDoList != null&& !this.toDoList.isEmpty())
        {
            return this.toDoList.size();

        }
        else
        {
            return 0;
        }
    }

    public void setToDos(List<ToDo> toDoList){
        this.toDoList = toDoList;
        notifyDataSetChanged();
    }

}
