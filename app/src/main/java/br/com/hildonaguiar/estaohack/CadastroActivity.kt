package br.com.hildonaguiar.estaohack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        //Criando uma Lista de opções para o Spinner
        val listaGenero = arrayListOf("Selecione o Gênero","Masculino","Feminino","Outros")

        //Criando um adaptador para a Lista e o Spinner
        val generoAdapter = ArrayAdapter(
            this, //Contexto
            android.R.layout.simple_spinner_dropdown_item, //Layout do Spinner
            listaGenero //Lista que foi criada
        )
        //Linkar o adaptador no Spinner
        spnCadastroGenero.adapter = generoAdapter

        //Executando o clique do botão cadastrar
        btnCadastroCadastrar.setOnClickListener {
            //Capturar os dados digitados
            val nome = edtCadastroNome.text.toString().trim()
            val sobrenome = edtCadastroSobrenome.text.toString().trim()
            val email = edtCadastroEmail.text.toString().trim().toLowerCase()
            val senha = edtCadastroSenha.text.toString().trim()
            val genero = spnCadastroGenero.selectedItem.toString()

            //Validação de campos
            if (nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty() || senha.isEmpty() || genero ==  listaGenero[0]){
                // Apresentando o Toast
                Toast.makeText(this,"Preencha todas as opções", Toast.LENGTH_LONG).show()
            }else{
                //Todos os Campos preenchidos
                //Criando ou acessando o arquivo de preferências compartilhadas
                val sharedPrefs = getSharedPreferences("cadastro_$email", Context.MODE_PRIVATE)

                //Editar o arquivo de preferências
                val editPrefs = sharedPrefs.edit()

                //Preparando os dados a serem salvos no arquivo
                editPrefs.putString("NOME",nome)
                editPrefs.putString("SOBRENOME", sobrenome)
                editPrefs.putString("EMAIL", email)
                editPrefs.putString("SENHA", senha)
                editPrefs.putString("GENERO", genero)

                //Salvando os dado no arquivo
                editPrefs.apply()

                //Abrindo a main activity
                val mIntent = Intent(this, MainActivity::class.java)
                //Passando informações através da Intent
                mIntent.putExtra("INTENT_EMAIL", email)
                startActivity(mIntent)

                //Tirando as telas do empilhameto
                finishAffinity()
            }
        }
    }
}