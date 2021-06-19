package ph.pulp.myapplication.data

import io.reactivex.Single
import ph.pulp.myapplication.data.model.WeatherDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface MainService {

    @GET(ApiEndPoint.WEATHER_DATA_BY_GROUP_ID)
    fun getWeatherList(
        @Query("id") ids: String,
        @Query("units") units: String,
        @Query("apiKey") apiKey: String
    ): Single<WeatherDataResponse>
}