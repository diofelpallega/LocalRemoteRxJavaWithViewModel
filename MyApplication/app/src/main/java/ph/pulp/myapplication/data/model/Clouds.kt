package ph.pulp.myapplication.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Clouds(
    val all: Int? = null,
)