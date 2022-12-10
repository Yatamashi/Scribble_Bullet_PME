package de.fhe.pmeplayground.view.toDolist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.fhe.pmeplayground.R;
import de.fhe.pmeplayground.view.core.BaseFragment;

public class ToDoListFragment extends BaseFragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_todo_list, container, false);


        ToDoListViewModel toDoListViewModel = this.getViewModel(ToDoListViewModel.class);


        RecyclerView toDoListView = root.findViewById(R.id.list_view_todos);


        final ToDoListAdapter adapter = new ToDoListAdapter(this.requireActivity());

        toDoListView.setAdapter(adapter);
        toDoListView.setLayoutManager(new LinearLayoutManager(this.requireActivity()));

        toDoListViewModel.getToDos().observe(this.requireActivity(), adapter::setToDos);



        return root;
    }
}

