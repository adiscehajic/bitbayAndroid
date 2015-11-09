package ba.bitcamp.android.bitbay.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import ba.bitcamp.android.bitbay.R;

/**
 * This class is used to load PayPal layout.
 */
public class PaypalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paypal);

        //Accepting data that we passed from previous activity
        Bundle extras = getIntent().getExtras();
        //Load data that is passed with key "url" and save it
        String url = extras.getString("url", "");

        //Get view from layout by its id
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new PayPalWebViewClient());

        //load url on that view
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

    /**
     * This inner class is used to load url on on our web view.
     */
    private class PayPalWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}