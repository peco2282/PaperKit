package com.github.peco2282.paperkit

import kotlin.test.Test

class UtilTest {
  @Test
  fun testComponent() {
    val component =
      component {
        append("Hello")
        append("World") {
          color(0x00ff00)
          style { "Test" }
        }
        append("!")
      }

    println(component.string())
  }
}
