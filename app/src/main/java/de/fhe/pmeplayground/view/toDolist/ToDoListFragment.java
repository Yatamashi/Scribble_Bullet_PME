package de.fhe.pmeplayground.view.toDolist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
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

        // ergänzt für Clicklistener
        final ToDoListAdapter adapter = new ToDoListAdapter(this.requireActivity(), toDoId -> {
            Bundle args = new Bundle();
            args.putLong("toDoId", toDoId);
            NavController nc = NavHostFragment.findNavController(this);
            nc.navigate(R.id.action_navigation_todo_list_to_navigation_detail_view, args);
        }
        );

        toDoListView.setAdapter(adapter);
        toDoListView.setLayoutManager(new LinearLayoutManager(this.requireActivity()));

        toDoListViewModel.getToDos().observe(this.requireActivity(), adapter::setToDos);



        return root;
    }
}

