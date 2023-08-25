/* Developed by Manhal */

package com.manhal.movies.network
import okhttp3.Interceptor
import okhttp3.Response

internal class RequestInterceptor : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()
    val originalUrl = originalRequest.url
    val url = originalUrl.newBuilder()
      .addQueryParameter("api_key", "df0afeb903f509ecce3212b0af798cc3")
      .build()

    val requestBuilder = originalRequest.newBuilder().url(url)
    val request = requestBuilder.build()
    return chain.proceed(request)
  }
}
