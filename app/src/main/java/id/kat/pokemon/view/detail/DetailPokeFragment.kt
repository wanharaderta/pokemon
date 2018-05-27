package id.kat.pokemon.view.detail

import android.os.Bundle
import android.support.v7.app.AppCompatDelegate
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.kat.news.utils.loadImage
import id.kat.pokemon.R
import id.kat.pokemon.data.local.Poke
import id.kat.pokemon.data.remote.RemotePokeDetail
import id.kat.pokemon.deps.provider.PokeProvider
import id.kat.pokemon.service.PokeDetailService
import id.kat.pokemon.view.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_detail_poke.*
import javax.inject.Inject


class DetailPokeFragment : BaseFragment(),DetailView {



    @Inject
    protected lateinit var service: PokeDetailService

    private var poke:Poke? = null

    private var presenter: DetailPresenter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        poke = arguments?.getParcelable("poke")

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        fun initView(){

            activity.supportActionBar?.title = "Detail ${poke?.name}"
            activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        initView()


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity.application as PokeProvider).providesPokeComponent().inject(this)
        return inflater.inflate(R.layout.fragment_detail_poke, container, false)
    }

    override fun onResume() {
        presenter = DetailPresenter()
        onAttach()
        super.onResume()
    }


    override fun onAttach() {
        presenter?.onAttach(this)
        presenter?.getDetail(service,poke)
    }

    override fun onDetach() {
        presenter?.onDetach()
        super.onDetach()
    }

    override fun onProgress() {
        activity.showProgressDialog()
    }


    override fun onSuccess(response: RemotePokeDetail) {

        val pos = 0

        nameForms.text = "Name : ${response.forms[pos].name}"
        abilityName.text = "Name : ${response.abilities[pos].ability.name}"
        slot.text = "Slot : ${response.abilities[pos].slot}"
        statName.text = "Name : ${response.stats[pos].stat.name}"
        statEffort.text = "Effrot : ${response.stats[pos].effort}"
        baseStat.text = "Base Stat : ${response.stats[pos].baseStat}"
        name.text = "Name : ${response.name}"
        weight.text = "Weight : ${response.weight}"
        moves.text = "Name : ${response.moves[pos].move.name}"

        loadImage(activity, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${poke?.number}.png", profilePicture)


        fun hide(){
            activity.hideProgressDialog()
        }

        hide()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                activity.onBackPressed()
                Log.d("ggggg","dfsdfsdfsdf")
            }
        }
        return super.onOptionsItemSelected(item)
    }


    companion object {
        val TAG = DetailPokeFragment::class.simpleName
        fun newInstance(poke: Poke?): DetailPokeFragment {

            val fragment = DetailPokeFragment()
            val bundle = Bundle()
            bundle.putParcelable("poke", poke)
            fragment.arguments = bundle
            return fragment

        }
    }

}
