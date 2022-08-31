package ua.zloydi.list

import org.junit.Test
import ua.zloydi.core.network.NetworkModule
import ua.zloydi.list.modules.Module

class NetworkRealTest {
    private val networkModule = NetworkModule()
    private val service = Module().provideService(
        networkModule.provideRetrofit(networkModule.provideHttpClient())
    )

    @Test
    fun `Test correct correct parameters - page`() {
        testPlayers(3, 20)
    }

    @Test
    fun `Test correct correct parameters - size`() {
        testPlayers(1, 25)
    }

    private fun testPlayers(page: Int, size: Int) {
        service.getPlayers(page, size).test().await().assertComplete().assertNoErrors()
            .assertValue { it.data.isNotEmpty() && it.data.size == size }
            .assertValue {
                it.meta.currentPage == page &&
                    it.meta.nextPage == page + 1 &&
                    it.meta.size == size
            }
    }
}
