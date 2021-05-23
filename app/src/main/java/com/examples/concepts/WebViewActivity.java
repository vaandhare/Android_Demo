package com.examples.concepts;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.w3c.dom.Document;

public class WebViewActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView webView = findViewById(R.id.webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url)
            {
                webView.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByTagName('header')[0];"
                        + "head.parentNode.removeChild(head);" +
                        "})()");

                webView.loadUrl("javascript:(function() { " +
                        "var head = document.getElementsByTagName('footer')[0];"
                        + "head.parentNode.removeChild(head);" +
                        "})()");
            }
        });
//        webView.loadUrl(getIntent().getStringExtra("webURL"));
        webView.loadUrl("https://www.youtube.com/");
    }
}