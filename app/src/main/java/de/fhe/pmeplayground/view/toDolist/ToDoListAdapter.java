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

    // Klicken wird ausgewertet
    public interface ToDoClickListener
    {
    void onClick(long toDoId);
    }

    private final ToDoClickListener toDoClickListener;



    static int counter = 0; //counter für Kinder

    //Definition vom Holder
    static class ToDoViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView toDoTitle;
      //  private final CheckBox toDoDone;

        private long currentToDoId = -1;
        private ToDoViewHolder(View itemView, ToDoClickListener toDoClickListener)
        {
            super(itemView);
            this.toDoTitle = itemView.findViewById(R.id.list_item_todo_title);
           // this.toDoDone = itemView.findViewById(R.id.list_item_todo_done);
            itemView.setOnClickListener(v -> {
                toDoClickListener.onClick( this.currentToDoId);
            });

        }

    }

    private final LayoutInflater inflater;
    private List<ToDo> toDoList; //Cached copy of Todos

    public ToDoListAdapter(Context context, ToDoClickListener toDoClickListener)
    {
        this.inflater = LayoutInflater.from(context);
        this.toDoClickListener = toDoClickListener;
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = this.inflater.inflate(R.layout.list_item_todo, parent, false);
        return new ToDoViewHolder(itemView, this.toDoClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position)
    {

        if( this.toDoList != null && !this.toDoList.isEmpty())
        {

        ToDo current = this.toDoList.get(position);
        holder.currentToDoId = current.getId();
        holder.toDoTitle.setText(String.format("%s", current.getToDoTitle()));
       // holder.toDoDone.setChecked(false);

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
