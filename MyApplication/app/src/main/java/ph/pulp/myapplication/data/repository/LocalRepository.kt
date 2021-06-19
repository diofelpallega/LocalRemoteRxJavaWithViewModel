package ph.pulp.myapplication.data.repository

import io.reactivex.Maybe
import io.reactivex.MaybeObserver
import io.reactivex.Observable
import io.reactivex.Single
import ph.pulp.myapplication.data.MainService
import ph.pulp.myapplication.data.model.WeatherDataResponse

internal interface  LocalRepository {

    fun getWeatherList(): io.reactivex.Single<WeatherDataResponse>?

}

internal class LocalRepositoryImpl(): LocalRepository {

    override fun getWeatherList(): Single<WeatherDataResponse>? {

        return Single.just(WeatherDataResponse(2,null))
    }

}