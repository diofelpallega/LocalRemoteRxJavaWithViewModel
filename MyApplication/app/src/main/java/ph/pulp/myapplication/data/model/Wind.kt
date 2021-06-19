package ph.pulp.myapplication.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Wind(
    val speed: Double? = null,
    val deg: Int? = null
)