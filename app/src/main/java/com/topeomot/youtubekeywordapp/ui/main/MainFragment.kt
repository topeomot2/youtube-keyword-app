package com.topeomot.youtubekeywordapp.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.topeomot.youtubekeywordapp.R
import com.topeomot.youtubekeywordapp.ui.ViewModelFactory
import com.topeomot.youtubekeywordapp.ui.home.HomeFragment
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject
import org.jetbrains.anko.longToast

class MainFragment : Fragment() {

    companion object {
        private val EXTRA_KEYWORD: String = "searchTerm"

        fun newInstance(keyword: String? = ""): Fragment{
            val fragment = MainFragment()
            if(keyword.isNullOrBlank())
                return fragment

            val bundle = Bundle(1)
            bundle.putString(EXTRA_KEYWORD, keyword)
            fragment.arguments = bundle
            return fragment
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MainViewModel

    private var keyword: String = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).activityComponent.inject(this)

        keyword = arguments?.getString(EXTRA_KEYWORD) ?: ""

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(keyword.isNotBlank())
            subjectEditText.text = SpannableStringBuilder(keyword)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainViewModel::class.java)

        subjectSubmitBtn.setOnClickListener { btn: View? ->
            subjectSubmitBtn.visibility = View.GONE
            progressBar.visibility = View.VISIBLE

            if(subjectEditText.text!!.isNullOrBlank() ) {
                activity!!.longToast(R.string.empty_keyword_warning)
                progressBar.visibility = View.GONE
                subjectSubmitBtn.visibility = View.VISIBLE
            } else {
                viewModel.setSubjectMatter(subjectEditText.text.toString())
                (activity as MainActivity).loadFragment(
                    fragment = HomeFragment.newInstance(),
                    addToBackStack = false)
            }
        }
    }

}
