package io.github.warahiko.shoppingmemokmmapplication.android.ui.shoppingitem.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.warahiko.shoppingmemokmmapplication.android.R
import io.github.warahiko.shoppingmemokmmapplication.android.ui.common.LoadingDialog
import io.github.warahiko.shoppingmemokmmapplication.android.ui.common.ShoppingMemoAppBar
import io.github.warahiko.shoppingmemokmmapplication.android.ui.common.compositionlocal.LocalTagMap
import io.github.warahiko.shoppingmemokmmapplication.android.ui.shoppingitem.common.ShoppingItemEditor
import io.github.warahiko.shoppingmemokmmapplication.android.ui.theme.ShoppingMemoAppTheme
import io.github.warahiko.shoppingmemokmmapplication.data.model.ShoppingItem
import io.github.warahiko.shoppingmemokmmapplication.data.model.ShoppingItemEditable
import org.koin.androidx.compose.getViewModel

@Composable
fun AddShoppingItemScreen(
    onBack: () -> Unit,
    viewModel: AddShoppingItemScreenViewModel = getViewModel(),
) {
    val uiModel by viewModel.uiModel.collectAsState()
    val showProgress by viewModel.showProgress.collectAsState()

    Scaffold(
        topBar = {
            ShoppingMemoAppBar(
                title = stringResource(R.string.shopping_item_add_title),
                icon = Icons.Default.ArrowBack,
                onClickIcon = onBack,
            )
        },
    ) {
        CompositionLocalProvider(LocalTagMap provides uiModel.tagsGroupedByType) {
            AddShoppingItemScreenContent(onAdd = {
                viewModel.addShoppingItem(it)
                    .invokeOnCompletion {
                        onBack()
                    }
            })
        }
    }

    LoadingDialog(isLoading = showProgress)
}

@Composable
private fun AddShoppingItemScreenContent(
    onAdd: (item: ShoppingItem) -> Unit = {},
) {
    val (shoppingItem, setShoppingItem) = remember { mutableStateOf(ShoppingItemEditable.newInstanceToAdd()) }
    val buttonEnabled by remember(shoppingItem) {
        derivedStateOf {
            shoppingItem.tag != null && shoppingItem.count.let { it.isNotBlank() && it.toInt() > 0 }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        ShoppingItemEditor(
            shoppingItem = shoppingItem,
            onChangeItem = setShoppingItem,
            modifier = Modifier.fillMaxWidth(),
        )
        Button(
            onClick = { onAdd(shoppingItem.fix()) },
            enabled = buttonEnabled,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.End),
        ) {
            Text(stringResource(R.string.shopping_item_add_button))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddShoppingItemScreenPreview() {
    ShoppingMemoAppTheme {
        AddShoppingItemScreenContent()
    }
}
