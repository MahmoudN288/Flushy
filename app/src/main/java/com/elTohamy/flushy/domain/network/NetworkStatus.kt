package com.elTohamy.flushy.domain.network

sealed class NetworkStatus {
    data object Unknown: NetworkStatus()
    data object Connected: NetworkStatus()
    data object Disconnected: NetworkStatus()
}