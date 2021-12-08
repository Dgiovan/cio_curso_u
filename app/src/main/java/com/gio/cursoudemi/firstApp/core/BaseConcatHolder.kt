package com.gio.cursoudemi.firstApp.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseConcatHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView) {
     abstract fun bin(adapter: T)
}