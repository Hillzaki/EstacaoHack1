package br.com.hildonaguiar.estaohack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Executando o Clique do Botão
        btnLoginEntrar.setOnClickListener {
            //Capturar dados digitados pelo usuário
            val email = edtLoginEmail.text.toString().trim().toLowerCase()
            val senha = edtLoginSenha.text.toString().trim()

            //Validação dos campos
            if(email.isEmpty()){
                edtLoginEmail.error = "Campo Obrigatório"
                edtLoginEmail.requestFocus()
            }else if(senha.isEmpty()){
                edtLoginSenha.error = "Campo Obrigatório"
                edtLoginSenha.requestFocus()
            }else{
                //Acessando o SharedPreferences e verificando email e senha
                val sharedPrefs = getSharedPreferences("cadastro_$email", Context.MODE_PRIVATE)
                //Recuperando dados do SP
                val emailPrefs = sharedPrefs.getString("EMAIL", "")
                val senhaPrefs = sharedPrefs.getString("SENHA", "")

                //Verificando E-mail e senha do usuário
                if(email == emailPrefs && senha == senhaPrefs){
                    Toast.makeText(this,"Usuário Logado com Sucesso!", Toast.LENGTH_LONG)
                        .show()
                    //Abrindo a Main
                    val mIntent = Intent(this, MainActivity::class.java)
                   //Passando informações através da intent
                    mIntent.putExtra("INTENT_EMAIL", email)
                    startActivity(mIntent)
                    finish()
                }else{
                    //Apresentar uma menagem de erro ao usuário
                    Toast.makeText(this, "E-mail e/ou Senha inválido(s)", Toast.LENGTH_LONG).show()

                }
            }
        }
        //Executando o CLiqe do botão Cadastrar
        btnLoginCadastrar.setOnClickListener {
            //Abrindo a tela de cadastro
            val mIntent = Intent(this, CadastroActivity::class.java)
            startActivity(mIntent)
        }
    }
}