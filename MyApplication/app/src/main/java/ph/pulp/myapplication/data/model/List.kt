package ph.pulp.myapplication.data.model

import com.squareup.moshi.JsonClass
import kotlin.collections.List

@JsonClass(generateAdapter = true)
data class List(
    val coord: Coord? = null,
    val sys: Sys? = null,
    val weather: List<Weather>,
    val main: Main? = null,
    val visibility: Int? = null,
    val wind: Wind? = null,
    val clouds: Clouds? = null,
    val dt: Int? = null,
    val id: Int? = null,
    val name: String? = null
)
