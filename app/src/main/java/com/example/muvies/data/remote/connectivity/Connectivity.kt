package com.example.muvies.data.remote.connectivity

import timber.log.Timber
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

object Connectivity {
    fun hasConnectivity(): Boolean {
        return try {
            val socket = Socket()
            socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            socket.close()
            Timber.d("Connection:::PING Success")
            true
        } catch (e: IOException) {
            Timber.d("Connection:::PING Error.$e")
            false
        }
    }
}
