/* Developed by Manhal */

package com.manhal.movies.models.network

import androidx.compose.runtime.Immutable
import com.manhal.movies.models.Keyword
import com.manhal.movies.models.NetworkResponseModel

@Immutable
data class KeywordListResponse(
  val id: Int,
  val keywords: List<Keyword>
) : NetworkResponseModel
