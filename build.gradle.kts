plugins {
    kotlin("multiplatform") version "1.9.20-Beta2"
    kotlin("plugin.serialization") version "1.9.0"

}

group = "s.knyazev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    js {
        browser {}
        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
            }
        }
        val commonTest by getting { }
        val jsMain by getting {
            dependencies {
                implementation(npm("webextension-polyfill", "0.10.0"))
            }
        }

        val jsTest by getting {
            dependencies {}
        }
    }
}


tasks {
    val extensionFolder = "build/extension"

    val jsBrowserWebpack by getting {}

    val copyBundleFile = register<Copy>("copyBundleFile") {
        dependsOn(jsBrowserWebpack)
        from("build/distributions") {
            include("*.js")
        }
        into(extensionFolder)
    }
    val copyResources = register<Copy>("copyResources") {
        from("src/jsMain/resources")
        into(extensionFolder)
    }
    val copyPolyfill = register<Copy>("copyPolyfill") {
        from("build/js/node_modules/webextension-polyfill/dist") {
            include("browser-polyfill.min.js")
        }
        into(extensionFolder)
    }
    val extension = register<Zip>("extension") {
        dependsOn(copyBundleFile, copyPolyfill, copyResources)
        from(extensionFolder)
        into("build")
    }
    assemble {
        dependsOn(extension)
    }
}
