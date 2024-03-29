package de.fhe.pmeplayground.view.core;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

public class BaseFragment extends Fragment {

    /*
        Getting a ViewModel as well as a specific AndroidViewModel that has
        access to the context requires some effort. This method hides all the
        complex stuff.
     */
    protected <T extends ViewModel> T getViewModel(Class<T> tClass) {
        return new ViewModelProvider(
                this,
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(
                        requireActivity().getApplication()
                )).get(tClass);
    }

    /*
        Helper method to hide the keyboard, for example when submitting a form.
     */
    public void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    protected void hideBackButton() {
        // Hide Back Button
        Objects.requireNonNull(
                ((AppCompatActivity) requireActivity()).getSupportActionBar()
        ).setDisplayHomeAsUpEnabled(false);
    }
}

