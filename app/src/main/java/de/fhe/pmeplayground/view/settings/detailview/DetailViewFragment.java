package de.fhe.pmeplayground.view.settings.detailview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import de.fhe.pmeplayground.R;
import de.fhe.pmeplayground.model.ToDo;
import de.fhe.pmeplayground.view.core.BaseFragment;

public class DetailViewFragment extends BaseFragment {


    private DetailViewViewModel viewModel;
    private LiveData<ToDo> toDoLiveData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_detail_view, container, false); //TODO: was ist der Container, was bringt Attach to Root
        viewModel = this.getViewModel(DetailViewViewModel.class); //TODO: Bitte die ganze Methode erklären
        this.hideBackButton();
        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        assert getArguments() != null;
        final long toDoId = getArguments().getLong("toDoId");
        this.toDoLiveData = viewModel.getToDo(toDoId);
        this.toDoLiveData.observe(requireActivity(), this::updateView);
        Log.i("EventCallbacks", "Observing Detail ToDo");

    }

    @Override
    public void onPause() {
        super.onPause();
        this.toDoLiveData.removeObservers(requireActivity());  //TODO: Was bringt das?
        Log.i("EventCallbacks", "Stopped observing Detail ToDo");
    }

    private void updateView(ToDo toDo) {
        Log.i("EventCallbacks", "Update Detail View with ToDo: " + toDo);


        assert getView() != null;
        TextView toDoTitleView = getView().findViewById(R.id.fragment_detail_view_todo_title);
        TextView toDoDescriptionView = getView().findViewById(R.id.fragment_detail_view_todo_description);
        TextView toDoCategoryView = getView().findViewById(R.id.fragment_detail_view_todo_category);
        TextView toDoDeadlineView = getView().findViewById(R.id.fragment_detail_view_todo_deadline);
        //CheckBox toDoDoneView = getView().findViewById(R.id.fragment_detail_view_todo_done); //TODO: checkbox in detailview reinmachen

        toDoTitleView.setText(toDo.getToDoTitle());
        toDoDescriptionView.setText(toDo.getDescription());
        toDoCategoryView.setText(toDo.getCategory());
        toDoDeadlineView.setText(toDo.getDeadline());

    }

}

