
package com.manhal.movies.extensions

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import com.manhal.movies.network.Api
import kotlinx.coroutines.flow.StateFlow

inline fun <T> LazyListScope.paging(
  items: List<T>,
  currentIndexFlow: StateFlow<Int>,
  threshold: Int = 0,
  pageSize: Int = Api.PAGING_SIZE,

  crossinline fetch: () -> Unit,
  crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit,
) {
  val currentIndex = currentIndexFlow.value

  itemsIndexed(items) { index, item ->

    itemContent(item)

    if ((index + threshold + 1) >= pageSize * (currentIndex - 1)) {
      fetch()
    }
  }
}
