package ph.pulp.myapplication.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataResponse<out T>(
    override val code: String,
    val data: T
): BaseResponse(code)
