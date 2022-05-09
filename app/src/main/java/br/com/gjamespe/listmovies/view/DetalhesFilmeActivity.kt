package br.com.gjamespe.listmovies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.gjamespe.listmovies.R
import br.com.gjamespe.listmovies.estrutura.network.modelo.Filme
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhes_filme.*

class DetalhesFilmeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_filme)

        val filme: Filme? = intent.getSerializableExtra("filme") as Filme

        Picasso.get().load(filme?.Capa()).into(activity_detalhes_filme_capa)
        activity_detalhes_filme_titulo.text = filme?.Titulo()
        activity_detalhes_filme_lancamento.text = filme?.DataLancamento()
        activity_detalhes_filme_avaliacao.text = filme?.Avaliacao()
        activity_detalhes_filme_sinopse.text = filme?.Sinopse()
    }
}
