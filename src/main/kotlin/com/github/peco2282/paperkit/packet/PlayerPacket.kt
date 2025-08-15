/**
 * Contains packet interfaces for player-related network communications.
 *
 * @author peco2282
 */
package com.github.peco2282.paperkit.packet

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

enum class EntityAnimation(internal val value: Int) {
  SWING_MAIN_HAND(0),
  UAKE_UP(2),
  SWING_OFFHAND(3),
  CRITICAL_HIT(4),
  MAGIC_CRITICAL_HIT(5);
}

interface IAnimatePacket : PlayerPacket {
  var entity: Entity?

  var animation: EntityAnimation
}
