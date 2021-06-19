package ph.pulp.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin
import org.koin.androidx.workmanager.koin.workManagerFactory
import ph.pulp.myapplication.di.mainModule
import kotlinx.android.synthetic.main.activity_weather_app.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_app)

        // initialize koin
        startKoin {
            androidContext(this@MainActivity)
            workManagerFactory()
            modules(listOf(mainModule))
        }.koin

        viewModel.getWeatherList()
        viewModel.weatherListLiveData.observe(this) {
            countryNameTextView.text = it.cnt.toString()
        }
    }
}