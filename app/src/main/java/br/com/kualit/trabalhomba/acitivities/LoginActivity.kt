package br.com.kualit.trabalhomba.acitivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.com.kualit.trabalhomba.R

private const val EMAIL_ADMINISTRADOR = "admin@kualit.com.br"

private const val DATA_SEPARATOR = "|"

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        cadastrarUsuarioAdmin()

        val edtEmail: EditText = findViewById(R.id.editTextTextEmailAddress)
        val edtPassword: EditText = findViewById(R.id.editTextTextPassword)

        val btnLogin: Button = findViewById(R.id.btn_login)
        btnLogin.setOnClickListener {
            realizarLogin(edtEmail, edtPassword)
        }
    }

    private fun realizarLogin(edtEmail: EditText, edtPassword: EditText) {
        val emailDigitado = edtEmail.text.toString()
        val senhaDigitada = edtPassword.text.toString()

        val dadosUsuario = buscarUsuarioPorEmail(emailDigitado)
        val token = dadosUsuario?.split(DATA_SEPARATOR)
        val senhaRecuperadaDoBanco = token?.get(1)

        if (emailDigitado == EMAIL_ADMINISTRADOR && senhaDigitada == senhaRecuperadaDoBanco) {
            return carregarHome()
        } else {
            mostrarToast()
        }
    }

    private fun carregarHome() {
        startActivity(Intent(this, ListagemActivity::class.java))
        this.finish()
    }

    private fun mostrarToast() {
        val text = "Credenciais inválidas"
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    private fun buscarUsuarioPorEmail(email: String?): String? {
        val sharedPreferences = getSharedPreferences("usuarios", MODE_PRIVATE)
        return sharedPreferences.getString(email, "NOT_FOUND" + DATA_SEPARATOR + "INVALID_PASS")
    }

    private fun cadastrarUsuarioAdmin() {
        val sharedPreferences = getSharedPreferences("usuarios", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(EMAIL_ADMINISTRADOR, "Administrador" + DATA_SEPARATOR + "123456")
        editor.apply()
    }
}