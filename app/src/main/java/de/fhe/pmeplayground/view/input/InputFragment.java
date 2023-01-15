package de.fhe.pmeplayground.view.input;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;
import de.fhe.pmeplayground.R;
import de.fhe.pmeplayground.model.ToDo;
import de.fhe.pmeplayground.view.core.BaseFragment;

public class InputFragment extends BaseFragment {

    private InputViewModel inputViewModel;
    private EditText toDoField;
    private EditText descriptionField;
    private EditText deadlineField;
    private EditText categoryField;
    private DatePicker dpDeadline;
    private Button btnSelectDeadline;


    private final View.OnClickListener saveButtonClickListener = v -> {

        if( v.getId() == R.id.btn_save_todo && !toDoField.getText().toString().equals(""))
        {
            ToDo newToDo = new ToDo(
                    toDoField.getText().toString(),
                    descriptionField.getText().toString(),
                    categoryField.getText().toString(),
                    deadlineField.getText().toString()
            );

            String returnValue = inputViewModel.saveToDo( newToDo );

            hideKeyboard( this.requireContext(), v );
            Snackbar.make(v, returnValue, Snackbar.LENGTH_SHORT).show();

            //for navigating to list after new input
            Bundle args = new Bundle();
            NavController nc = NavHostFragment.findNavController(this);
            nc.navigate(R.id.action_navigation_input_to_navigation_todo_list, args);

        }
        else
        {
            Snackbar.make(v, "Bitte ein ToDo eingeben", Snackbar.LENGTH_LONG).show();
        }
    };




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        inputViewModel = this.getViewModel(InputViewModel.class);

        View root = inflater.inflate(R.layout.fragment_input, container, false);

        this.toDoField = root.findViewById(R.id.et_todo);
        this.descriptionField = root.findViewById(R.id.et_description);
        this.categoryField = root.findViewById(R.id.et_category);
        this.deadlineField = root.findViewById(R.id.et_deadline);
        this.dpDeadline = root.findViewById(R.id.dp_deadline);
        this.btnSelectDeadline = root.findViewById(R.id.btn_select_deadline);

//Alles zum Spinner
        Log.i( "EventCallbacks", "vor dem getList Aufruf.");
        List<String> listOfCategories = inputViewModel.getListOfCategories();
        Log.i( "EventCallbacks", "nach dem getList Aufruf.");
        Spinner categorySpinner = root.findViewById(R.id.category_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listOfCategories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = parent.getItemAtPosition(position).toString();
                categoryField.setText(selectedCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                categoryField.setText("");
            }
        });

        
//Ende Spinner
        //Kalender
        Log.i("EventCallbacks", "soll kalender visible GONE machen ");
        dpDeadline.setVisibility(View.GONE);
        btnSelectDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("EventCallbacks", "soll kalender visible machen ");


                dpDeadline.setVisibility(View.VISIBLE);
                hideKeyboard(getView().getContext(), v );

                dpDeadline.init(dpDeadline.getYear(), dpDeadline.getMonth(), dpDeadline.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                        String date = format.format(calendar.getTime());
                        Log.i("EventCallbacks", "Angegebenes Datum: " + date);
                        deadlineField.setText(date);
                        dpDeadline.setVisibility(View.GONE);
                    }
                });
            }
        });
        //Kalender ende

        Button saveBtn = root.findViewById(R.id.btn_save_todo);
        saveBtn.setOnClickListener(this.saveButtonClickListener);


        return root;
    }
}