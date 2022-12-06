package de.fhe.pmeplayground.view.toDolist;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import de.fhe.pmeplayground.R;
import de.fhe.pmeplayground.model.ToDo;
import de.fhe.pmeplayground.view.core.BaseFragment;

public class ToDoListFragment extends BaseFragment {

    private ToDoListViewModel toDoListViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        toDoListViewModel = this.getViewModel(ToDoListViewModel.class);

        View root = inflater.inflate(R.layout.fragment_todo_list, container, false);

        TextView textToDos = root.findViewById( R.id.text_todos );
        textToDos.setMovementMethod(new ScrollingMovementMethod());

        List<ToDo> toDoList = toDoListViewModel.getToDos();

        StringBuilder sb = new StringBuilder();


        for( ToDo c: toDoList ) {
            sb.append(c.getId()).append(": ")
                    .append( c.getToDoTitle()).append(" ")
                    .append(c.getDescription()).append("\n");
        }

        textToDos.setText( sb.toString() );

        return root;
    }
}

