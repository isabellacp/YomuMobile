package com.isabellacp.dev.app.yomu.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.isabellacp.dev.app.yomu.enums.ListingType
import com.isabellacp.dev.app.yomu.fragments.ListingFragment

class TabLayoutAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val ITEM_SIZE = 3

    override fun getItemCount(): Int {
        return ITEM_SIZE
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                ListingFragment.newInstance(ListingType.ANIME)
            }

            1 -> {
                ListingFragment.newInstance(ListingType.MANGA)
            }

            2 -> {
                ListingFragment.newInstance(ListingType.LIBRARY)
            }

            else -> ListingFragment.newInstance(ListingType.TRENDING_ANIME)
        }
    }

}