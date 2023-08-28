package com.bongku.mwobwa.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bongku.mwobwa.R
import com.bongku.mwobwa.data.entity.ContentsEntity
import com.bongku.mwobwa.data.entity.ContentsResult
import com.bongku.mwobwa.data.entity.SavedContentEntity
import com.bongku.mwobwa.data.entity.TvContentsEntity
import com.bongku.mwobwa.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private var contentsList: ArrayList<ContentsResult> = ArrayList()

    private var media = "movie"

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

        if (viewModel.previousContents != null) {
            initAdapter(viewModel.previousContents!!)
        } else {
            viewModel.getOttCompany()
        }
        initOttCompanyObserver()
        initContentsObserver()
        initMediaType()
        initRefreshBtn()
    }

    private fun convertTvcontentsToContents(tvContents: TvContentsEntity): ContentsEntity {
        val convertedResults = tvContents.results.map { tvResult ->
            ContentsResult(
                tvResult.genreIDS,
                tvResult.original_name,
                tvResult.overview,
                tvResult.popularity,
                tvResult.name,
                tvResult.voteAverage,
                tvResult.voteCount,
                tvResult.company,
                tvResult.poster_path
            )
        }
        return ContentsEntity(convertedResults)
    }

    private fun initContentsObserver() {
        viewModel.contentsMovieResponse.observe(viewLifecycleOwner, Observer {
            val contents = getRandomContents(it)
            contentsList.add(contents)

            viewModel.previousContents = contentsList.toList()
            initAdapter(contentsList)
        })

        viewModel.contentsTvResponse.observe(viewLifecycleOwner, Observer {
            val convertedContents = convertTvcontentsToContents(it)
            val contents = getRandomContents(convertedContents)
            contentsList.add(contents)

            viewModel.previousContents = contentsList.toList()
            initAdapter(contentsList)
        })
    }

    private fun initOttCompanyObserver() {
        viewModel.ottCompany.observe(viewLifecycleOwner, Observer {
            for (company in it) {
                val randomPage = getRandomPage()
                initGetContents(media, randomPage, company, "KR")
            }
        })
    }

    private fun initGetContents(
        mediaType: String,
        page: Int,
        company: String,
        region: String
    ) {
        var provider = 0
        if (company == "netflix") {
            provider = 8
        } else if (company == "apple tv") {
            provider = 2
        } else {
            provider = 337
        }

        viewModel.getContents(mediaType, page, provider, region)
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
        rvAdapter.itemClick = object : HomeRVAdapter.ItemClick {
            override fun onGgimClick(contentsEntity: ContentsResult) {
                val insertedRoomData = SavedContentEntity(
                    0,
                    contentsEntity.title,
                    contentsEntity.voteAverage,
                    contentsEntity.voteCount,
                    contentsEntity.company,
                    contentsEntity.poster_path
                )

                viewModel.insertRoomData(insertedRoomData)
            }

        }
    }

    private fun getRandomPage(): Int {
        val randompage = Random.nextInt(1, 21)
        return randompage
    }

    private fun reloadContents() {
        contentsList.clear()
        viewModel.getOttCompany()
    }

    private fun initMediaType() {
        binding.run {
            homeMovie.setOnClickListener {
                if (media == "tv") {
                    media = "movie"
                    it.setBackgroundResource(R.drawable.bg_round_app_color_10dp)
                    homeTv.setBackgroundResource(R.drawable.bg_round_gray_10dp)

                    reloadContents()
                }
            }

            homeTv.setOnClickListener {
                if (media == "movie") {
                    media = "tv"
                    it.setBackgroundResource(R.drawable.bg_round_app_color_10dp)
                    homeMovie.setBackgroundResource(R.drawable.bg_round_gray_10dp)

                    reloadContents()
                }
            }
        }
    }

    private fun initRefreshBtn() {
        binding.homeRefresh.setOnClickListener {
            reloadContents()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}