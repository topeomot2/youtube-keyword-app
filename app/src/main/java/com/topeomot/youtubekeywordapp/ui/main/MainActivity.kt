package com.topeomot.youtubekeywordapp.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import com.topeomot.youtubekeywordapp.R
import com.topeomot.youtubekeywordapp.YoutubeKeywordApplication
import com.topeomot.youtubekeywordapp.di.component.ActivityComponent
import com.topeomot.youtubekeywordapp.di.component.DaggerActivityComponent
import com.topeomot.youtubekeywordapp.di.module.ActivityModule
import com.topeomot.youtubekeywordapp.model.Snippet
import com.topeomot.youtubekeywordapp.model.exception.NoSubjectMatterException
import com.topeomot.youtubekeywordapp.ui.ViewModelFactory
import com.topeomot.youtubekeywordapp.ui.home.HomeFragment
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var searchTerm: String? = ""

    private lateinit var viewModel: MainViewModel

    val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent
            .builder()
            .activityModule(ActivityModule(this))
            .applicationComponent(YoutubeKeywordApplication.get(this).component)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        activityComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainViewModel::class.java)

        try{
            searchTerm = viewModel.getSubjectMatter()
        } catch (ex: NoSubjectMatterException) {

        }

        if (savedInstanceState == null) {

            if(searchTerm.isNullOrEmpty()) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
            } else {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, HomeFragment.newInstance())
                    .commitNow()
            }
        }
    }

    fun loadFragment(fragment: Fragment, action: String = "replace", addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()

        when(action) {
            "add" -> transaction.add(R.id.container, fragment)
            else -> transaction.replace(R.id.container, fragment)
        }

        if(addToBackStack) transaction.addToBackStack(null)

        transaction.commitNow()
    }


}
