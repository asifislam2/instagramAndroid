package com.instagram.instagram;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Fragment_reels extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reels, container, false);
        WebView loadReels_Webview = view.findViewById(R.id.loadReels_Webview);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);


        loadReels_Webview.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        loadReels_Webview.getSettings().setJavaScriptEnabled(true);
        loadReels_Webview.setWebViewClient(new WebViewClient());
        loadReels_Webview.loadUrl("https://www.tiktok.com/foryou");
        progressBar.setVisibility(View.GONE);
        loadReels_Webview.setVisibility(View.VISIBLE);


        return view;
    }
}