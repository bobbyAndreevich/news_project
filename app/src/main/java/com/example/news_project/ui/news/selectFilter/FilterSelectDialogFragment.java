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

public class FilterSelectDialogFragment extends DialogFragment {

    Filters filters;

    SelectFiltersListAdapter adapter;

    FragmentFilterSelectBinding binding;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = FragmentFilterSelectBinding.inflate(getLayoutInflater());
        Log.e("создался", "");
        filters = FilterSelectDialogFragmentArgs.fromBundle(getArguments()).getFilters();
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

    private void initAdapter(){
        binding.selectFiltersList.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new SelectFiltersListAdapter();
        adapter.submitList(filters.list);
        adapter.setOnItemClick(FilterSelectDialogFragmentArgs.fromBundle(getArguments()).getOnSelectAction());
        adapter.setDismiss(this::dismiss);
        binding.selectFiltersList.setAdapter(adapter);
    }
}
