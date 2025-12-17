import org.jetbrains.compose.resources.ResourcesExtension
import org.gradle.api.tasks.Copy
import org.gradle.api.file.DuplicatesStrategy

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.compose)
    alias(libs.plugins.android.kotlin.multiplatform.library)
}

compose.resources {
    publicResClass = true
    generateResClass = always  // 'always' is correct for Kotlin 2.x
}

// Defer task configuration until after project evaluation to prevent lifecycle errors
project.afterEvaluate {
    // This handles the duplicate index.html file
    tasks.named<Copy>("jsProcessResources") {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    // This ensures the processed index.html is copied to the final docs folder
    tasks.named("jsBrowserProductionWebpack") {
        doLast {
            copy {
                from(tasks.named<Copy>("jsProcessResources").get().destinationDir)
                into(rootProject.projectDir.resolve("docs"))
            }
        }
    }
}

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig {
                outputFileName = "ExcipientQuiz-shared.js"
                outputPath = rootProject.projectDir.resolve("docs")
            }
        }
        binaries.executable()
    }

    androidLibrary {
        namespace = "com.example.excipientquiz.shared"
        compileSdk = 36
        minSdk = 24
        androidResources.enable = true
    }

    sourceSets {
        val commonMain by getting {
            // <-- explicitly attach your composeResources folder
            resources.srcDir("src/commonMain/composeResources")

            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.components.resources)
                implementation(compose.ui)
                implementation("io.github.oshai:kotlin-logging:6.0.3")
                implementation("com.russhwolf:multiplatform-settings:1.1.1")
                implementation("com.russhwolf:multiplatform-settings-no-arg:1.1.1")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("org.slf4j:slf4j-simple:2.0.13") // Compatible SLF4J v2 backend
                implementation(libs.androidx.core.ktx)           // 1.10.1 from TOML
                implementation(libs.androidx.activity.compose)   // 1.8.0 from TOML
                implementation(libs.androidx.appcompat)          // if added to TOML, e.g., 1.6.1
                implementation(libs.androidx.compose.ui.tooling)
                implementation(libs.androidx.compose.ui.tooling.preview)
                implementation("com.russhwolf:multiplatform-settings-datastore:1.1.1")
                implementation("com.russhwolf:multiplatform-settings-no-arg:1.1.1")
            }
        }

        val jsMain by getting {
            resources.srcDir("src/jsMain/resources")
            dependencies {
                implementation("com.russhwolf:multiplatform-settings-js:1.1.1")
            }
        }

    }
}
