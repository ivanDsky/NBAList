package ua.zloydi.list

import androidx.paging.PagingSource
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.zloydi.core.network.NetworkModule
import ua.zloydi.list.modules.Module
import ua.zloydi.list.network.PlayersRepository
import ua.zloydi.list.network.models.PlayerItem
import ua.zloydi.list.pagination.PlayersPagingSource

class PagingRealTest {
	private val networkModule = NetworkModule()
	private val service = Module().provideService(networkModule.provideRetrofit(networkModule.provideHttpClient()))
	private val paging = PlayersPagingSource(PlayersRepository(service))
	
	@Test
	fun `Test correct refresh`() {
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