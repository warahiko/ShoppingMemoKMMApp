package io.github.warahiko.shoppingmemokmmapplication.android.ui.common

import io.github.warahiko.shoppingmemokmmapplication.android.error.LaunchSafe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class ExternalScope(
    launchSafe: LaunchSafe,
) : CoroutineScope, LaunchSafe by launchSafe {

    val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}
