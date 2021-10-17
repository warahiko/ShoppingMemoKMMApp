package io.github.warahiko.shoppingmemokmmapplication.android.ui.shoppingitem.list

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ReportProblem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.warahiko.shoppingmemokmmapplication.android.R
import io.github.warahiko.shoppingmemokmmapplication.android.ui.theme.ShoppingMemoAppTheme

@Composable
fun ShoppingItemListFailed(
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = Icons.Rounded.ReportProblem,
            contentDescription = null,
            tint = MaterialTheme.colors.primary,
            modifier = Modifier.size(60.dp),
        )
        Text(
            stringResource(R.string.shopping_item_list_error),
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(top = 4.dp),
        )
        Button(
            onClick = onClickRetry,
            modifier = Modifier.padding(top = 40.dp),
        ) {
            Text(stringResource(R.string.shopping_item_list_error_button))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingItemListFailedPreview() {
    ShoppingMemoAppTheme {
        ShoppingItemListFailed()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ShoppingItemListFailedDarkPreview() {
    ShoppingMemoAppTheme {
        ShoppingItemListFailed()
    }
}

