package id.kat.pokemon.view.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
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

class HomeActivity : BaseActivity(), HomeView, SwipyRefreshLayout.OnRefreshListener {


    @Inject
    protected lateinit var service: PokeService

    private var presenter: HomePresenter? = null

    var offSet: Int = 100

    var layoutManager: LinearLayoutManager? = null

    var onBottom: Boolean = false

    var scrollListener: EndlessRecyclerViewScrollListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        (application as PokeProvider).providesPokeComponent().inject(this)

        fun initView() {

            layout_swipe_refresh.setOnRefreshListener(this)
            layout_swipe_refresh.setDistanceToTriggerSync(100)

            val lm = GridLayoutManager(this, 3)
            rvPoke.layoutManager = lm
            scrollListener = object : EndlessRecyclerViewScrollListener(lm) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {

                }

            }

            rvPoke.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val visibleItemCount = layoutManager?.childCount
                    val totalItemCount = layoutManager?.itemCount
                    val firstVisibleItemPosition = layoutManager?.findFirstVisibleItemPosition()

                    if (visibleItemCount != null) {
                        if (visibleItemCount + firstVisibleItemPosition!! >= totalItemCount!! && firstVisibleItemPosition >= 0) {
                            Log.v("onScrolled", "true")
                            onBottom = true
                        } else {
                            onBottom = false
                        }
                    }
                }
            })

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

            Poke(name, url, 0)
        }

        val adapter = HomeAdapter(this, poke, object : HomeListener {
            override fun onClick(poke: Poke) {
                presenter?.pokemonDetail(poke)
            }
        })


        rvPoke.adapter = adapter
        rvPoke.addOnScrollListener(scrollListener)
        adapter.notifyDataSetChanged()

        fun hide() {
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
        offSet += 50
        presenter?.getPokemon(service, offSet)
    }

    override fun onPokeDetail(poke: Poke) {
        startActivity<DetailActivity>(
                "poke" to poke
        )
        this.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
    }

    override fun onBackPressed() {
        showExitDialog()
    }


    fun showExitDialog(): AlertDialog {
        return AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("Do you want to exit the application?")
                .setPositiveButton("Exit", { dialog, which ->
                    val intent = Intent(Intent.ACTION_MAIN)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    intent.addCategory(Intent.CATEGORY_HOME)
                    this.startActivity(intent)
                })
                .setNegativeButton("Cancel", null)
                .show()
    }


}
