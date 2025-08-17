package com.otakujikan.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.otakujikan.R
import com.otakujikan.databinding.FragmentHomeBinding
import com.otakujikan.domain.state.UiState
import com.otakujikan.viewmodel.HomeViewModel
import com.otakujikan.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : AbstractFragment() {

    /**
     * Binding for the HomeFragment.
     * This binding is used to access the views in the fragment's layout.
     */
    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    /**
     * ViewModel for the HomeFragment.
     * This ViewModel is responsible for fetching and managing the top anime data.
     */
    private val viewModel: HomeViewModel by viewModels()

    /**
     * Shared ViewModel to communicate between fragments.
     * This is used to pass data from HomeFragment to AnimeDetailsFragment.
     */
    private val sharedViewModel: SharedViewModel by activityViewModels()

    /**
     * This method is used to initialize the UI for the fragment.
     * @return The root view of the fragment.
     */
    override fun initUI(): View = binding.root

    /**
     * This method is used to initialize the actions for the fragment.
     * It sets up the ViewModel, binds the data, and initializes the swipe refresh layout.
     * @param v The view of the fragment.
     * @param savedInstanceState The saved instance state bundle.
     */
    override fun initActions(v: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        binding.executePendingBindings()
        binding.swipeRefresh.apply {
            setOnRefreshListener {
                viewModel.fetchTopAnime(true)
            }
        }
    }

    /**
     * This method is used to initialize the observers for the ViewModel.
     * It collects the events from the ViewModel and updates the UI accordingly.
     */
    override fun initObservers() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    // Collecting list item click events from the ViewModel
                    viewModel.listItemClickListener.collect { dataItem ->
                        dataItem?.let {
                            sharedViewModel.setAnimeData(it)
                            findNavController().navigate(R.id.action_home_to_anime_details)
                        }
                    }
                }

                launch {
                    // Collecting API UI events from the ViewModel
                    viewModel.uiEvents.collect { state ->
                        when (state) {
                            is UiState.Loading -> {
                                setLoading(true)
                            }

                            is UiState.Success -> {
                                setLoading(false)
                                viewModel.handleTopAnimeResponse(state.data)
                            }

                            is UiState.Error -> {
                                setLoading(false)
                                if (viewModel.adapter.getAllItems().isEmpty()) {
                                    Toast.makeText(
                                        requireContext(),
                                        state.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            is UiState.SessionExpired -> {
                                setLoading(false)
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * This method is used to set the loading state of the UI.
     * It shows or hides the swipe refresh layout based on the loading state.
     * @param isLoading Boolean indicating whether the UI is in a loading state.
     */
    private fun setLoading(isLoading: Boolean) {
        binding.swipeRefresh.isRefreshing = isLoading
    }

}