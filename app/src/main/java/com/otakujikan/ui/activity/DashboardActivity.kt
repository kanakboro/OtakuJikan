package com.otakujikan.ui.activity

import android.view.View
import com.otakujikan.databinding.ActivityDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AbstractActivity() {

    private val binding: ActivityDashboardBinding by lazy {
        ActivityDashboardBinding.inflate(layoutInflater)
    }

    override fun initUI(): View = binding.root

    override fun initActions() {

    }

    override fun initObservers() {

    }

}