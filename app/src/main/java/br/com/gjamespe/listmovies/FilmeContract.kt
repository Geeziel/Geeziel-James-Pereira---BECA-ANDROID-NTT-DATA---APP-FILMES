package br.com.gjamespe.listmovies

import br.com.gjamespe.listmovies.estrutura.network.modelo.Filme
import br.com.gjamespe.listmovies.presenter.FilmePresenter

interface FilmeContract {

    interface Model {
        suspend fun getFilmes(coroutineScope: FilmePresenter)
    }

    interface Presenter {
        fun start(view: View, model: Model)
        fun destroy()
        fun retrieveFilmes()
        fun finishRetrieveFilmes(filmes: List<Filme>)
    }

    interface View {
        fun showMessage(message: String)
        fun showFilmes(filmes: List<Filme>)
    }

}