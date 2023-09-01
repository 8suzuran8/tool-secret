package com.example.tool_secret

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.tool_secret.ui.main.MainFragment

class ToolSecretViewPager constructor(activity: FragmentActivity, globalActivityInfo: MutableMap<String, String>) {
    var viewPager: ViewPager2 = activity.findViewById(R.id.view_pager2)
    var adapter: ScreenSlidePagerAdapter? = null

    init {
        // Instantiate a ViewPager2 and a PagerAdapter.
        // The pager adapter, which provides the pages to the view pager widget.
        this.adapter = ScreenSlidePagerAdapter(activity, globalActivityInfo)
        this.viewPager.adapter = this.adapter
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    inner class ScreenSlidePagerAdapter(activity: FragmentActivity,
                                        private val globalActivityInfo: MutableMap<String, String>
    ) : FragmentStateAdapter(activity) {
        var fragments: MutableMap<Int, MainFragment> = mutableMapOf()

        /**
         * 0: 表示
         * 1: textarea プレーン表示
         * 2: textarea 暗号表示
         */
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            this.fragments[position] = MainFragment(this.globalActivityInfo)

            this.fragments[position]!!.arguments = Bundle().apply {
                putInt("pageNumber", position)
            }

            return this.fragments[position]!!
        }
    }
}
