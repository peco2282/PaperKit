package com.github.peco2282.paperkit;

import org.bukkit.Color;
import org.bukkit.util.Vector;
import org.junit.jupiter.api.Test;

class JavaUtilTest {
  @Test
  void testPacket() {
    PacketUtils.destroy(
        it -> {
          it.setId(1);
          it.setVector(new Vector(1, 1, 1));
        });
  }

  @Test
  void testComponent() {
    var component =
        ComponentUtils.component(
            j -> {
              j.append(
                  "Hello World!",
                  c -> {
                    c.color(Color.AQUA);
                  });
            });
    System.out.println(component);
  }
}
