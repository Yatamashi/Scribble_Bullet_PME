package de.fhe.pmeplayground.view.input;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import androidx.annotation.NonNull;
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

    private final View.OnClickListener saveButtonClickListener = v -> {

        if( v.getId() == R.id.btn_save_todo)
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
        }
    };

    public InputFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        inputViewModel = this.getViewModel(InputViewModel.class);

        View root = inflater.inflate(R.layout.fragment_input, container, false);

        this.toDoField = root.findViewById(R.id.et_todo);
        this.descriptionField = root.findViewById(R.id.et_description);
        this.categoryField = root.findViewById(R.id.et_category);
        this.deadlineField = root.findViewById(R.id.et_deadline);

//Alles zum Spinner
        List<String> listOfCategories = new ArrayList<>(Arrays.asList("C", "C++", "Java"));//inputViewModel.getListOfCategories(); TODO: Fehler beim Datendurchschieben finden

        Spinner spinner = root.findViewById(R.id.category_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listOfCategories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
//Ende Spinner

        Button saveBtn = root.findViewById(R.id.btn_save_todo);
        saveBtn.setOnClickListener(this.saveButtonClickListener);

        return root;
    }
}
