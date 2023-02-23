package de.fhe.pmeplayground.view.toDoList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import de.fhe.pmeplayground.R;
import de.fhe.pmeplayground.view.core.BaseFragment;


public class ToDoListFragment extends BaseFragment
{
    public static String selectedCategory = "alle ToDos";
    public static Boolean switchState = false;

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

        //for click-listener
        final ToDoListAdapter adapter = new ToDoListAdapter(this.requireActivity(), toDoId -> {
            Bundle args = new Bundle();
            args.putLong("toDoId", toDoId);
            NavController nc = NavHostFragment.findNavController(this);
            nc.navigate(R.id.action_navigation_todo_list_to_navigation_detail_view, args);
        }, toDoListViewModel::setToDoDone
        );

        // --everything for the spinner  --//
        List<String> listOfCategories = toDoListViewModel.getListOfCategories();
        Spinner categorySpinner = root.findViewById(R.id.category_spinner);
        listOfCategories.add(0,"alle ToDos");
        ArrayAdapter<String> toDoListAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listOfCategories);
        toDoListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(toDoListAdapter);

        /*
        sets selected category and calls setTodos for realtime changing the list view
         */
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                selectedCategory = parent.getItemAtPosition(position).toString();
                Log.i("EventCallbacks", "selected category in ToDoList: " + selectedCategory);
                toDoListViewModel.getToDos().observe(getActivity(), adapter::setToDos);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //  --everything for the switch--  //
        /*
         * sets is Checked for adapter to use for filtering
         */
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch toDoDoneSwitch =  root.findViewById(R.id.done_todos_switch);
        toDoDoneSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            switchState = isChecked;
            Log.i("EventCallbacks", "toDoDoneSwitch changed witch: " + switchState);
            toDoListViewModel.getToDos().observe(this.requireActivity(), adapter::setToDos);
        });

        toDoListView.setAdapter(adapter);
        toDoListView.setLayoutManager(new LinearLayoutManager(this.requireActivity()));

        toDoListViewModel.getToDos().observe(this.requireActivity(), adapter::setToDos);


        Button newToDoBtn = root.findViewById(R.id.new_todo_button);
        newToDoBtn.setOnClickListener(this.newTodoButtonClickListener);

        return root;
    }
}


