package ph.pulp.myapplication.data.repository

import io.reactivex.rxjava3.core.Observable
import ph.pulp.myapplication.data.MainService
import ph.pulp.myapplication.data.model.WeatherDataResponse

internal interface MainRepository {

    suspend fun getWeatherList(ids: String, apiKey: String): io.reactivex.Single<WeatherDataResponse>
}

internal class MainRepositoryImpl(
    private val mainService: MainService
): MainRepository {

    override suspend fun getWeatherList(ids: String, apiKey: String): io.reactivex.Single<WeatherDataResponse> = mainService.getWeatherList(
        ids, "metric", apiKey
    )
}