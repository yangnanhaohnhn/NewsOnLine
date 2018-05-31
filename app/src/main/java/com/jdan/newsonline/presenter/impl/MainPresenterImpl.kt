package com.jdan.newsonline.presenter.impl

import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.jdan.newsonline.R
import com.jdan.newsonline.domain.constants.Config
import com.jdan.newsonline.mvp.BaseModel
import com.jdan.newsonline.mvp.BaseModelImpl
import com.jdan.newsonline.mvp.BasePresenterImpl
import com.jdan.newsonline.presenter.IMainPresenter
import com.jdan.newsonline.ui.fragment.MineFragment
import com.jdan.newsonline.ui.fragment.NewsFragment
import com.jdan.newsonline.ui.fragment.NoticeFragment
import com.jdan.newsonline.ui.fragment.VideoFragment
import com.jdan.newsonline.ui.view.IMainView
import com.orhanobut.logger.Logger

class MainPresenterImpl(view: IMainView) : BasePresenterImpl<IMainView, BaseModel>(), IMainPresenter {
    private var mFragmentList = arrayListOf<Fragment>()

    init {
        attachView(view, BaseModelImpl())
    }

    override fun createFragments(fm: FragmentManager?) {
        mFragmentList.clear()
        mFragmentList.add(NewsFragment.newInstance(Config.FRAGMENT_TAG_NEWS))
        mFragmentList.add(VideoFragment.newInstance(Config.FRAGMENT_TAG_VIDEO))
        mFragmentList.add(NoticeFragment.newInstance(Config.FRAGMENT_TAG_NOTICE))
        mFragmentList.add(MineFragment.newInstance(Config.FRAGMENT_TAG_MINE))

        var adapter = MainPagerAdapter(fm,mFragmentList)
        mvpView!!.mainVp.adapter = adapter
        mvpView!!.mainVp.currentItem = 0 //设置当前显示标签页为第一页
    }

    override fun onPageChangeListener(): ViewPager.OnPageChangeListener {
        return object : ViewPager.OnPageChangeListener{
            /**
             * @param state The new scroll state.
             * @see ViewPager.SCROLL_STATE_IDLE
             *
             * @see ViewPager.SCROLL_STATE_DRAGGING
             *
             * @see ViewPager.SCROLL_STATE_SETTLING
             */
            override fun onPageScrollStateChanged(state: Int) {
            }

            /**
             * This method will be invoked when the current page is scrolled, either as part
             * of a programmatically initiated smooth scroll or a user initiated touch scroll.
             *
             * @param position Position index of the first page currently being displayed.
             * Page position+1 will be visible if positionOffset is nonzero.
             * @param positionOffset Value from [0, 1) indicating the offset from the page at position.
             * @param positionOffsetPixels Value in pixels indicating the offset from position.
             */
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            /**
             * This method will be invoked when a new page becomes selected. Animation is not
             * necessarily complete.
             *
             * @param position Position index of the new selected page.
             */
            override fun onPageSelected(position: Int) {
                when(position){
                    0 ->
                            mvpView!!.mainNavigation.selectedItemId = R.id.main_bottom_news
                    1 ->
                            mvpView!!.mainNavigation.selectedItemId = R.id.main_bottom_video
                    2 ->
                            mvpView!!.mainNavigation.selectedItemId = R.id.main_bottom_notice
                    3 ->
                            mvpView!!.mainNavigation.selectedItemId = R.id.main_bottom_mine

                }
            }
        }
    }

    override fun navigationItemSelectedListener(): BottomNavigationView.OnNavigationItemSelectedListener? {
        return BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.main_bottom_news ->
                    clickItem(0)
                R.id.main_bottom_video ->
                    clickItem(1)
                R.id.main_bottom_notice ->
                    clickItem(2)
                R.id.main_bottom_mine ->
                    clickItem(3)
                else ->
                    false
            }
        }
    }

    private fun clickItem(i: Int): Boolean {
        Logger.e("...$i")
        mvpView!!.mainVp.currentItem = i
        return true
    }

    inner class MainPagerAdapter(fm:FragmentManager?, private val fragmentList:ArrayList<Fragment>) : FragmentPagerAdapter(fm) {
        /**
         * Return the Fragment associated with a specified position.
         */
        override fun getItem(position: Int): Fragment {
            return fragmentList.get(position)
        }

        /**
         * Return the number of views available.
         */
        override fun getCount(): Int {
            return fragmentList.size
        }
    }
}
