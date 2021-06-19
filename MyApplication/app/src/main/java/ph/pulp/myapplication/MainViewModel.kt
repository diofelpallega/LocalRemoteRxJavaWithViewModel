package ph.pulp.myapplication

import android.util.Log
import androidx.lifecycle.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ph.pulp.myapplication.data.ApiEndPoint
import ph.pulp.myapplication.data.model.WeatherDataResponse
import ph.pulp.myapplication.data.repository.LocalRepository
import ph.pulp.myapplication.data.repository.MainRepository
import ph.pulp.myapplication.util.ErrorHandler
import ph.pulp.myapplication.util.SingleLiveEvent
import timber.log.Timber
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

internal class MainViewModel(
    private val mainRepository: MainRepository,
    private val localRepository: LocalRepository
) : ViewModel(), ErrorHandler {

    private val _weatherListLiveData = MutableLiveData<WeatherDataResponse>()
    val weatherListLiveData: LiveData<WeatherDataResponse> = _weatherListLiveData

    private val loadingCount = AtomicInteger(0)

    private val _isLoadingLiveData = SingleLiveEvent<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> = _isLoadingLiveData

    var disposable: Disposable? = null

    fun getWeatherList() {
        launchWithErrorHandling {
            mainRepository.getWeatherList(
                ApiEndPoint.LONDON_ID,
                BuildConfig.WEATHER_API_KEY)
                .onErrorResumeNext {
                    localRepository.getWeatherList()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this@MainViewModel::handleResponse)
        }
    }

    private fun handleResponse(response: WeatherDataResponse) {
        _weatherListLiveData.value = response
    }

    private fun launchWithErrorHandling(
        context: CoroutineContext = EmptyCoroutineContext,
        handleLoading: Boolean = false,
        call: suspend CoroutineScope.() -> Unit
    ): Job = viewModelScope.launch(context) {
        try {
            if (handleLoading) _isLoadingLiveData.postValue(loadingCount.incrementAndGet() > 0)
            call()
        } catch (error: Throwable) {
            handleError(error)
        } finally {
            if (handleLoading) _isLoadingLiveData.postValue(loadingCount.decrementAndGet() > 0)
        }
    }

    override fun handleError(error: Throwable) {
        Log.d("Errors", error.toString())
        Timber.d("Error = " + error.localizedMessage)
    }
}