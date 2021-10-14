import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import java.io.FileInputStream
import java.util.Properties

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("com.codingfeline.buildkonfig")
    id("kotlin-kapt")
    id("kotlinx-serialization")
}

version = "1.0"

val notionProperties = Properties()
notionProperties.load(FileInputStream(rootProject.file("notion.properties")))

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }

    iosTarget("ios") {}

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        frameworkName = "shared"
        podfile = project.file("../iosApp/Podfile")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.insert-koin:koin-core:3.1.2")

                implementation("io.ktor:ktor-client-core:1.6.4")
                implementation("io.ktor:ktor-client-logging:1.6.4")
                implementation("io.ktor:ktor-client-serialization:1.6.4")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2-native-mt") {
                    version {
                        strictly("1.5.2-native-mt")
                    }
                }
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.0")

                implementation("com.benasher44:uuid:0.3.1")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:1.6.4")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:1.6.4")
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(28)
        targetSdkVersion(30)
    }
}

buildkonfig {
    packageName = "io.github.warahiko.shoppingmemokmmapplication"

    defaultConfigs {
        buildConfigField(STRING, "NOTION_BASE_URL", "https://api.notion.com/v1/")
        buildConfigField(STRING, "NOTION_TOKEN", notionProperties.getProperty("notionToken"))
        buildConfigField(STRING, "NOTION_VERSION", notionProperties.getProperty("notionVersion"))
        buildConfigField(STRING, "DATABASE_ID", notionProperties.getProperty("databaseIdRelease"))
        buildConfigField(STRING, "TAG_DATABASE_ID", notionProperties.getProperty("tagDatabaseIdRelease"))
    }
    defaultConfigs("dev") {
        buildConfigField(STRING, "DATABASE_ID", notionProperties.getProperty("databaseIdDebug"))
        buildConfigField(STRING, "TAG_DATABASE_ID", notionProperties.getProperty("tagDatabaseIdDebug"))
    }
}
