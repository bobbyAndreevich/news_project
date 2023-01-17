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

import javax.inject.Inject;

public class DeleteFilterDialogFragment extends DialogFragment {

    @Inject
    FiltersViewModel viewModel;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        ((DaggerApp) requireActivity().getApplication()).getAppComponent().inject(this);
        return new AlertDialog.Builder(requireContext())
                .setMessage(getString(R.string.delete_filter_accept))
                .setPositiveButton(
                        getString(R.string.confirm_delete_filter),
                        (dialogInterface, i) -> deleteFilterAction())
                .create();
    }

    private void deleteFilterAction() {
        Filter filter = (Filter) requireArguments().getSerializable(Codes.FILTER_KEY);
        viewModel.deleteFilter(filter);
        this.dismiss();
    }

    public static String TAG = "DeleteFilterDialogFragment";
}
