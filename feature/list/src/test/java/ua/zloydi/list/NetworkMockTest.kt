package ua.zloydi.list

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.zloydi.list.modules.Module

class NetworkMockTest {
    private val webServer = MockWebServer()
    private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).baseUrl(webServer.url("/"))
        .build()

    private val service = Module().provideService(retrofit)

    @After
    fun closeServer() {
        webServer.shutdown()
    }

    @Test
    fun `Test correct response`() {
        webServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(javaClass.getResource("/PlayersPage1.json").readText())
        )

        service.getPlayers(1, 20)
            .test()
            .await()
            .assertComplete()
            .assertNoErrors()
            .assertValue { it.data.isNotEmpty() && it.data.size == 20 }
            .assertValue { it.meta.currentPage == 1 && it.meta.nextPage == 2 && it.meta.size == 20 }
    }
}
