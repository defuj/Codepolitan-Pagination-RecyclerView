package com.ysn.codepolitan_pagination

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_data_content.view.*

/**
 * Created by root on 21/09/17.
 */

class AdapterData(private var listData: List<String>, private var listViewType: List<Int>) : RecyclerView.Adapter<AdapterData.ViewHolder>() {

    private val TAG = javaClass.simpleName
    companion object {
        val ITEM_VIEW_TYPE_CONTENT = 1
        val ITEM_VIEW_TYPE_LOADING = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        return when (viewType) {
            ITEM_VIEW_TYPE_CONTENT -> ViewHolderContent(
                    layoutInflater.inflate(R.layout.item_data_content, null)
            )
            else -> ViewHolderLoading(
                    layoutInflater.inflate(R.layout.item_data_loading, null)
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val viewType = listViewType[position]
        val data = listData[position]
        when (viewType) {
            ITEM_VIEW_TYPE_CONTENT -> {
                holder?.itemView
                        ?.text_view_number_item_data_content
                        ?.text = data
            }
            else -> {
                /** nothing to do in here */
            }
        }
    }

    override fun getItemCount(): Int = listData.size

    override fun getItemViewType(position: Int): Int = listViewType[position]

    fun refresh(listData: ArrayList<String>, listViewType: ArrayList<Int>) {
        Log.d(TAG, "refresh")
        this.listData = listData
        this.listViewType = listViewType
        notifyDataSetChanged()
    }

    open class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    inner class ViewHolderContent(itemView: View?) : ViewHolder(itemView)

    inner class ViewHolderLoading(itemView: View?) : ViewHolder(itemView)

}
