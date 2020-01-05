package com.god.taeiim.myapplication.ui

import com.god.taeiim.myapplication.Tabs
import com.god.taeiim.myapplication.api.model.SearchResult
import com.god.taeiim.myapplication.api.model.SearchResultShow
import com.god.taeiim.myapplication.data.SearchHistory
import com.god.taeiim.myapplication.data.source.NaverRepository

class ContentsPresenter(
    private val naverRepository: NaverRepository,
    private val view: ContentsContract.View
) : ContentsContract.Presenter {

    override fun start() {

    }

    override fun searchContents(searchType: String, query: String) {
        if (query.isBlank()) {
            view.blankSearchQuery()

        } else {
            naverRepository.getResultData(
                searchType,
                query,
                success = {
                    view.updateItems(searchResultShowWrapper(searchType, it).items)
                    naverRepository.saveSearchResult(SearchHistory(it.items, searchType, query))
                },
                fail = { view.failToSearch() }
            )
        }
    }

    override fun getLastSearchHistory(searchType: String) {
        naverRepository.getLastSearchResultData(searchType)
            ?.let {
                view.updateItems(
                    searchResultShowWrapper(
                        searchType,
                        SearchResult(it.resultList)
                    ).items
                )
            }
    }

    override fun searchResultShowWrapper(
        searchType: String,
        searchResult: SearchResult
    ): SearchResultShow {
        val searchResultShow = SearchResultShow(searchResult.items.map {
            SearchResultShow.Item(it.title, it.subtitle, it.description, it.link, it.image)
        })

        searchResult.items.map { item: SearchResult.Item ->
            searchResultShow.items.map {
                when (searchType) {
                    Tabs.BLOG.name -> it.subtitle = item.postdate
                    Tabs.NEWS.name -> it.subtitle = item.pubDate
                    Tabs.MOVIE.name -> {
                        it.subtitle = item.pubDate
                        it.description = (item.director + item.actor)
                    }
                    Tabs.BOOK.name -> it.subtitle = item.author
                    else -> it
                }
            }
        }

        return searchResultShow
    }
}