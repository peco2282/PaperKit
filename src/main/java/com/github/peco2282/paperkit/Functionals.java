package com.github.peco2282.paperkit;

import com.github.peco2282.paperkit.internals.ComponentCreator;
import com.github.peco2282.paperkit.internals.ComponentJoiner;
import org.bukkit.event.Event;

import java.util.function.Consumer;

/**
 * Functional interfaces for component handling and event listening. Contains interfaces for
 * component joining, component creation, and event listening.
 *
 * @author peco2282
 */
public interface Functionals {

  /**
   * Functional interface for joining components together. Consumes a ComponentJoiner to build
   * compound text components.
   */
  @FunctionalInterface
  interface IComponentJoiner extends Consumer<ComponentJoiner> {}

  /**
   * Functional interface for creating individual components. Consumes a ComponentCreator to
   * configure and build text components.
   */
  @FunctionalInterface
  interface IComponentCreator extends Consumer<ComponentCreator> {}

  /**
   * Functional interface for handling Bukkit events. Provides a type-safe way to handle specific
   * event types.
   *
   * @param <E> The type of event to handle
   */
  @FunctionalInterface
  interface EventListener<E extends Event> {
    /**
     * Called when the event occurs.
     *
     * @param event The event that occurred
     */
    void onEvent(E event);
  }
}
