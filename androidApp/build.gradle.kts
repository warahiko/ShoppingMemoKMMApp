plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))

    implementation(Dependencies.androidMaterial)
    implementation(Dependencies.swipeRefresh)
    implementation(Dependencies.viewPager)
    implementation(Dependencies.viewPagerIndicators)

    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.lifecycleRuntimeKtx)
    implementation(Dependencies.AndroidX.lifecycleViewModelCompose)
    implementation(Dependencies.AndroidX.activityCompose)
    implementation(Dependencies.AndroidX.navigationFragmentKtx)
    implementation(Dependencies.AndroidX.navigationUiKtx)
    implementation(Dependencies.AndroidX.navigationCompose)
    implementation(Dependencies.AndroidX.Compose.compiler)
    implementation(Dependencies.AndroidX.Compose.ui)
    implementation(Dependencies.AndroidX.Compose.material)
    implementation(Dependencies.AndroidX.Compose.materialIconsExtended)
    implementation(Dependencies.AndroidX.Compose.uiTooling)

    implementation(Dependencies.KotlinX.datetime)

    // DI
    implementation(Dependencies.koinAndroid)
    implementation(Dependencies.koinAndroidXCompose)

    // test
    testImplementation(Dependencies.jUnit4)
    androidTestImplementation(Dependencies.AndroidX.Test.extJUnit)
    androidTestImplementation(Dependencies.AndroidX.Test.espressoCore)
    androidTestImplementation(Dependencies.AndroidX.Compose.uiTestJUnit4)

    // debug
    implementation(Dependencies.timber)
}

android {
    compileSdkVersion(30)

    // signing configs
    apply(from = "signingConfigs.gradle", to = android)

    defaultConfig {
        applicationId = "io.github.warahiko.shoppingmemokmmapplication.android"
        minSdkVersion(28)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
        }
        getByName("debug") {
            applicationIdSuffix = ".debug"
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.AndroidX.compose
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.freeCompilerArgs += listOf(
        "-Xuse-experimental=com.google.accompanist.pager.ExperimentalPagerApi",
        "-Xuse-experimental=androidx.compose.foundation.ExperimentalFoundationApi",
    )
}
