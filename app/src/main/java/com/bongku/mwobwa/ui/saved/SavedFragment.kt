package com.bongku.mwobwa.ui.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.bongku.mwobwa.data.entity.SavedContentEntity
import com.bongku.mwobwa.databinding.FragmentSavedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedFragment : Fragment() {

    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SavedViewModel by viewModels()

    private var roomDataList: ArrayList<SavedContentEntity> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRoomData()
        initObserver()
    }

    private fun initObserver() {

        viewModel.roomData.observe(viewLifecycleOwner, Observer {
            roomDataList.clear()
            for (data in it) {
                roomDataList.add(data)
            }

            if (!roomDataList.isEmpty()) {
                initAdapter()
            }
        })
    }

    private fun initAdapter() {
        val rvAdapter = SavedRVAdapter(requireContext(), roomDataList)
        val customSwipeHelper = CustomSwipeHelper(rvAdapter).apply {
            setClamp(resources.displayMetrics.widthPixels.toFloat() / 8)
        }
        ItemTouchHelper(customSwipeHelper).attachToRecyclerView(binding.savedRv)

        binding.run {
            savedRv.adapter = rvAdapter
            savedRv.layoutManager = LinearLayoutManager(requireContext())
            savedRv.setOnTouchListener { _, _ ->
                customSwipeHelper.removePreviousClamp(savedRv)
                false
            }
        }
        rvAdapter.itemClick = object : SavedRVAdapter.ItemClick {
            override fun onDeleteClick(savedContentEntity: SavedContentEntity) {
                viewModel.deleteRoomData(savedContentEntity)
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}