import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import java.io.FileInputStream
import java.util.Properties

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("com.codingfeline.buildkonfig")
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
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting
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
        buildConfigField(
            STRING,
            "NOTION_VERSION",
            notionProperties.getProperty("notionVersion")
        )
        buildConfigField(
            STRING,
            "DATABASE_ID",
            notionProperties.getProperty("databaseIdRelease")
        )
        buildConfigField(
            STRING,
            "TAG_DATABASE_ID",
            notionProperties.getProperty("tagDatabaseIdRelease")
        )
    }
    defaultConfigs("dev") {
        buildConfigField(
            STRING,
            "DATABASE_ID",
            notionProperties.getProperty("databaseIdDebug")
        )
        buildConfigField(
            STRING,
            "TAG_DATABASE_ID",
            notionProperties.getProperty("tagDatabaseIdDebug")
        )
    }
}
