

import com.ikran.movieinfo.common.BaseTest
import com.ikran.movieinfo.data.TitleDetail
import com.ikran.movieinfo.network.MovieApi
import com.ikran.movieinfo.viewmodel.DetailViewModel
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.lang.IllegalStateException

class DetailViewModelTest : BaseTest() {

    private companion object {
        const val TITLE = "Star"
    }

    @MockK
    lateinit var mvApi: MovieApi

    @InjectMockKs
    lateinit var detailViewModel: DetailViewModel

    @Test
    fun getTitleDetail_whenSuccess_emitsDetail() {
        every { mvApi.getTitle(any()) } returns Single.just(mockk())

        assertThat(detailViewModel.getTitleDetail(TITLE).value).isInstanceOf(TitleDetail::class.java)
    }

    @Test
    fun getTitleDetail_whenFailed_observesEmpty() {
        every { mvApi.getTitle(any()) } returns Single.error(IllegalStateException())

        assertThat(detailViewModel.getTitleDetail(TITLE).value).isNull()
    }
}