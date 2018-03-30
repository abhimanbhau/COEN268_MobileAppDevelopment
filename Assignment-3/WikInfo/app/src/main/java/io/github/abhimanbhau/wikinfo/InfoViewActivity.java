package io.github.abhimanbhau.wikinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class InfoViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_view);

        WebView view = findViewById(R.id.webView);
        view.setWebViewClient(new WebViewClient());
        view.loadUrl(getIntent().getExtras().getString("URL"));
    }
}
