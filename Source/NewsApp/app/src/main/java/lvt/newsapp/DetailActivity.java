package lvt.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;


public class DetailActivity extends AppCompatActivity {
    public static final String NEWS_URL = "news_url";
    private String mUrl;
    private TextView mWebViewLoadingTextView;
    private WebView mWebView;
    private ProgressBar mWebViewProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mWebView = (WebView) findViewById(R.id.wv_detail);
        mUrl = getIntent().getStringExtra(NEWS_URL);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//        mWebView.setPictureListener(this);
        mWebView.loadUrl(mUrl);
    }
}