package com.example.news_project.ui.filters.filtersList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.news_project.DI.DaggerApp;
import com.example.news_project.R;
import com.example.news_project.domain.enities.Filter;
import com.example.news_project.ui.Codes;

import java.util.function.Consumer;

import javax.inject.Inject;

public class DeleteFilterDialogFragment extends DialogFragment {

    private final Consumer<Filter> deleteFilterAction;

    public DeleteFilterDialogFragment(Consumer<Filter> action){
        deleteFilterAction = action;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setMessage(getString(R.string.delete_filter_accept))
                .setPositiveButton(
                        getString(R.string.confirm_delete_filter),
                        (dialogInterface, i) -> deleteFilterAction())
                .create();
    }

    private void deleteFilterAction() {
        Filter filter = (Filter) requireArguments().getSerializable(Codes.FILTER_KEY);
        deleteFilterAction.accept(filter);
        this.dismiss();
    }

    public static String TAG = "DeleteFilterDialogFragment";
}
