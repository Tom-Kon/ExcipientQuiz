import org.jetbrains.compose.resources.ResourcesExtension

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

kotlin {
    js(IR) {
        browser()
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
                implementation("com.russhwolf:multiplatform-settings:1.1.1")
                implementation("com.russhwolf:multiplatform-settings-no-arg:1.1.1")
            }
        }

        val androidMain by getting {
            dependencies {
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
            dependencies {
                implementation("com.russhwolf:multiplatform-settings-js:1.1.1")
            }
        }

    }
}
