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

    static int counter = 0; //counter für Kinder

    //Definition vom Holder
    static class ToDoViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView toDoTitle;
       // private final CheckBox done;

        private ToDoViewHolder(View itemView)
        {
            super(itemView);
            this.toDoTitle = itemView.findViewById(R.id.list_item_todo_title);
           // this.done = itemView.findViewById(R.id.list_item_todo_done);
        }
    }

    private final LayoutInflater inflater;
    private List<ToDo> toDoList; //Cached copy of Todos

    public ToDoListAdapter(Context context)
    {
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = this.inflater.inflate(R.layout.list_item_todo, parent, false);
        return new ToDoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position)
    {

        if( this.toDoList != null && !this.toDoList.isEmpty())
        {

        ToDo current = this.toDoList.get(position);
        holder.toDoTitle.setText(String.format("%s", current.getToDoTitle()));

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