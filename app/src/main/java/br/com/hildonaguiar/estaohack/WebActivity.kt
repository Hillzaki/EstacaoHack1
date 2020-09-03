package br.com.hildonaguiar.estaohack

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        //Habilitando a execução de códigos javascript
        wbvWeb.settings.javaScriptEnabled = true

        //Carregando o site
        wbvWeb.loadUrl("http://br.cellep.com/estacaohack")

        //Definindo o Werbview como cliente web padrão
        wbvWeb.webViewClient = WebViewClient()
    }
}