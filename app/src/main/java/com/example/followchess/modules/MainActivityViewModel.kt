package com.example.followchess.modules

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.followchess.models.Trn
import com.example.followchess.models.Trophy
import com.example.followchess.network.ApiCallFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel(application: Application):AndroidViewModel(application) {

    val configJsonArrayList=MutableLiveData<Trophy>()
    val isLoading=MutableLiveData<Boolean>()

    @SuppressLint("CheckResult")
    fun getConfigJsonList(){
        ApiCallFactory.getRetrofitInstance().getConfigJsonDetail().
        subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{isLoading.postValue(true)}
            .doOnError{isLoading.postValue(false)}
            .doOnComplete {isLoading.postValue(false)  }
            .subscribe({result->
                configJsonArrayList.postValue(result)
                Log.d("The size is",result.trns.size.toString())
            },{error->

            })
    }

}