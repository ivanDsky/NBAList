package ua.zloydi.core.network

import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@dagger.Module
class NetworkModule {
	@Provides
	fun provideHttpClient() = OkHttpClient.Builder()
		.readTimeout(5,TimeUnit.SECONDS)
		.connectTimeout(2,TimeUnit.SECONDS)
//		.addInterceptor(HeadersInterceptor())
		.build()
	
	@Provides
	fun provideRetrofit(client: OkHttpClient) = Retrofit.Builder()
		.addConverterFactory(GsonConverterFactory.create())
		.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
		.baseUrl("https://www.balldontlie.io/")
		.client(client)
		.build()
}