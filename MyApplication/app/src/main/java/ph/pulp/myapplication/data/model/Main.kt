package ph.pulp.myapplication.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Main(
    val temp: Double? = null,
    val pressure: Int? = null,
    val humidity: Int? = null,
    val tempMin: Int? = null,
    val tempMax: Int? = null
)