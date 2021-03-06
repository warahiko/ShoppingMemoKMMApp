package io.github.warahiko.shoppingmemokmmapplication

import io.github.warahiko.shoppingmemokmmapplication.data.network.api.ShoppingItemApi
import io.github.warahiko.shoppingmemokmmapplication.data.network.api.TagApi
import io.github.warahiko.shoppingmemokmmapplication.data.repository.ShoppingItemRepository
import io.github.warahiko.shoppingmemokmmapplication.data.repository.TagRepository
import io.github.warahiko.shoppingmemokmmapplication.usecase.shoppingitem.AddShoppingItemUseCase
import io.github.warahiko.shoppingmemokmmapplication.usecase.shoppingitem.ArchiveShoppingItemUseCase
import io.github.warahiko.shoppingmemokmmapplication.usecase.shoppingitem.ChangeShoppingItemIsDoneUseCase
import io.github.warahiko.shoppingmemokmmapplication.usecase.shoppingitem.DeleteCompletelyShoppingItemUseCase
import io.github.warahiko.shoppingmemokmmapplication.usecase.shoppingitem.DeleteShoppingItemUseCase
import io.github.warahiko.shoppingmemokmmapplication.usecase.shoppingitem.EditShoppingItemUseCase
import io.github.warahiko.shoppingmemokmmapplication.usecase.shoppingitem.RestoreShoppingItemUseCase
import io.github.warahiko.shoppingmemokmmapplication.usecase.tag.AddTagUseCase
import io.github.warahiko.shoppingmemokmmapplication.usecase.tag.DeleteTagUseCase
import io.github.warahiko.shoppingmemokmmapplication.usecase.tag.EditTagUseCase
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
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication {
    return startKoin {
        appDeclaration()
        modules(repositoryModules, useCaseModules)
    }
}

private val repositoryModules: Module = module {
    single { createHttpClient() }

    single { ShoppingItemApi(get()) }
    single { ShoppingItemRepository(get(), get()) }

    single { TagApi(get()) }
    single { TagRepository(get()) }
}

private val useCaseModules: Module = module {
    single { AddShoppingItemUseCase(get()) }
    single { EditShoppingItemUseCase(get()) }
    single { ArchiveShoppingItemUseCase(get()) }
    single { ChangeShoppingItemIsDoneUseCase(get()) }
    single { DeleteCompletelyShoppingItemUseCase(get()) }
    single { DeleteShoppingItemUseCase(get()) }
    single { RestoreShoppingItemUseCase(get()) }

    single { AddTagUseCase(get()) }
    single { EditTagUseCase(get()) }
    single { DeleteTagUseCase(get()) }
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
