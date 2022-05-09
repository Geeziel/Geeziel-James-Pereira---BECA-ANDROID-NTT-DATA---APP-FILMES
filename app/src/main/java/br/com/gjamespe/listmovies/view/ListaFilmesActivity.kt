package br.com.gjamespe.listmovies.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.gjamespe.listmovies.FilmeContract
import br.com.gjamespe.listmovies.R
import br.com.gjamespe.listmovies.estrutura.modules.Modules
import br.com.gjamespe.listmovies.estrutura.network.modelo.Filme
import kotlinx.android.synthetic.main.activity_lista_filmes.*

class ListaFilmesActivity : AppCompatActivity(), FilmeContract.View {
    private val presenter: FilmeContract.Presenter =
        Modules.filmeModule.presenter
    private val model: FilmeContract.Model =
        Modules.filmeModule.model

    val onClickListener: OnClickListener = object :
        OnClickListener {
        override fun onClick(filme: Filme) {
            chamaDetalhesFilme(filme)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_filmes)
        presenter.start(this, model)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    private fun configuraLista(filmes: List<Filme>) {
        val adapter = ListaFilmesAdapter(this, filmes)
        adapter.onClickListener = onClickListener
        activity_lista_filmes_gridview.adapter = adapter
    }

    private fun chamaDetalhesFilme(filme: Filme) {
        var intent = Intent(this@ListaFilmesActivity, DetalhesFilmeActivity::class.java)
        intent.putExtra("filme", filme)
        startActivity(intent)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showFilmes(filmes: List<Filme>) {
        configuraLista(filmes)
    }

}
