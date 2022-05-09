package br.com.gjamespe.listmovies.estrutura.modules

import br.com.gjamespe.listmovies.FilmeContract
import br.com.gjamespe.listmovies.estrutura.network.Api
import br.com.gjamespe.listmovies.presenter.FilmePresenter

object Modules {

    interface FilmeModule {
        val presenter : FilmeContract.Presenter
        val model : FilmeContract.Model
    }


    val filmeModule : FilmeModule by lazy {
        object : FilmeModule{
            override val presenter: FilmeContract.Presenter
                get() = FilmePresenter()

            override val model: FilmeContract.Model
                get() = FilmePresenter.FilmeModel(Api.Webservice.filmeService())

        }
    }
}