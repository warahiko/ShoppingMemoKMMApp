package io.github.warahiko.shoppingmemokmmapplication.android.ui.shoppingitem.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer
import io.github.warahiko.shoppingmemokmmapplication.android.ui.theme.ShoppingMemoAppTheme

@Composable
fun ShoppingItemListLoading(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .background(color = MaterialTheme.colors.background)
            .fillMaxWidth()
            .shimmer(),
    ) {
        repeat(2) {
            LoadingHeader()
            repeat(2) {
                LoadingRow(Modifier.padding(start = 16.dp))
                if (it < 1) {
                    Divider(
                        color = MaterialTheme.colors.onBackground,
                        startIndent = 16.dp,
                        modifier = Modifier.alpha(0.5f),
                    )
                }
            }
        }
    }
}

@Composable
private fun LoadingHeader(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .height(48.dp)
                .padding(vertical = 8.dp)
                .padding(start = 16.dp),
        ) {
            Box(
                modifier = Modifier
                    .height(LocalDensity.current.run {
                        MaterialTheme.typography.h6.fontSize.toDp()
                    })
                    .width(160.dp)
                    .background(
                        color = Color.Gray,
                        shape = RoundedCornerShape(4.dp),
                    ),
            )
        }
        Divider(color = MaterialTheme.colors.onBackground)
    }
}

@Composable
private fun LoadingRow(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .size(16.dp)
                .align(Alignment.CenterVertically)
                .background(
                    color = Color.Gray,
                    shape = RoundedCornerShape(4.dp),
                ),
        )
        Box(
            modifier = Modifier
                .padding(8.dp)
                .height(LocalDensity.current.run {
                    MaterialTheme.typography.body1.fontSize.toDp()
                })
                .width(320.dp)
                .align(Alignment.CenterVertically)
                .background(
                    color = Color.Gray,
                    shape = RoundedCornerShape(4.dp),
                ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ShoppingITemListLoadingPreview() {
    ShoppingMemoAppTheme {
        ShoppingItemListLoading()
    }
}
