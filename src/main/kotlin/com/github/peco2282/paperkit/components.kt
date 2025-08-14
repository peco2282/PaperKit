/**
 * Utility functions for working with Adventure Components.
 * Provides extensions and factory methods for component creation and manipulation.
 *
 * @author peco2282
 */
@file:JvmName("ComponentUtils")

package com.github.peco2282.paperkit

import com.github.peco2282.paperkit.Functionals.IComponentJoiner
import com.github.peco2282.paperkit.internals.ComponentJoiner
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer

/**
 * Converts this string to a Component using legacy section format (§).
 *
 * @return The converted Component
 */
fun String.component(): Component = LegacyComponentSerializer.legacySection().deserialize(this)

/**
 * Converts this Component to a string using legacy section format (§).
 *
 * @return The serialized string
 */
fun Component.string() = LegacyComponentSerializer.legacySection().serialize(this)

/**
 * Converts this Component to use ampersand (&) format.
 *
 * @return The converted Component using ampersand format
 */
fun Component.ampComponent() = LegacyComponentSerializer.legacyAmpersand().deserialize(this.string())

/**
 * Converts this Component to a string using ampersand (&) format.
 *
 * @return The serialized string using ampersand format
 */
fun Component.ampString() = LegacyComponentSerializer.legacyAmpersand().serialize(this)

/**
 * Creates a Component using the provided joiner consumer.
 *
 * @param consumer The component joiner consumer
 * @return The built Component
 */
fun component(consumer: IComponentJoiner): Component {
  val joiner = ComponentJoiner()
  consumer.accept(joiner)
  return joiner.build()
}

/**
 * Creates a Component using the provided creator function.
 *
 * @param creator The component creator function
 * @return The built Component
 */
fun component(creator: ComponentJoiner.() -> Unit): Component {
  val builder = ComponentJoiner()
  builder.creator()
  return builder.build()
}

/**
 * Applies modifications to this Component using the provided creator function.
 *
 * @param creator The component modifier function
 * @return The modified Component
 */
fun Component.add(creator: Component.() -> Unit): Component {
  this.creator()
  return this
}
