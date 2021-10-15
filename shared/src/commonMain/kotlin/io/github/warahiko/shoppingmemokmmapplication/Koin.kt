package io.github.warahiko.shoppingmemokmmapplication

import io.github.warahiko.shoppingmemokmmapplication.data.network.api.ShoppingItemListApi
import io.github.warahiko.shoppingmemokmmapplication.data.network.api.TagListApi
import io.github.warahiko.shoppingmemokmmapplication.data.repository.ShoppingItemListRepository
import io.github.warahiko.shoppingmemokmmapplication.data.repository.TagListRepository
import io.ktor.client.HttpClient
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication {
    return startKoin {
        appDeclaration()
        modules(modules)
    }
}

private val modules: Module = module {
    single { createHttpClient() }

    single { ShoppingItemListApi(get()) }
    single { ShoppingItemListRepository(get(), get()) }

    single { TagListApi(get()) }
    single { TagListRepository(get()) }
}

private fun createHttpClient(): HttpClient {
    return HttpClient {
        defaultRequest {
            contentType(ContentType.Application.Json)
            headers {
                append(HttpHeaders.Authorization, "Bearer ${BuildKonfig.NOTION_TOKEN}")
                append("Notion-Version", BuildKonfig.NOTION_VERSION)
            }
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                json = Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
    }
}

@Suppress("unused")
fun initKoinIos() = initKoin {}

@Suppress("unused")
class InjectorIos : KoinComponent {
    val shoppingItemListRepository: ShoppingItemListRepository by inject()
    val tagListRepository: TagListRepository by inject()
}
