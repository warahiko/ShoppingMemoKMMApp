package io.github.warahiko.shoppingmemokmmapplication.android.ui.common

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.warahiko.shoppingmemokmmapplication.android.ui.theme.ShoppingMemoAppTheme

@Composable
fun ShoppingMemoAppBar(
    title: String,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    onClickIcon: (() -> Unit)? = null,
) {
    TopAppBar(modifier = modifier) {
        if (icon != null) {
            Box(
                modifier = Modifier
                    .clickable(
                        enabled = onClickIcon != null,
                        onClick = { onClickIcon?.invoke() },
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(radius = 28.dp),
                    )
                    .size(56.dp),
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Center),
                )
            }
        }
        Spacer(Modifier.width(16.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f),
        )
    }
}

@Preview
@Composable
private fun ShoppingMemoAppBarPreview() {
    ShoppingMemoAppTheme {
        ShoppingMemoAppBar(title = "買い物メモ", icon = Icons.Default.ShoppingCart)
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ShoppingMemoAppBarDarkPreview() {
    ShoppingMemoAppTheme {
        ShoppingMemoAppBar(title = "買い物メモ", icon = Icons.Default.ShoppingCart)
    }
}

@Preview
@Composable
private fun ShoppingMemoAppBar_IconEmptyPreview() {
    ShoppingMemoAppTheme {
        ShoppingMemoAppBar(title = "買い物メモ")
    }
}
