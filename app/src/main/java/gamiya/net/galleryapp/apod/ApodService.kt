package gamiya.net.galleryapp.apod

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*


private const val BASE_URL = "https://api.nasa.gov/planetary/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private var retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApodService {
    object ApodApi {
        //lazy initialization
        //https://kotlinlang.org/docs/reference/delegated-properties.html#lazy
        val apodService : ApodService by lazy {
            retrofit.create(ApodService::class.java)
        }
    }

    //REST standard GET method to get resources from a web service URI
    @GET("apod")
    fun getImageOfToday(
        @Query("api_key") apiKey: String? = "DEMO_KEY")
    : Call<String>

    @GET("apod")
    fun getImageOfADay(
        @Query("api_key") apiKey: String? = "DEMO_KEY",
        @Query("date") date: String? = null)
            : Call<String>

    @GET("apod")
    fun getImageOfADayApod(
        @Query("api_key") apiKey: String? = "DEMO_KEY",
        @Query("date") date: String? = null)
            : Call<Apod>
}