package com.otakujikan.ui.fragment


import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class AbstractFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initUI()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActions(view,savedInstanceState)
        initObservers()
    }

    abstract fun initUI(): View

    abstract fun initActions(v: View, savedInstanceState: Bundle?)

    abstract fun initObservers()
}