package com.elTohamy.flushy.data.remote.flushyViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elTohamy.flushy.data.remote.dto.timezone.Timezone
import com.elTohamy.flushy.data.repos.FlushyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

@HiltViewModel
class FlushyViewModel @Inject constructor(private val inPlayMatchesRepository: FlushyRepository): ViewModel() {
    //Timezone
    private val _timezoneData = mutableStateListOf<String>()
    val timezoneData : MutableList<String> = _timezoneData

    var loading: Boolean by mutableStateOf(false)

    init{
        viewModelScope.launch {
            retrieveTimezones()
        }
    }

    private fun retrieveTimezones(){
        viewModelScope.launch {
            loading = true
            val call : Call<Timezone> = inPlayMatchesRepository.getTimezone()
            call.enqueue(object : Callback<Timezone> {
                override fun onResponse(
                    call: Call<Timezone>,
                    response: retrofit2.Response<Timezone>
                ) {
                    if(response.isSuccessful){
                        val responseData: List<String?>? = response.body()?.response
                        for (i in 0 until responseData!!.size) {
                            // on below line we are adding data to course list.
                            _timezoneData.add(responseData[i]!!)
                        }
                        loading = false
                    } else{
                        loading = false
                    }
                }

                override fun onFailure(call: Call<Timezone>, t: Throwable) {
                    loading = false
                    Log.d("Failed Retrieve", "Network Error")
                }

            })
        }
    }
}