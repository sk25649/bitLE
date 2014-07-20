package com.austin.siwan.bitle;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by Kevin on 7/19/2014.
 */
public class InvoiceWebViewActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        setContentView(webView);
        webView.loadUrl(getIntent().getExtras().getString("url"));
    }
}
