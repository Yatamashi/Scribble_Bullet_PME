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
import de.fhe.pmeplayground.model.Contact;
import de.fhe.pmeplayground.view.core.BaseFragment;

public class InputFragment extends BaseFragment {

    private InputViewModel inputViewModel;
    private EditText lastnameField;
    private EditText firstnameField;

    private final View.OnClickListener saveButtonClickListener = v -> {

        if( v.getId() == R.id.btn_save_contact)
        {
            Contact newContact = new Contact(
                    lastnameField.getText().toString(),
                    firstnameField.getText().toString() );

            String returnValue = inputViewModel.saveContact( newContact );

            hideKeyboard( this.requireContext(), v );
            Snackbar.make(v, returnValue, Snackbar.LENGTH_SHORT).show();
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        inputViewModel = this.getViewModel(InputViewModel.class);

        View root = inflater.inflate(R.layout.fragment_input, container, false);

        this.lastnameField = root.findViewById(R.id.et_lastname);
        this.firstnameField = root.findViewById(R.id.et_firstname);

        Button saveBtn = root.findViewById(R.id.btn_save_contact);
        saveBtn.setOnClickListener(this.saveButtonClickListener);

        return root;
    }
}
