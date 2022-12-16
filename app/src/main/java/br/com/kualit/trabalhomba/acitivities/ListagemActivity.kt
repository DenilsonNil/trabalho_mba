package br.com.kualit.trabalhomba.acitivities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import br.com.kualit.trabalhomba.R
import br.com.kualit.trabalhomba.adapters.UsuarioAdapter
import br.com.kualit.trabalhomba.model.Usuario


class ListagemActivity : AppCompatActivity() {


    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listagem)
        configureList()

        val fab: View = findViewById(R.id.fab_add_users)
        fab.setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
            this.finish()
        }
    }

    private fun configureList() {
        listView = findViewById(R.id.recipe_list_view)

        val allUsers = getAllUsers()
        println("Tamanho da Lista" + allUsers.size)
        val adapter = UsuarioAdapter(this, allUsers)
        listView.adapter = adapter
    }

    fun getAllUsers(): List<Usuario> {
        val sharedPreferences = getSharedPreferences("usuarios", MODE_PRIVATE)
        val users = sharedPreferences.all
        val usuarios = mutableListOf<Usuario>()
        for (key in users.keys) {
            val dadosUsuario = users.get(key)
            val token = dadosUsuario.toString().split("|")
            usuarios.add(Usuario(token.get(0), key))
        }

        return usuarios
    }
}