@file:JvmName("EventUtils")

package com.github.peco2282.paperkit

import org.bukkit.Bukkit
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

inline fun <reified T : Event> Plugin.on(
  priority: EventPriority = EventPriority.NORMAL,
  ignoreCancelled: Boolean = false,
  async: Boolean = false,
  noinline block: (T) -> Unit
) {
  Bukkit.getPluginManager().registerEvent(
    T::class.java,
    object : Listener {},
    priority,
    { _, event ->
      if (event is T) {
        if (async) {
          Bukkit.getScheduler().runTaskAsynchronously(this, Runnable { block(event) })
        } else {
          block(event)
        }
      }
    },
    this,
    ignoreCancelled
  )
}

fun <T : Event> on(
  plugin: Plugin,
  eventClass: Class<T>,
  priority: EventPriority,
  ignoreCancelled: Boolean,
  listener: Functionals.EventListener<T>
) {
  Bukkit.getPluginManager().registerEvent(
    eventClass,
    object : Listener {},
    priority,
    { l: Listener, event: Event ->
      if (eventClass.isInstance(event)) {
        listener.onEvent(eventClass.cast(event))
      }
    },
    plugin,
    ignoreCancelled
  )
}
