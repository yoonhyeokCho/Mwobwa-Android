package com.bongku.mwobwa.ui.home

import android.os.Bundle
import android.util.Log
import android.view.ContentInfo
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bongku.mwobwa.BuildConfig
import com.bongku.mwobwa.R
import com.bongku.mwobwa.data.entity.ContentsEntity
import com.bongku.mwobwa.data.entity.ContentsResult
import com.bongku.mwobwa.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.random.Random

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private var contentsList: ArrayList<ContentsResult> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contentsList.clear()


        viewModel.getOttCompany()
        initOttCompanyObserver()
        initContentsObserver()
    }

    private fun initContentsObserver() {
        viewModel.contentsResponse.observe(viewLifecycleOwner, Observer {
            val contents = getRandomContents(it)
            contentsList.add(contents)
            initAdapter(contentsList)
        })
    }

    private fun initOttCompanyObserver() {
        viewModel.ottCompany.observe(viewLifecycleOwner, Observer {
            for (company in it) {
                initGetContents("movie", false, 1, company)
            }
        })
    }

    private fun initGetContents(
        mediaType: String,
        includeAdult: Boolean,
        page: Int,
        company: String
    ) {
        viewModel.getContents(mediaType, includeAdult, page, company)
    }

    private fun getRandomContents(contents: ContentsEntity): ContentsResult {

        val randomIndex = Random.nextInt(contents.results.size - 1)
        return contents.results[randomIndex]
    }

    private fun initAdapter(contents: List<ContentsResult>) {
        val rvAdapter = HomeRVAdapter(requireContext(), contents)
        binding.run {
            homeNetflixRv.adapter = rvAdapter
            homeNetflixRv.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}