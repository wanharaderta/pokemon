package id.kat.pokemon.view.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import id.kat.pokemon.R
import kotlinx.android.synthetic.main.activity_splash.*

/**
 *
 * Created by Wanhar Aderta Daeng Maro on 5/26/2018.
 * Email : wanhardaengmaro@gmail.com
 *
 */
open class BaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }


    fun setFragment(fragment: Fragment, tag: String, addToBackStack: Boolean) {
        if (tag == currentFragmentTag) {
            return
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment, tag)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(tag)
        }
        fragmentTransaction.commit()
    }


    fun setFragment(fragment: Fragment, tag: String, addToBackStack: Boolean, animRes: IntArray?) {
        if (tag == currentFragmentTag) {
            return
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (animRes != null) {
            fragmentTransaction.setCustomAnimations(animRes[0], animRes[1], animRes[2], animRes[3])
        }
        fragmentTransaction.replace(R.id.fragment_container, fragment, tag)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(tag)
        }
        fragmentTransaction.commit()
    }

    fun addFragment(fragment: Fragment, tag: String, addToBackStack: Boolean, animRes: IntArray?) {
        if (tag == currentFragmentTag) {
            return
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (animRes != null) {
            fragmentTransaction.setCustomAnimations(animRes[0], animRes[1], animRes[2], animRes[3])
        }
        fragmentTransaction.add(R.id.fragment_container, fragment, tag)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(tag)
        }
        fragmentTransaction.commit()
    }


    val currentFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(R.id.fragment_container)

    private val currentFragmentTag: String?
        get() {
            val f = currentFragment
            return f?.tag
        }

    fun showProgressDialog() {
        progress_dialog.visibility = android.view.View.VISIBLE
    }

    fun hideProgressDialog() {
        progress_dialog.visibility = android.view.View.GONE
    }


}