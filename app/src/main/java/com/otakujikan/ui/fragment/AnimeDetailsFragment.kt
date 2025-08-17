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
import com.otakujikan.databinding.FragmentAnimeDetailsBinding
import com.otakujikan.domain.state.UiState
import com.otakujikan.viewmodel.AnimeDetailsViewModel
import com.otakujikan.viewmodel.SharedViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AnimeDetailsFragment : AbstractFragment() {

    /**
     * Binding for the AnimeDetailsFragment.
     * This binding is used to access the views in the fragment's layout.
     */
    private val binding: FragmentAnimeDetailsBinding by lazy {
        FragmentAnimeDetailsBinding.inflate(layoutInflater)
    }

    private var youtubeId: String? = null
    private var youtubePlayer: YouTubePlayer? = null

    /**
     * ViewModel for the AnimeDetailsFragment.
     * This ViewModel is responsible for fetching and managing the anime details data.
     */
    private val viewModel: AnimeDetailsViewModel by viewModels()

    /**
     * Shared ViewModel to communicate between fragments.
     * This is used to accept data from HomeFragment to AnimeDetailsFragment.
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

        // Setting up the swipe refresh layout to refresh anime details
        binding.swipeRefresh.apply {
            setOnRefreshListener {
                viewModel.fetchAnimeDetails()
            }
        }

        // Setting up the back button to navigate back to the previous fragment
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        lifecycle.addObserver(binding.youtubePlayerView)

        // Adding a listener to the YouTube player view to handle video playback
        binding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youtubePlayer = youTubePlayer
                youtubeId?.let {
                    youTubePlayer.loadVideo(it, 1f)
                }
            }
        })

    }

    /**
     * This method is used to initialize observers for the ViewModel.
     * It collects data from the ViewModel and updates the UI accordingly.
     */
    override fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    // Collecting the anime data from the shared ViewModel
                    sharedViewModel.animeData.collect { anime ->
                        anime?.let {
                            viewModel.initData(anime)
                        }
                    }
                }

                launch {
                    // Collecting the playback listener to handle YouTube video playback
                    viewModel.playerPlayBackListener.collect { id ->
                        id?.let {
                            if (!youtubeId.equals(it,true)){
                                youtubeId = it
                                youtubePlayer?.loadVideo(it,1f)
                            }
                        }
                    }
                }

                launch {
                    // Collecting the API UI events from the ViewModel
                    viewModel.uiEvents.collect { state ->
                        when (state) {
                            is UiState.Loading -> {
                                setLoading(true)
                            }

                            is UiState.Success -> {
                                setLoading(false)
                                viewModel.handleUpdatedData(state.data)
                            }

                            is UiState.Error -> {
                                setLoading(false)
                                if (viewModel.animeDetails.get() == null) {
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
     * * This method is used to set the loading state of the UI.
     * It shows or hides the swipe refresh layout based on the loading state.
     * @param isLoading Boolean indicating whether the UI is in a loading state.
     */
    private fun setLoading(isLoading: Boolean) {
        binding.swipeRefresh.isRefreshing = isLoading
    }
}