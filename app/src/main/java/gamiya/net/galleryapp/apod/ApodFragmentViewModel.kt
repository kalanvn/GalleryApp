package gamiya.net.galleryapp.apod

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApodFragmentViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()

    val response : LiveData<String>
        get() = _response

    private val _apod = MutableLiveData<Apod>()

    val apod : LiveData<Apod>
        get() = _apod

    fun getApod(){

        ApodService.ApodApi.apodService.getImageOfADayApod(/*date = "2020-01-01"*/).enqueue(
            object : Callback<Apod> {
                override fun onResponse(call: Call<Apod>, response: Response<Apod>) {
                    _apod.value = response.body()
                }

                override fun onFailure(call: Call<Apod>, t: Throwable) {

                }
            }
        )

        //get string
//         ApodService.ApodApi.apodService.getImageOfADay(/*date = "2020-01-01"*/).enqueue(
//             object : Callback<String> {
//                 override fun onResponse(call: Call<String>, response: Response<String>) {
//                     _response.value = response.body()
//                 }
//
//                 override fun onFailure(call: Call<String>, t: Throwable) {
//                     _response.value = "Failure : " + t.message
//                 }
//
//             }
//         )
    }
}