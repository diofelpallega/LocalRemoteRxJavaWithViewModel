package ph.pulp.myapplication.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherDataResponse(
    val cnt: Int? = null,
    val list: kotlin.collections.List<List?>? = null
)