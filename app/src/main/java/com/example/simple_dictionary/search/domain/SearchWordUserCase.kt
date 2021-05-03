package com.example.simple_dictionary.search.domain

import com.example.simple_dictionary.common.data.api.SkyengApi
import com.example.simple_dictionary.core.mappers.SearchResultMapper
import com.example.simple_dictionary.search.presenter.model.SearchResultItem
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchWordUserCase @Inject constructor(
    private val skyengApi: SkyengApi,
    private val searchResultMapper: SearchResultMapper
) {
    fun execute(search: String): Single<List<SearchResultItem>> {
        return skyengApi.search(search)
            .map { items ->
                items.map { item -> searchResultMapper.mapItem(item) }
            }
    }
}

