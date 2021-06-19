package ph.pulp.myapplication.di

import com.squareup.moshi.Moshi
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ph.pulp.myapplication.MainViewModel
import ph.pulp.myapplication.data.MainService
import ph.pulp.myapplication.data.repository.LocalRepository
import ph.pulp.myapplication.data.repository.LocalRepositoryImpl
import ph.pulp.myapplication.data.repository.MainRepository
import ph.pulp.myapplication.data.repository.MainRepositoryImpl
import ph.pulp.myapplication.util.BigDecimalAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val mainModule = module {

    single { get<Retrofit>().create(MainService::class.java)}

    single {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .dispatcher(Dispatcher().apply {
                maxRequests = 16
            })
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    single {
        Moshi.Builder()
            .add(BigDecimalAdapter)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<MainRepository> {
        MainRepositoryImpl(mainService = get())
    }

    single<LocalRepository> {
        LocalRepositoryImpl()
    }

    viewModel {
        MainViewModel(
            mainRepository = get(),
            localRepository = get()
        )
    }
}