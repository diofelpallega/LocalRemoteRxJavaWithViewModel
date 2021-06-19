package ph.pulp.myapplication.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class BaseResponse(open val code: String)