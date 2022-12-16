package br.com.kualit.trabalhomba.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import br.com.kualit.trabalhomba.R
import br.com.kualit.trabalhomba.model.Usuario

class UsuarioAdapter(private val context: Context, private val dataSource: List<Usuario>) :
    BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        println("List Size " + dataSource.size)
        return dataSource.size

    }

    override fun getItem(position: Int): Usuario {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val rowView = inflater.inflate(R.layout.usuario_item, parent, false)
        val nome = rowView.findViewById(R.id.txt_item_usuario_nome) as TextView
        val email = rowView.findViewById(R.id.txt_item_usuario_email) as TextView

        val usuario = getItem(position)
        nome.text = usuario.nome
        email.text = usuario.email

        println("Position " + position + " Nome " + usuario.nome)
        return rowView

    }
}