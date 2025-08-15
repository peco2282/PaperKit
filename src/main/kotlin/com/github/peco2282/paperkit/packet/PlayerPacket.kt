/**
 * Contains packet interfaces for player-related network communications.
 *
 * @author peco2282
 */
package com.github.peco2282.paperkit.packet

import com.github.peco2282.paperkit.internals.Debugged
import org.bukkit.NamespacedKey
import org.bukkit.advancement.Advancement
import org.bukkit.advancement.AdvancementProgress
import org.bukkit.entity.Entity
import org.bukkit.util.Vector

/**
 * Base interface for all player-related packets.
 * Extends PacketScope to provide player sending capabilities.
 */
interface PlayerPacket : PacketScope

/**
 * Packet interface for block destruction events.
 *
 * @property id The unique identifier of the destruction event
 * @property vector The position vector of the block being destroyed
 * @property progress The destruction progress from 0-9
 */
interface IBlockDestructionPacket : PlayerPacket {
  var id: Int
  var vector: Vector
  var progress: Int
}

/**
 * Represents relative coordinate axes for entity movement.
 */
enum class Relative {
  X,
  Y,
  Z
}

/**
 * Packet interface for entity teleportation events.
 *
 * @property id The entity's unique identifier
 * @property relatives Set of relative coordinates being used
 * @property position The absolute position vector
 * @property delta The relative movement vector
 * @property onGround Whether the entity is on ground
 * @property rotX The X-axis rotation
 * @property rotY The Y-axis rotation
 */
interface ITeleportEntityPacket : PlayerPacket {
  var id: Int
  var relatives: Set<Relative>

  var position: Vector
  var delta: Vector
  var onGround: Boolean

  var rotX: Float
  var rotY: Float
}

/**
 * Represents different types of entity animations.
 *
 * @property value The internal numeric value of the animation
 */
enum class EntityAnimation(
  internal val value: Int
) {
  /** Animation for swinging the main hand */
  SWING_MAIN_HAND(0),

  /** Animation for waking up from sleep */
  WAKE_UP(2),

  /** Animation for swinging the off hand */
  SWING_OFFHAND(3),

  /** Animation for a critical hit */
  CRITICAL_HIT(4),

  /** Animation for a magical critical hit */
  MAGIC_CRITICAL_HIT(5)
}

/**
 * Packet interface for entity animation events.
 *
 * @property entity The entity performing the animation, null if not set
 * @property animation The type of animation to perform
 */
@Debugged
interface IAnimatePacket : PlayerPacket {
  var entity: Entity?

  var animation: EntityAnimation
}

/**
 * Packet interface for updating player advancements.
 *
 * @property reset Whether to clear all existing advancement data
 * @property add Collection of advancements to add
 * @property remove Collection of advancement keys to remove
 * @property progress Map of advancement progress updates by advancement key
 */
interface IUpdateAdvancementsPacket : PlayerPacket {
  var reset: Boolean
  var add: Collection<Advancement>
  var remove: Collection<NamespacedKey>
  var progress: Map<NamespacedKey, AdvancementProgress>
}
