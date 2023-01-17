package de.fhe.pmeplayground.view.toDoList;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.stream.Collectors;

import de.fhe.pmeplayground.R;
import de.fhe.pmeplayground.model.ToDo;
import de.fhe.pmeplayground.view.core.BaseFragment;

public class ToDoListFragment extends BaseFragment {


    public static String selectedCategory = "alle ToDos";



    private final View.OnClickListener newTodoButtonClickListener = v -> {

            //for navigating to list after new input
            Bundle args = new Bundle();
            NavController nc = NavHostFragment.findNavController(this);
            nc.navigate(R.id.action_navigation_todo_list_to_navigation_input, args);
    };


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
        }, (toDoId, checked) -> {
            toDoListViewModel.setToDoDone(toDoId, checked);

        }
        );

        //Alles zum Spinner
        List<String> listOfCategories = toDoListViewModel.getListOfCategories();
        Spinner categorySpinner = root.findViewById(R.id.category_spinner);
        listOfCategories.add(0,"alle ToDos");
        ArrayAdapter<String> toDoListAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listOfCategories);
        toDoListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(toDoListAdapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String temp = selectedCategory;
                selectedCategory = parent.getItemAtPosition(position).toString();

                   Log.i("EventCallbacks", "selected category in ToDoList: " + selectedCategory);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //für gefiltert nach category


        toDoListView.setAdapter(adapter);
        toDoListView.setLayoutManager(new LinearLayoutManager(this.requireActivity()));

        toDoListViewModel.getToDos().observe(this.requireActivity(), adapter::setToDos);

        Button newToDoBtn = root.findViewById(R.id.new_todo_button);
        newToDoBtn.setOnClickListener(this.newTodoButtonClickListener);

        return root;
    }
}

//TODO: Layout mit Landscape anpassen
//TODO: Refresh wenn category ausgewählt wurde
//TODO: Layout anpassen mit new todo und spinner schöner
//TODO: eventuell filter für erledigte Todos