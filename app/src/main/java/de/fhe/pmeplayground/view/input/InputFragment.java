package de.fhe.pmeplayground.view.input;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import de.fhe.pmeplayground.R;
import de.fhe.pmeplayground.model.ToDo;
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
                    deadlineField.getText().toString());

            String returnValue = inputViewModel.saveToDo( newToDo );

            hideKeyboard( this.requireContext(), v );
            Snackbar.make(v, returnValue, Snackbar.LENGTH_SHORT).show();
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        inputViewModel = this.getViewModel(InputViewModel.class);

        View root = inflater.inflate(R.layout.fragment_input, container, false);

        this.toDoField = root.findViewById(R.id.et_todo);
        this.descriptionField = root.findViewById(R.id.et_description);

        Button saveBtn = root.findViewById(R.id.btn_save_todo);
        saveBtn.setOnClickListener(this.saveButtonClickListener);

        return root;
    }
}
