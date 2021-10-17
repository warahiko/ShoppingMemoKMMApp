package io.github.warahiko.shoppingmemokmmapplication.android.ui.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import io.github.warahiko.shoppingmemokmmapplication.android.R
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel

@Composable
fun SplashScreen(
    onNext: () -> Unit,
    viewModel: SplashScreenViewModel = getViewModel(),
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            tint = MaterialTheme.colors.onBackground,
            contentDescription = null,
            modifier = Modifier
                .size(192.dp)
                .align(Alignment.Center),
        )
    }

    LaunchedEffect(Unit) {
        viewModel.fetchShoppingItems()
        // 500ms スプラッシュ画面を表示
        delay(500)
        onNext()
    }
}
