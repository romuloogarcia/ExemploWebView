package br.edu.pdm.exemplowebview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class ActivityPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        // amarrando componentes
        WebView webView = (WebView) findViewById(R.id.webview);

        // carregando as configurações do webview
        WebSettings webSettings = webView.getSettings();

        // ativando o uso de JavaScript no webview
        webSettings.setJavaScriptEnabled(true);

        // carregando uma página no webview
        webView.loadUrl("http://webviewteste.000webhostapp.com/");

        // implementando uma interface para que a página possa chamar um JavaScript
        webView.addJavascriptInterface(new WebAppInterface(this), "webviewJavaScript");


    }

    public class WebAppInterface {
        Context mContext;

        // instanciar a interface e configurar o contexto
        WebAppInterface(Context c) {
            mContext = c;
        }

        // interface que possibilita mostrar um Toast do Android chamado por um JavaScript na página
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_LONG).show();
        }
    }
}
