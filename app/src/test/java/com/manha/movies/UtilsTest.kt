/* Developed by Manhal */

package com.manha.movies

import com.manhal.movies.formatToThreeDotDigit
import com.manhal.movies.fromMinToHourMinStyle
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UtilsTest {
  @Before
  fun setUp() {
  }
  @Test
  fun `test format To Three Dot Digit for number with more than four digits`() {
    val result: String = formatToThreeDotDigit(20000)
    Assert.assertEquals(result, "20,000")
  }

  @Test
  fun `test format To Three Dot Digit for number with four digits`() {
    val result: String = formatToThreeDotDigit(2000)
    Assert.assertEquals(result, "2,000")
  }

  @Test
  fun `test format To Three Dot Digit for number with less than four digits`() {
    val result: String = formatToThreeDotDigit(200)
    Assert.assertEquals(result, "200")
  }

  @Test
  fun `test from MinTo HourMinutes Style for minutes bigger than 60`() {
    val result: String = fromMinToHourMinStyle(170)
    Assert.assertEquals(result, "2h 50m")
  }
  @Test
  fun `test from MinTo HourMinutes Style for minutes equal 60`() {
    val result: String = fromMinToHourMinStyle(60)
    Assert.assertEquals(result, "1h")
  }

  @Test
  fun `test from MinTo HourMinutes Style for minutes less than 60`() {
    val result: String = fromMinToHourMinStyle(50)
    Assert.assertEquals(result, "50m")
  }
}
