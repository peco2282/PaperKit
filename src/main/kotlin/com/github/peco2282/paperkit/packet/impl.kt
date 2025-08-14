package com.github.peco2282.paperkit.packet

import com.github.peco2282.paperkit.internals.blockPos
import com.github.peco2282.paperkit.internals.vec3
import net.minecraft.network.protocol.game.ClientboundBlockDestructionPacket
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket
import net.minecraft.world.entity.PositionMoveRotation
import org.bukkit.util.Vector
import net.minecraft.world.entity.Relative as NMSRelative

internal object ClientboundBlockDestructionPacketDefault : IBlockDestructionPacket, PacketWrapper {
  override var id: Int = 0
  override var vector: Vector = Vector()
  override var progress: Int = 0

  override fun convert() = ClientboundBlockDestructionPacket(id, vector.blockPos(), progress)

  override fun validate(): Boolean = id != 0 && progress in 0..10 && vector == Vector()
}

internal open class ClientboundTeleportEntityPacketDefault(
  override var relatives: Set<Relative>
) : ITeleportEntityPacket,
  PacketWrapper {
  override var id: Int = 0
  override var position: Vector = Vector()
  override var delta: Vector = Vector()
  override var onGround: Boolean = false
  override var rotX: Float = -1f
  override var rotY: Float = -1f

  override fun convert(): ClientboundTeleportEntityPacket {
    val relatives =
      relatives
        .map {
          when (it) {
            Relative.X -> NMSRelative.X
            Relative.Y -> NMSRelative.Y
            Relative.Z -> NMSRelative.Z
          }
        }.toSet()
    val rot =
      PositionMoveRotation(
        position.vec3(),
        delta.vec3(),
        rotX,
        rotY
      )
    val packet =
      ClientboundTeleportEntityPacket.teleport(
        id,
        rot,
        relatives,
        onGround
      )
    return packet
  }

  override fun validate(): Boolean = id != 0 && position != Vector() && delta != Vector() && rotX != -1f && rotY != -1f
}

internal object ClientboundAbsoluteTeleportEntityPacketDefault : ClientboundTeleportEntityPacketDefault(setOf())

internal object ClientboundRelativeTeleportEntityPacketDefault :
  ClientboundTeleportEntityPacketDefault(setOf(Relative.X, Relative.Y, Relative.Z))
