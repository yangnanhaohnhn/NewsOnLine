package com.jdan.newsonline.domain.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.jdan.newsonline.domain.adapter.holder.BaseViewHolder
import com.jdan.newsonline.listener.OnItemClickListener
import com.jdan.newsonline.listener.OnLongClickListener
import java.util.ArrayList

abstract class BaseRecyclerViewAdapter<D, H : BaseViewHolder>(private val mContext: Context, protected var mLists: ArrayList<D>) : RecyclerView.Adapter<H>(), View.OnClickListener, View.OnLongClickListener {

    private val inflater: LayoutInflater
    private var mOnItemClickListener: OnItemClickListener? = null
    private var mOnLongClickListener: OnLongClickListener? = null

    init {
        inflater = LayoutInflater.from(mContext)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.mOnItemClickListener = listener
    }

    fun setOnLongClickListener(listener: OnLongClickListener) {
        this.mOnLongClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H {
        val convertView = inflater.inflate(getLayoutId(viewType), parent, false)
        //创建的view注册点击事件
        convertView.setOnClickListener(this)
        convertView.setOnLongClickListener(this)
        return convert(convertView, viewType)
    }

    override fun getItemCount(): Int {
        return mLists.size
    }

    override fun onBindViewHolder(holder: H, position: Int) {
        //将position保存在itemView的tag中
        holder.itemView.tag = position
        bindView(holder,mLists,position)
    }

    fun setText(view: View, textStr: String): BaseRecyclerViewAdapter<*, *> {
        if (view is TextView) {
            view.text = textStr
        } else if (view is Button) {
            view.text = textStr
        } else if (view is EditText) {
            view.setText(textStr)
        }
        return this
    }

    /**
     * 是否隐藏,显示
     *
     * @param view
     * @param hide
     */
    fun isHide(view: View, hide: Boolean) {
        if (hide)
            view.visibility = View.GONE
        else
            view.visibility = View.VISIBLE
    }

    fun setText(view: View, textId: Int): BaseRecyclerViewAdapter<*, *> {
        return setText(view, mContext.getString(textId))
    }

    open fun clear() {
        mLists.clear()
        notifyDataSetChanged()
    }

    override fun onClick(v: View) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener!!.onItemClick(v, v.tag as Int)
        }
    }

    override fun onLongClick(v: View): Boolean {
        if (mOnLongClickListener != null) {
            mOnLongClickListener!!.onItemLongClick(v, v.tag as Int)
        }
        return true
    }

    protected abstract fun bindView(holder: H, mLists: ArrayList<D>, position: Int)

    protected abstract fun convert(convertView: View, viewType: Int): H

    protected abstract fun getLayoutId(viewType: Int): Int
}