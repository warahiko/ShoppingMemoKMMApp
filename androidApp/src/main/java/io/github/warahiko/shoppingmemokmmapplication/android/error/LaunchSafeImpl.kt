package io.github.warahiko.shoppingmemokmmapplication.android.error

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LaunchSafeImpl(
    private val errorCatcher: ErrorCatcher,
) : LaunchSafe {

    override fun CoroutineScope.launchSafe(
        context: CoroutineContext,
        start: CoroutineStart,
        block: suspend CoroutineScope.() -> Unit,
    ): Job = launch(context + errorCatcher.defaultPolicy, start, block)
}
