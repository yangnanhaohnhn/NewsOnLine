package com.jdan.newsonline.presenter.impl

import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.jdan.newsonline.R
import com.jdan.newsonline.domain.constants.Config
import com.jdan.newsonline.mvp.BaseModel
import com.jdan.newsonline.mvp.BaseModelImpl
import com.jdan.newsonline.mvp.BasePresenterImpl
import com.jdan.newsonline.presenter.IGuidePresenter
import com.jdan.newsonline.ui.view.IGuideView

class GuidePresenterImpl(view: IGuideView) : BasePresenterImpl<IGuideView, BaseModel>(), IGuidePresenter {
    override fun pageChangeListener(): ViewPager.OnPageChangeListener {
        return object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                if (position == mImageViewList.size - 1){
                    mImageViewList[position].setOnClickListener {
                        sharedUtil!!.putBoolean(Config.IS_FIRST,false)
                        mvpView!!.startMainActivity()
                    }
                }
            }
        }
    }

    private var mImgList = arrayListOf<Int>()
    private var mImageViewList = arrayListOf<ImageView>()
    override fun loadVp() {
        mImgList.add(R.drawable.guide_1)
        mImgList.add(R.drawable.guide_2)
        mImgList.add(R.drawable.guide_3)

        for (i in mImgList){
            var iv = ImageView(mvpView!!.activityContext)
            iv.setBackgroundResource(i)
            mImageViewList.add(iv)
        }
        mvpView!!.guideVp.adapter = GuideVpAdapter(mImageViewList)
    }

    init {
        attachView(view, BaseModelImpl())
    }

    inner class GuideVpAdapter(var mImageViewList : ArrayList<ImageView>) : PagerAdapter(){

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view==`object`
        }

        override fun getCount(): Int {
            return mImageViewList.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            container.addView(mImageViewList.get(position))
            return mImageViewList.get(position)
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(mImageViewList.get(position))
        }
    }
}
