package com.github.peco2282.paperkit.internals

import kotlin.reflect.KProperty

internal data class Value<T>(
  var value: T
) {
  operator fun getValue(
    thisRef: Any?,
    prop: KProperty<*>
  ) = value

  operator fun setValue(
    thisRef: Any?,
    prop: KProperty<*>,
    value: T
  ) {
    this.value = value
  }

  fun consume(consumer: (T) -> T) {
    value = value.let(consumer)
  }
}
