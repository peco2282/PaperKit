plugins {
  kotlin("jvm") version "2.1.21"
  id("io.papermc.paperweight.userdev") version "2.0.0-beta.18"
  idea
  `maven-publish`
  id("com.diffplug.spotless") version "7.2.0"
}

group = "com.github.peco2282"
val propVer = project.property("version")
version =
  if (propVer != null && propVer.toString() != "unspecified") propVer.toString()
  else "v1.0.0"

repositories {
  mavenCentral()
}

dependencies {
  testImplementation(kotlin("test"))
  paperweight.paperDevBundle("1.21.4-R0.1-SNAPSHOT")
}
java {
  toolchain.languageVersion.set(JavaLanguageVersion.of(23))
}
tasks.build {
  dependsOn("spotlessApply")
}
tasks.test {
  useJUnitPlatform()
}
kotlin {
  jvmToolchain(21)
}
idea {
  module {
    isDownloadJavadoc = true
    isDownloadSources = true
  }
}

val sourcesJar by tasks.registering(Jar::class, fun Jar.() {
  group = JavaBasePlugin.BUILD_TASK_NAME
  archiveClassifier.set("sources")
  from(sourceSets.main.get().allSource)
})

val javadocJar by tasks.registering(Jar::class, fun Jar.() {
  group = JavaBasePlugin.BUILD_TASK_NAME
  archiveClassifier.set("javadoc")
  val javadocTask = tasks.named("javadoc", Javadoc::class).get()
  dependsOn(javadocTask)
  from(javadocTask.destinationDir)
})

artifacts {
  archives(sourcesJar)
  archives(javadocJar)
}

val owningRepo = "peco2282/PaperKit"

publishing {
  publications {
    create<MavenPublication>("maven") {
      from(components["java"])
      artifact(sourcesJar)
      artifact(javadocJar)
      pom {
        name.set(project.name.lowercase())
        artifactId = project.name.lowercase()
        description.set("PaperKit for Plugin-Dev")
        url.set("https://github.com/${owningRepo}")

        licenses {
          license {
            name.set("The Apache Software License, Version 2.0")
            url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
          }
        }
        developers {
        }
        scm {
          connection.set("scm:git:git://github.com/${owningRepo}.git")
          developerConnection.set("scm:git:ssh://github.com/${owningRepo}.git")
          url.set("https://github.com/${owningRepo}")
        }
      }
    }
  }
  repositories {
    maven {
      name = "GitHubPackages"
      url = uri("https://maven.pkg.github.com/${owningRepo}")

      credentials {
        username = System.getenv("GITHUB_ACTOR") ?: project.findProperty("gpr.user") as String?
        password = System.getenv("GITHUB_TOKEN") ?: project.findProperty("gpr.token") as String?
      }
    }
  }
}


spotless {
  kotlin {
    ktlint("1.7.0").setEditorConfigPath(".editorconfig")
    toggleOffOn()
  }
  java {
    googleJavaFormat()
    importOrder(
      "*",
      "",
      "java",
      "static *"
    )
    removeUnusedImports()
    trimTrailingWhitespace()
    endWithNewline()
    leadingTabsToSpaces(2)
  }
}
