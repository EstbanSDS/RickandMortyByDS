package com.example.rickandmortybyds.utils.application

import com.google.gson.annotations.SerializedName

class ErrorApiResponse(
    @SerializedName("ram_code") var ramCode: Int? = null,
    @SerializedName("app_code") var appCode: String? = null,
    @SerializedName(
        "descripcion",
        alternate = ["error_description", "message"]
    ) var descripcion: String? = null,
    @SerializedName("data") var data: ArrayList<String> = arrayListOf(),
    @SerializedName("error") var error: String? = null
)