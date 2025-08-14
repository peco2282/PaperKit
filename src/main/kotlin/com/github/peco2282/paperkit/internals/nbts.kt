package com.github.peco2282.paperkit.internals

import org.bukkit.NamespacedKey
import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.Plugin

class NBTCreator(
  private val plugin: Plugin,
  private val container: PersistentDataContainer
) {
  internal fun <P, C : Any> put(
    key: String,
    type: PersistentDataType<P, C>,
    value: C
  ) = apply { container.set(NamespacedKey(plugin, key), type, value) }

  fun string(
    key: String,
    value: String
  ) = apply { put(key, PersistentDataType.STRING, value) }

  fun int(
    key: String,
    value: Int
  ) = apply { put(key, PersistentDataType.INTEGER, value) }

  fun intArray(
    key: String,
    value: IntArray
  ) = apply { put(key, PersistentDataType.INTEGER_ARRAY, value) }

  fun long(
    key: String,
    value: Long
  ) = apply { put(key, PersistentDataType.LONG, value) }

  fun longArray(
    key: String,
    value: LongArray
  ) = apply { put(key, PersistentDataType.LONG_ARRAY, value) }

  fun double(
    key: String,
    value: Double
  ) = apply { put(key, PersistentDataType.DOUBLE, value) }

  fun float(
    key: String,
    value: Float
  ) = apply { put(key, PersistentDataType.FLOAT, value) }

  fun boolean(
    key: String,
    value: Boolean
  ) = apply { put(key, PersistentDataType.BOOLEAN, value) }

  fun byte(
    key: String,
    value: Byte
  ) = apply { put(key, PersistentDataType.BYTE, value) }

  fun short(
    key: String,
    value: Short
  ) = apply { put(key, PersistentDataType.SHORT, value) }

  fun byteArray(
    key: String,
    value: ByteArray
  ) = apply { put(key, PersistentDataType.BYTE_ARRAY, value) }
}
