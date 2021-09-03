package production.luku.ke;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    WebView wv;
    boolean check = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.animationLayout);

        wv = (WebView) findViewById(R.id.webview);
        wv.getSettings().setLoadsImagesAutomatically(true);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setAppCacheEnabled(true);
        wv.getSettings().setDomStorageEnabled(true);
        wv.getSettings().setSupportMultipleWindows(true);
        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.startsWith("tel:") || url.startsWith("whatsapp:")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                    return true;
                }else if (url.startsWith("mailto:")) {
                    Intent mail = new Intent(Intent.ACTION_SEND);
                    mail.setType("application/octet-stream");
                    String AdressMail = new String(url.replace("mailto:" , "")) ;
                    mail.putExtra(Intent.EXTRA_EMAIL, new String[]{ AdressMail });
                    mail.putExtra(Intent.EXTRA_SUBJECT, "");
                    mail.putExtra(Intent.EXTRA_TEXT, "");
                    startActivity(mail);
                    return true;
                }
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                findViewById(R.id.animationLayout).setVisibility(View.GONE);
                findViewById(R.id.webview).setVisibility(View.VISIBLE);

            }
        });
        wv.loadUrl("https://luku.ke/");
    }



    @Override
    public void onBackPressed() {
        if (wv.canGoBack()) {
            wv.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
