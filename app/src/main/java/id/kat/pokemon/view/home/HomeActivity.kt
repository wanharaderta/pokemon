package id.kat.pokemon.view.home

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection
import id.kat.pokemon.R
import id.kat.pokemon.data.local.Poke
import id.kat.pokemon.data.remote.RemotePoke
import id.kat.pokemon.deps.provider.PokeProvider
import id.kat.pokemon.ext.EndlessRecyclerViewScrollListener
import id.kat.pokemon.service.PokeService
import id.kat.pokemon.view.base.BaseActivity
import id.kat.pokemon.view.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class HomeActivity : BaseActivity(),HomeView, SwipyRefreshLayout.OnRefreshListener {


    @Inject
    protected lateinit var service: PokeService

    private var presenter: HomePresenter? = null

    var offSet:Int = 20

    var scrollListener: EndlessRecyclerViewScrollListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        (application as PokeProvider).providesPokeComponent().inject(this)

        fun initView(){

            layout_swipe_refresh.setOnRefreshListener(this)
            layout_swipe_refresh.direction = SwipyRefreshLayoutDirection.BOTTOM

            val lm = GridLayoutManager(this,3)
            rvPoke.layoutManager = lm
            scrollListener = object : EndlessRecyclerViewScrollListener(lm){
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    progress_dialog.visibility = View.GONE
                    layout_swipe_refresh.isRefreshing = true
                    presenter?.getPokemon(service, offSet+21)

                }

            }

        }

        initView()

    }

    override fun onResume() {
        presenter = HomePresenter()
        onAttach()
        super.onResume()
    }

    override fun onProgress() {
        progress_dialog.visibility = View.VISIBLE

    }

    override fun onSuccess(response: RemotePoke, offset: Int) {
       val data = response.results

        val poke = data.map {
            val name = it?.name
            val url = it?.url

            Poke(name,url,0)
        }

        val adapter = HomeAdapter(this,poke, object : HomeListener{
            override fun onClick(poke: Poke) {
                presenter?.pokemonDetail(poke)
            }
        })


        rvPoke.adapter = adapter
        rvPoke.addOnScrollListener(scrollListener)
        adapter.notifyDataSetChanged()

        if (offset == 1) {
            scrollListener?.resetState()
            offSet = 15
        } else {
            offSet += 15

        }

        fun hide(){
            progress_dialog.visibility = android.view.View.GONE
            rvPoke.visibility = android.view.View.VISIBLE
            layout_swipe_refresh.isRefreshing = false
        }

        hide()


    }

    override fun onAttach() {
        presenter?.onAttach(this)
        presenter?.getPokemon(service, 50)

    }

    override fun onDetach() {
        presenter?.onDetach()
    }

    override fun onDestroy() {
        onDetach()
        super.onDestroy()
    }

    override fun onRefresh(direction: SwipyRefreshLayoutDirection?) {
        if (direction == SwipyRefreshLayoutDirection.BOTTOM) {
            presenter?.getPokemon(service,50)
        }
    }

    override fun onPokeDetail(poke: Poke) {
        startActivity<DetailActivity>(
                "poke" to poke
        )
        this.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
    }




}
