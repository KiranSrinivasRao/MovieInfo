package com.ikran.movieinfo.network

import com.ikran.movieinfo.common.BaseTest
import com.ikran.movieinfo.data.SearchItem
import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.junit.Test

class SearchItemTest : BaseTest() {

    @Test
    fun equals_whenDifferentObject_returnsFalse() {
        Assertions.assertThat(mockk<SearchItem>() == mockk()).isFalse()
    }

    @Test
    fun equals_whenSameObject_returnsTrue() {
        val searchItem = mockk<SearchItem>()
        Assertions.assertThat(searchItem == searchItem).isTrue()
    }

    @Test
    fun equals_whenTitleIdSame_returnsTrue() {
        val titleId = "t123"
        val searchItem = SearchItem(null, null, titleId, null, null)
        val searchItem2 = SearchItem(null, null, titleId, null, null)

        Assertions.assertThat(searchItem == searchItem2).isTrue()
    }
}