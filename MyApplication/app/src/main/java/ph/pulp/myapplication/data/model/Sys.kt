package ph.pulp.myapplication.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sys(
    val type: Int? = null,
    val id: Int? = null,
    val message: Double? = null,
    val country: String? = null,
    val sunrise: Int? = null,
    val sunset: Int? = null
)