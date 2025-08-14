# PaperKit

A Kotlin library for Paper plugins that provides utilities for sending custom packets to players.

## Installation

Add the following to your `build.gradle.kts`:

## Usage

### Component

```kotlin
val hello =
  component {
    append("Hello")
    append("World") {
      color(0x00ff00)
      style { "Test" }
    }
    append("!")
  }
```
### NBT

```kotlin
nbt(plugin, ItemStack(Material.STONE)) {
      string("key1", "value1")
      int("key2", 1)
      intArray("key3", intArrayOf(1, 2, 3))
      long("key4", 1L)
      longArray("key5", longArrayOf(1L, 2L, 3L))
      double("key6", 1.0)
      float("key7", 1f)
      boolean("key8", true)
      byte("key9", 1)
      short("key10", 1)
}
```

### Event

```kotlin
plugin.on<PlayerJoinEvent> {
  it.player.sendMessage(
    hello 
// = component {
//    append("Hello")
//    append("World") {
//      color(0x00ff00)
//      style { "Test" }
//    }
//    append("!")
//}
  )
}

```

### Tasks

```kotlin
runTask(plugin, 1 /* second(s) */) {
  plugin.logger.info("One second later")
}
```
