/* Developed by Manhal */

package com.manhal.movies.di

import android.content.Context
import coil.ImageLoader
import com.manhal.movies.network.Api
import com.manhal.movies.network.RequestInterceptor
import com.manhal.movies.network.service.GenreService
import com.manhal.movies.network.service.MovieDetailService
import com.manhal.movies.network.service.MovieService
import com.manhal.movies.network.service.TheDiscoverService
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(RequestInterceptor())
      .build()
  }

  @Provides
  @Singleton
  fun provideImageLoader(
    @ApplicationContext context: Context,
    okHttpClient: OkHttpClient
  ): ImageLoader {
    return ImageLoader.Builder(context)
      .okHttpClient { okHttpClient }
      .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(okhHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okhHttpClient)
      .baseUrl(Api.BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
      .build()
  }

  @Provides
  @Singleton
  fun provideTheDiscoverService(retrofit: Retrofit): TheDiscoverService {
    return retrofit.create(TheDiscoverService::class.java)
  }

  @Provides
  @Singleton
  fun provideMovieService(retrofit: Retrofit): MovieService {
    return retrofit.create(MovieService::class.java)
  }

  @Provides
  @Singleton
  fun provideGenreService(retrofit: Retrofit): GenreService {
    return retrofit.create(GenreService::class.java)
  }

  @Provides
  @Singleton
  fun provideMovieDetailService(retrofit: Retrofit): MovieDetailService {
    return retrofit.create(MovieDetailService::class.java)
  }
}
