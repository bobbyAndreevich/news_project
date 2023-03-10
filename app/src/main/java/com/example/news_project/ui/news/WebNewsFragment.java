package com.example.news_project.ui.news;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.news_project.R;
import com.example.news_project.databinding.FragmentWebNewsBinding;
import com.example.news_project.ui.Codes;

public class WebNewsFragment extends Fragment {

    FragmentWebNewsBinding binding;

    public WebNewsFragment() {
        super(R.layout.web_view_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentWebNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String newsUrl = WebNewsFragmentArgs.fromBundle(getArguments()).getNewsUrl();
        binding.wevView.getSettings().setJavaScriptEnabled(true);
        binding.wevView.loadUrl(newsUrl);
    }
}