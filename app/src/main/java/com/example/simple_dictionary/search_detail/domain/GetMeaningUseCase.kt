package com.example.simple_dictionary.search_detail.domain

import com.example.simple_dictionary.common.data.api.SkyengApi
import com.example.simple_dictionary.core.mappers.MeaningMapper
import com.example.simple_dictionary.core.mappers.MeaningWrapper
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetMeaningUseCase @Inject constructor(
    private val skyengApi: SkyengApi,
    private val meaningMapper: MeaningMapper
) {

    fun execute(id: Int): Single<MeaningWrapper> {
        return skyengApi.getMeaning(id)
            .map { it.first() }
            .map(meaningMapper::mapItem)
    }
}