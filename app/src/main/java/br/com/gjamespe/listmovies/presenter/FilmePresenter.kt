package br.com.gjamespe.listmovies.presenter

import br.com.gjamespe.listmovies.FilmeContract
import br.com.gjamespe.listmovies.estrutura.network.Api
import br.com.gjamespe.listmovies.estrutura.network.modelo.Filme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmePresenter : FilmeContract.Presenter {

    private var model: FilmeContract.Model? = null
    private var view: FilmeContract.View? = null

    private val job = Job()
    private val scopeIO = CoroutineScope(job + Dispatchers.IO)
    private val scopeMainThread = CoroutineScope(job + Dispatchers.Main)

    override fun start(view: FilmeContract.View, model: FilmeContract.Model) {
        this.view = view
        this.model = model
        retrieveFilmes()
    }

    override fun destroy() {
        this.view = null
        job.cancel()
    }

    override fun retrieveFilmes() {
        scopeIO.launch {
            model?.getFilmes(this@FilmePresenter)
        }
    }

    override fun finishRetrieveFilmes(filmes: List<Filme>) {
        if(filmes.isEmpty()) {
            scopeMainThread.launch {
                view?.showMessage("Ops!! Ocorreu um erro ao carregar a lista, favor tentar novamente:)")
            }
        } else {
            scopeMainThread.launch {
                filmes?.let {
                    view?.showFilmes(it)
                }
            }
        }
    }

    class FilmeModel(private val api: Api) : FilmeContract.Model {

        override suspend fun getFilmes(presenter: FilmePresenter) {
            val call = Api.Webservice.filmeService().fetchTrendingMovies()

            call.enqueue(object : Callback<Filme.ApiResponse> {
                override fun onFailure(call: Call<Filme.ApiResponse>, t: Throwable) {
                    presenter.finishRetrieveFilmes(listOf())
                }

                override fun onResponse(call: Call<Filme.ApiResponse>, response: Response<Filme.ApiResponse>) {
                    presenter.finishRetrieveFilmes(response.body()!!.results)
                }

            })
        }

    }

}