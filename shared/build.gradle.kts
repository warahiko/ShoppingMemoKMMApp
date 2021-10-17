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
                implementation(Dependencies.koinCore)

                implementation(Dependencies.ktorClientCore)
                implementation(Dependencies.ktorClientLogging)
                implementation(Dependencies.ktorClientSerialization)

                implementation(Dependencies.KotlinX.coroutinesCore) {
                    version {
                        strictly(Versions.KotlinX.coroutines)
                    }
                }
                implementation(Dependencies.KotlinX.serializationJson)
                implementation(Dependencies.KotlinX.datetime)

                implementation(Dependencies.uuid)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(Dependencies.Kotlin.testCommon)
                implementation(Dependencies.Kotlin.testAnnotationsCommon)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Dependencies.AndroidX.annotation)
                implementation(Dependencies.ktorClientAndroid)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(Dependencies.Kotlin.testJUnit)
                implementation(Dependencies.jUnit4)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(Dependencies.ktorClientIos)
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
    }
    defaultConfigs("dev") {
        buildConfigField(STRING, "DATABASE_ID", notionProperties.getProperty("databaseIdDebug"))
        buildConfigField(STRING, "TAG_DATABASE_ID", notionProperties.getProperty("tagDatabaseIdDebug"))
    }
    defaultConfigs("release") {
        buildConfigField(STRING, "DATABASE_ID", notionProperties.getProperty("databaseIdRelease"))
        buildConfigField(STRING, "TAG_DATABASE_ID", notionProperties.getProperty("tagDatabaseIdRelease"))
    }
}
