package com.example.practicalproject.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicalproject.Network.ApiService
import com.example.practicalproject.Network.RetrofitHelper
import com.example.practicalproject.modelclass.CoinDetails
import com.example.practicalproject.modelclass.Coins
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val service = RetrofitHelper().retrofit.create(ApiService::class.java)

    private val _data: MutableLiveData<List<CoinDetails>> = MutableLiveData()
    val data: MutableLiveData<List<CoinDetails>> get() = _data


    fun getCoins() {

        viewModelScope.launch {

            try {
                val response: Response<Coins> = service.getcoins()
                if(response.isSuccessful ){}
                val result= response.body()?.data?.list
                _data.postValue(result!!)


                Log.d("reult",result.toString())
            }catch (e:Exception){

                Log.d("exceptionerror",e.toString())
            }
        }

    }
}