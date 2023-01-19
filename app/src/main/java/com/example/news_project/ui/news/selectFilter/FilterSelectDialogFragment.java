package com.example.news_project.ui.news.selectFilter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.news_project.R;
import com.example.news_project.databinding.FragmentFilterSelectBinding;
import com.example.news_project.domain.enities.Filter;
import com.example.news_project.ui.news.selectFilter.arguments.Filters;
import com.example.news_project.ui.news.selectFilter.arguments.OnSelectFilterAction;

import java.util.Objects;

public class FilterSelectDialogFragment extends DialogFragment {

    SelectFiltersListAdapter adapter;

    FragmentFilterSelectBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFilterSelectBinding.inflate(getLayoutInflater());
        binding.selectAllButton.setOnClickListener(click -> onSelectAllClick());
        initAdapter();
        Objects.requireNonNull(getDialog()).getWindow()
                .setBackgroundDrawableResource(R.drawable.dialog_back);
        return binding.getRoot();
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
        Filters filters = FilterSelectDialogFragmentArgs.fromBundle(getArguments()).getFilter();
        binding.selectFiltersList.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new SelectFiltersListAdapter();
        if(filters.list.isEmpty()){
            binding.noFiltersText.setVisibility(View.VISIBLE);
            binding.selectAllButton.setVisibility(View.GONE);
        }
        adapter.submitList(filters.list);
        adapter.setOnItemClick(FilterSelectDialogFragmentArgs.fromBundle(getArguments()).getOnSelectAction());
        adapter.setDismiss(this::dismiss);
        binding.selectFiltersList.setAdapter(adapter);
    }
}
