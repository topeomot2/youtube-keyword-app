package com.topeomot.youtubekeywordapp.ui.home

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log.i
import android.view.*

import com.topeomot.youtubekeywordapp.R
import com.topeomot.youtubekeywordapp.adapter.SearchListAdapter
import com.topeomot.youtubekeywordapp.model.Snippet
import com.topeomot.youtubekeywordapp.model.YoutubeResponse
import com.topeomot.youtubekeywordapp.model.exception.NoSubjectMatterException
import com.topeomot.youtubekeywordapp.ui.ViewModelFactory
import com.topeomot.youtubekeywordapp.ui.main.MainActivity
import com.topeomot.youtubekeywordapp.ui.main.MainFragment
import com.topeomot.youtubekeywordapp.ui.main.MainViewModel
import com.topeomot.youtubekeywordapp.util.DeviceUtils
import com.topeomot.youtubekeywordapp.util.YoutubeKeywordConstant
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.home_fragment.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import javax.inject.Inject

class HomeFragment : Fragment(), AnkoLogger {

    companion object {
        fun newInstance() = HomeFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var deviceUtils: DeviceUtils

    private lateinit var viewModel: MainViewModel

    var searchListAdapter: SearchListAdapter? = null

    private val disposable: CompositeDisposable = CompositeDisposable()
    var searchResults: ArrayList<Snippet> = ArrayList()
    var searchTerm: String = ""
    var pageToken = ""

    var screenWidth: Int = 0
    var orientation: String = YoutubeKeywordConstant.PORTRAIT
    var imageWidth: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).activityComponent.inject(this)


        orientation = deviceUtils.getOrientation(activity as MainActivity)
        var displayDimension = deviceUtils.getScreenSize(((activity as MainActivity)
            .getSystemService(Context.WINDOW_SERVICE)) as WindowManager)
        screenWidth = displayDimension[0]

        if(orientation.equals(YoutubeKeywordConstant.LANDSCAPE)) {
            imageWidth = screenWidth / 2
        } else {
            imageWidth = screenWidth
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setLogo(R.drawable.ic_launcher_round)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainViewModel::class.java)

        try{
            searchTerm = viewModel.getSubjectMatter()
            (activity as AppCompatActivity).supportActionBar!!.title = "Keyword: ${searchTerm}"

            disposable.add(
                viewModel.search(searchTerm!!, pageToken)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response: YoutubeResponse? ->
                        processList(response!!.items)

                    },
                        { error -> error("ERROR",error)})
            )


        } catch (ex: NoSubjectMatterException) {

        }
    }


    private fun processList(searchList: ArrayList<Snippet>) {

        progressBar.visibility = View.GONE

        if(searchList.isNotEmpty()) {
            searchResults = searchList
            searchListAdapter = SearchListAdapter(activity!!, searchList, imageWidth)
            recyclerview.adapter = searchListAdapter
            var layoutManager = LinearLayoutManager(this.context)
            if(orientation.equals(YoutubeKeywordConstant.LANDSCAPE)){
                layoutManager = GridLayoutManager(this.context, 2)
            }

            recyclerview.layoutManager = layoutManager
            recyclerview.itemAnimator = DefaultItemAnimator()

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.change_keyword -> {
                (activity as MainActivity).loadFragment(MainFragment.newInstance(searchTerm),
                    addToBackStack = false)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
