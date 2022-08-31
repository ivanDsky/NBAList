package ua.zloydi.list

import androidx.paging.PagingSource
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.zloydi.list.modules.Module
import ua.zloydi.list.network.PlayersRepository
import ua.zloydi.list.network.models.PlayerItem
import ua.zloydi.list.pagination.PlayersPagingSource

class PagingMockTest {
	private val webServer = MockWebServer()
	private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
		.addCallAdapterFactory(RxJava3CallAdapterFactory.create()).baseUrl(webServer.url("/"))
		.build()
	
	private val service = Module().provideService(retrofit)
	private val paging = PlayersPagingSource(PlayersRepository(service))
	
	@After
	fun closeServer() {
		webServer.shutdown()
	}
	
	@Test
	fun `Test correct refresh`() {
		webServer.enqueue(
			MockResponse()
				.setResponseCode(200)
				.setBody(javaClass.getResource("/PlayersPage1.json").readText())
		)
		paging.loadSingle(PagingSource.LoadParams.Refresh(1,20,false))
			.test()
			.await()
			.assertComplete()
			.assertNoErrors()
			.assertValue {
				(it as? PagingSource.LoadResult.Page<Int, PlayerItem> != null) &&
					it.data.isNotEmpty() &&
					it.data.size == 20 &&
					it.prevKey == null &&
					it.nextKey == 2
			}
		
	}
	
	@Test
	fun `Test correct append`() {
		webServer.enqueue(
			MockResponse()
				.setResponseCode(200)
				.setBody(javaClass.getResource("/PlayersPage3.json").readText())
		)
		paging.loadSingle(PagingSource.LoadParams.Append(3,20,false))
			.test()
			.await()
			.assertComplete()
			.assertNoErrors()
			.assertValue {
				(it as? PagingSource.LoadResult.Page<Int, PlayerItem> != null) &&
					it.data.isNotEmpty() &&
					it.data.size == 20 &&
					it.prevKey == 2 &&
					it.nextKey == 4
			}
		
	}
}