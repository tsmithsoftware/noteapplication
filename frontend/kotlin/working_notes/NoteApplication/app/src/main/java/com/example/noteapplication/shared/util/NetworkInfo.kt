package com.example.noteapplication.shared.util

import android.content.Context
import androidx.databinding.ObservableBoolean

interface NoteNetworkInfo {
    fun isConnected(): ObservableBoolean
}

class NoteNetworkInfoImpl(val context: Context): NoteNetworkInfo {
    override fun isConnected(): ObservableBoolean {
        //val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        //val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        //val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return ObservableBoolean(false)
    }
}