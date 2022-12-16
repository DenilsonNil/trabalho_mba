package br.com.kualit.trabalhomba.acitivities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.kualit.trabalhomba.R
import br.com.kualit.trabalhomba.adapters.UsuarioAdapter
import br.com.kualit.trabalhomba.model.Usuario

private const val DATA_SEPARATOR = "|"

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val botaoCadastrar: View = findViewById(R.id.btn_cadastrar)

        botaoCadastrar.setOnClickListener {
            val edtNome: EditText = findViewById(R.id.edt_cadastro_nome)
            val edtEmail: EditText = findViewById(R.id.edt_cadastro_email)
            val edtPassword: EditText = findViewById(R.id.edt_cadastro_password)

            if(validarCampos(edtNome, edtEmail, edtPassword)) {
                cadastrarUsuario(edtNome.text.toString(), edtEmail.text.toString(), edtPassword.text.toString())
                mostrarToast("Usuario cadastrado com sucesso")
                startActivity(Intent(this, ListagemActivity::class.java))
                this.finish()
            }
        }
    }

    fun validarCampos(nome: EditText, email: EditText, senha: EditText): Boolean{
        if(nome.text.isEmpty() || email.text.isEmpty() || senha.text.isEmpty()) {
            mostrarToast("Todos os campos são obrigatórios")
            return false
        }

        return true
    }

    fun mostrarToast(mensagem: String) {
        val text = mensagem
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    private fun cadastrarUsuario(nome: String, email: String, senha: String) {
        val sharedPreferences = getSharedPreferences("usuarios", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(email, nome + DATA_SEPARATOR + senha)
        editor.apply()
    }
}