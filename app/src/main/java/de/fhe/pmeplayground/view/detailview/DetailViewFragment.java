package de.fhe.pmeplayground.view.detailview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import de.fhe.pmeplayground.R;
import de.fhe.pmeplayground.model.ToDo;
import de.fhe.pmeplayground.view.core.BaseFragment;

/**
 * DetailViewFragment is for users to see details of a "dodo", he can see the title, category, description an deadline
 */
public class DetailViewFragment extends BaseFragment {

    private DetailViewViewModel viewModel;
    private LiveData<ToDo> toDoLiveData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_detail_view, container, false);
        viewModel = this.getViewModel(DetailViewViewModel.class);
        this.hideBackButton();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getArguments() != null)
        {

            final long toDoId = getArguments().getLong("toDoId");
            this.toDoLiveData = viewModel.getToDo(toDoId);
            this.toDoLiveData.observe(requireActivity(), this::updateView);
            Log.i("EventCallbacks", "Observing Detail ToDo");

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(this.toDoLiveData != null)
        {
        this.toDoLiveData.removeObservers(requireActivity());
        }
        Log.i("EventCallbacks", "Stopped observing Detail ToDo");
    }

    private void updateView(ToDo toDo) {
        Log.i("EventCallbacks", "Update Detail View with ToDo: " + toDo);


        assert getView() != null;
        TextView toDoTitleView = getView().findViewById(R.id.fragment_detail_view_todo_title);
        TextView toDoDescriptionView = getView().findViewById(R.id.fragment_detail_view_todo_description);
        TextView toDoCategoryView = getView().findViewById(R.id.fragment_detail_view_todo_category);
        TextView toDoDeadlineView = getView().findViewById(R.id.fragment_detail_view_todo_deadline);


        toDoTitleView.setText(toDo.getToDoTitle());
        toDoDescriptionView.setText(toDo.getDescription());
        toDoCategoryView.setText(toDo.getCategory());
        toDoDeadlineView.setText(toDo.getDeadline());

    }



}

