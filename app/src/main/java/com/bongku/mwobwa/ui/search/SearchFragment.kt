package com.bongku.mwobwa.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bongku.mwobwa.data.entity.ContentsResult
import com.bongku.mwobwa.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    private var moviecontentsList: ArrayList<ContentsResult> = ArrayList()
    private var tvContentsList: ArrayList<ContentsResult> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSearchBtn()
        initSearchObserver()
        initEditText()
    }

    private fun initSearchObserver() {

        viewModel.searchMovieContentsResponse.observe(viewLifecycleOwner) {
            moviecontentsList.clear()
            for (result in it.results) {
                moviecontentsList.add(result)
            }

            val rvAdapter = SearchRVAdapter(requireContext(), moviecontentsList)
            binding.run {
                searchMovieRv.adapter = rvAdapter
                searchMovieRv.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }
        }

        viewModel.searchTvContentsResponse.observe(viewLifecycleOwner) {
            tvContentsList.clear()
            for (result in it.results) {
                tvContentsList.add(result)
            }

            val rvAdapter = SearchRVAdapter(requireContext(), tvContentsList)
            binding.run {
                searchTvRv.adapter = rvAdapter
                searchTvRv.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    private fun updateSearchBtn() {
        binding.run {
            if (!searchEditText.text.toString().isEmpty()) {
                searchBtn.isEnabled = true
            } else {
                searchBtn.isEnabled = false
            }
        }
    }

    private fun initEditText() {
        binding.searchEditText.addTextChangedListener {
            updateSearchBtn()
        }
    }

    private fun initSearchBtn() {
        binding.run {
            searchBtn.setOnClickListener {
                val title = searchEditText.text.toString()
                viewModel.getSearchContents("movie", title, false, 1)
                viewModel.getSearchContents("tv", title, false, 1)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}