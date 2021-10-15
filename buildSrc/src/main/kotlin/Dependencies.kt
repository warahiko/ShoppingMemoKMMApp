object Dependencies {
    const val androidBuildGradle =
        "com.android.tools.build:gradle:${Versions.buildGradle}"
    const val ktLint =
        "com.pinterest:ktlint:${Versions.ktLint}"

    object Kotlin {
        const val gradlePlugin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val serialization =
            "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
        const val testCommon =
            "org.jetbrains.kotlin:kotlin-test-common:${Versions.kotlin}"
        const val testAnnotationsCommon =
            "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.kotlin}"
        const val testJUnit =
            "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    }

    object KotlinX {
        const val serializationJson =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KotlinX.serializationJson}"
        const val datetime =
            "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.KotlinX.datetime}"
        const val coroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KotlinX.coroutines}"
    }

    object AndroidX {
        const val coreKtx =
            "androidx.core:core-ktx:${Versions.AndroidX.coreKtx}"
        const val appCompat =
            "com.google.android.material:material:${Versions.AndroidX.appCompat}"
        const val lifecycleRuntimeKtx =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.lifecycle}"
        const val lifecycleViewModelCompose =
            "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.AndroidX.lifecycleViewModelCompose}"
        const val activityCompose =
            "androidx.activity:activity-compose:${Versions.AndroidX.activityCompose}"
        const val navigationFragmentKtx =
            "androidx.navigation:navigation-fragment-ktx:${Versions.AndroidX.navigation}"
        const val navigationUiKtx =
            "androidx.navigation:navigation-ui-ktx:${Versions.AndroidX.navigation}"
        const val navigationCompose =
            "androidx.navigation:navigation-compose:${Versions.AndroidX.navigationCompose}"
        const val hiltNavigationCompose =
            "androidx.hilt:hilt-navigation-compose:${Versions.AndroidX.hiltNavigationCompose}"

        object Compose {
            const val version = Versions.AndroidX.compose
            const val compiler =
                "androidx.compose.compiler:compiler:${Versions.AndroidX.compose}"
            const val ui =
                "androidx.compose.ui:ui:${Versions.AndroidX.compose}"
            const val uiTooling =
                "androidx.compose.ui:ui-tooling:${Versions.AndroidX.compose}"
            const val material =
                "androidx.compose.material:material:${Versions.AndroidX.compose}"
            const val materialIconsExtended =
                "androidx.compose.material:material-icons-extended:${Versions.AndroidX.compose}"
            const val uiTestJUnit4 =
                "androidx.compose.ui:ui-test-junit4:${Versions.AndroidX.compose}"
        }

        object Test {
            const val extJUnit =
                "androidx.test.ext:junit:${Versions.AndroidX.Test.extJUnit}"
            const val espressoCore =
                "androidx.test.espresso:espresso-core:${Versions.AndroidX.Test.espressoCore}"
        }
    }

    const val androidMaterial =
        "com.google.android.material:material:${Versions.androidMaterial}"
    const val swipeRefresh =
        "com.google.accompanist:accompanist-swiperefresh:${Versions.swipeRefresh}"
    const val viewPager =
        "com.google.accompanist:accompanist-pager:${Versions.viewPager}"
    const val viewPagerIndicators =
        "com.google.accompanist:accompanist-pager-indicators:${Versions.viewPager}"

    // DI
    const val koinCore =
        "io.insert-koin:koin-core:${Versions.koin}"
    const val koinAndroid =
        "io.insert-koin:koin-android:${Versions.koin}"

    // build config
    const val buildKonfigGradlePlugin =
        "com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:${Versions.buildKonfigGradlePlugin}"

    // network
    const val ktorClientCore =
        "io.ktor:ktor-client-core:${Versions.ktor}"
    const val ktorClientLogging =
        "io.ktor:ktor-client-logging:${Versions.ktor}"
    const val ktorClientSerialization =
        "io.ktor:ktor-client-serialization:${Versions.ktor}"
    const val ktorClientAndroid =
        "io.ktor:ktor-client-android:${Versions.ktor}"
    const val ktorClientIos =
        "io.ktor:ktor-client-ios:${Versions.ktor}"

    // test
    const val jUnit4 =
        "junit:junit:${Versions.jUnit4}"

    // debug
    const val timber =
        "com.jakewharton.timber:timber:${Versions.timber}"

    // uuid
    const val uuid =
        "com.benasher44:uuid:${Versions.uuid}"
}

object Versions {
    const val buildGradle = "7.0.3"
    const val kotlin = "1.5.31"
    const val ktLint = "0.41.0"

    object KotlinX {
        const val serializationJson = "1.3.0"
        const val datetime = "0.3.0"
        const val coroutines = "1.5.2-native-mt"
    }

    object AndroidX {
        const val coreKtx = "1.6.0"
        const val appCompat = "1.4.0"
        const val lifecycle = "2.3.1"
        const val lifecycleViewModelCompose = "1.0.0-alpha07"
        const val activityCompose = "1.3.1"
        const val navigation = "2.3.5"
        const val navigationCompose = "2.4.0-alpha06"
        const val hiltNavigationCompose = "1.0.0-alpha03"
        const val compose = "1.0.1"

        object Test {
            const val extJUnit = "1.1.3"
            const val espressoCore = "3.4.0"
        }
    }

    const val androidMaterial = "1.4.0"
    const val swipeRefresh = "0.11.1"
    const val viewPager = "0.17.0"

    // DI
    const val koin = "3.1.2"

    // build config
    const val buildKonfigGradlePlugin = "0.10.2"

    // network
    const val ktor = "1.6.4"

    // test
    const val jUnit4 = "4.13.2"

    // debug
    const val timber = "5.0.1"

    // UUID
    const val uuid = "0.3.1"
}
