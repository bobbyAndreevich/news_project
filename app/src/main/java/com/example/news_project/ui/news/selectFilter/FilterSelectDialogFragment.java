package com.example.news_project.ui.news.selectFilter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.news_project.databinding.FragmentFilterSelectBinding;
import com.example.news_project.domain.enities.Filter;
import com.example.news_project.ui.news.selectFilter.arguments.Filters;
import com.example.news_project.ui.news.selectFilter.arguments.OnSelectFilterAction;

public class FilterSelectDialogFragment extends DialogFragment {

    Filters filters;

    SelectFiltersListAdapter adapter;

    FragmentFilterSelectBinding binding;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = FragmentFilterSelectBinding.inflate(getLayoutInflater());
        binding.selectAllButton.setOnClickListener(click -> onSelectAllClick());
        Log.e("создался", "");
        filters = FilterSelectDialogFragmentArgs.fromBundle(getArguments()).getFilter();
        initAdapter();
        return new AlertDialog
                .Builder(requireActivity())
                .setView(binding.getRoot())
                .create();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void onSelectAllClick() {
        OnSelectFilterAction action = FilterSelectDialogFragmentArgs
                .fromBundle(getArguments()).getOnSelectAction();
        Filter filter = new Filter();
        filter.name = "Все";
        action.accept(filter);
        this.dismiss();
    }

    private void initAdapter() {
        binding.selectFiltersList.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new SelectFiltersListAdapter();
        adapter.submitList(filters.list);
        adapter.setOnItemClick(FilterSelectDialogFragmentArgs.fromBundle(getArguments()).getOnSelectAction());
        adapter.setDismiss(this::dismiss);
        binding.selectFiltersList.setAdapter(adapter);
    }
}
