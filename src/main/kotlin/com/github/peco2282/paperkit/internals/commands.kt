/**
 * Provides utilities for creating and managing Minecraft commands using a DSL approach.
 *
 * @author peco2282
 */
@file:Suppress("UnstableApiUsage")
@file:ApiStatus.Experimental

package com.github.peco2282.paperkit.internals

import com.github.peco2282.paperkit.internals.Value
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.suggestion.SuggestionProvider
import io.papermc.paper.command.brigadier.CommandSourceStack
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents
import org.bukkit.plugin.Plugin
import org.jetbrains.annotations.ApiStatus

@DslMarker
annotation class CommandDsl

/**
 * Creates and manages Minecraft commands using a DSL-style builder pattern.
 *
 * @property parent The parent command creator, if this is a subcommand
 */
@CommandDsl
class CommandCreator(
  private val parent: CommandCreator? = null
) {
  private val self: Value<ArgumentBuilder<CommandSourceStack, *>> =
    parent?.self ?: Value(LiteralArgumentBuilder.literal(""))

  /**
   * Adds a literal argument to the command.
   *
   * @param literal The literal string to match
   * @param creator The configuration block for this command
   * @return This command creator instance
   */
  fun literal(
    literal: String,
    creator: CommandCreator.() -> Unit
  ) = apply {
    val command = LiteralArgumentBuilder.literal<CommandSourceStack>(literal)
    val c = CommandCreator(this)
    c.creator()
    self.consume { it.then(command) }
  }

  /**
   * Adds a required argument to the command.
   *
   * @param argument The name of the argument
   * @param type The argument type
   * @param creator The configuration block for this command
   * @return This command creator instance
   */
  fun <T> argument(
    argument: String,
    type: ArgumentType<T>,
    creator: CommandCreator.() -> Unit
  ) = apply {
    val arg = RequiredArgumentBuilder.argument<CommandSourceStack, T>(argument, type)
    val c = CommandCreator(this)
    c.creator()
    self.consume { it.then(arg) }
  }

  /**
   * Sets a requirement predicate for this command.
   *
   * @param predicate The predicate that must be satisfied to execute this command
   * @return This command creator instance
   */
  fun requires(predicate: (CommandSourceStack) -> Boolean) =
    apply {
      self.consume { it.requires(predicate) }
    }

  /**
   * Sets the execution handler for this command.
   *
   * @param block The code to execute when this command is run
   * @return This command creator instance
   */
  fun executes(block: (CommandContext<CommandSourceStack>) -> Int) =
    apply {
      self.consume { it.executes(block) }
    }

  /**
   * Adds static suggestions for this command argument.
   *
   * @param suggestions List of suggestion strings
   * @return This command creator instance
   */
  fun suggestion(suggestions: List<String>) =
    suggestion { _, builder ->
      suggestions.forEach { builder.suggest(it) }
      builder.buildFuture()
    }

  /**
   * Adds dynamic suggestions for this command argument.
   *
   * @param provider The suggestion provider
   * @return This command creator instance
   */
  fun suggestion(provider: SuggestionProvider<CommandSourceStack>) =
    apply {
      if (self.value is RequiredArgumentBuilder<CommandSourceStack, *>) {
        self.consume {
          it as RequiredArgumentBuilder<CommandSourceStack, *>
          it.suggests(provider)
        }
      }
    }

  /**
   * Registers this command with the given plugin.
   *
   * @param plugin The plugin to register the command with
   */
  fun register(plugin: Plugin) {
    if (parent == null && self.value is LiteralArgumentBuilder<CommandSourceStack>) {
      plugin.lifecycleManager.registerEventHandler(LifecycleEvents.COMMANDS) {
        it.registrar().register((self.value as LiteralArgumentBuilder<CommandSourceStack>).build())
      }
    } else {
      plugin.logger.warning("コマンドの親でないか、LiteralArgumentBuilderではありません。")
    }
  }
}
