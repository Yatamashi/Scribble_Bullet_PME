package de.fhe.pmeplayground.view.toDoList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import de.fhe.pmeplayground.R;
import de.fhe.pmeplayground.model.ToDo;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ToDoViewHolder> {

    // click on title
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

    static int counter = 0; //counter child instances

    //definition of holder
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

    //gives holder
    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = this.inflater.inflate(R.layout.list_item_todo, parent, false);
        return new ToDoViewHolder(itemView, this.toDoTitleClickListener, this.toDoCheckboxClickListener);
    }

    //method sets data from database into holder
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


    /**
     * filters the list at first for checked and not checked for checked state.
     * Afterwars filters for category due to choosen category in ToDoListFragment
     * @param toDoList list comes in an gets filtered
     */
    @SuppressLint("NotifyDataSetChanged")
    public void setToDos(List<ToDo> toDoList)
    {
        List<ToDo> toDoListTemp = toDoList;
        if(ToDoListFragment.switchState)
        {
            toDoListTemp = toDoList.stream().filter(toDo -> !toDo.getToDoDone()).collect(Collectors.toList());
            Log.i("EventCallbacks", "List of not done todos: " + toDoList);
        }

        if(Objects.equals(ToDoListFragment.selectedCategory, "alle ToDos"))
        {
            this.toDoList = toDoListTemp;
        }
        else
        {
            this.toDoList = toDoListTemp.stream().filter(toDo -> toDo.getCategory().equals(ToDoListFragment.selectedCategory) ).collect(Collectors.toList());
        }

        notifyDataSetChanged();
        toDoListTemp = toDoList;
    }

}
