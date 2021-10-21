package io.github.warahiko.shoppingmemokmmapplication.android.ui.shoppingitem.edit

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
import io.github.warahiko.shoppingmemokmmapplication.data.model.preview.ShoppingItemPreview
import org.koin.androidx.compose.getViewModel

@Composable
fun EditShoppingItemScreen(
    defaultShoppingItemId: String,
    onBack: () -> Unit,
    viewModel: EditShoppingItemScreenViewModel = getViewModel(),
) {
    val defaultShoppingItem = remember {
        viewModel.getShoppingItem(defaultShoppingItemId) ?: run {
            onBack()
            return
        }
    }
    val uiModel by viewModel.uiModel.collectAsState()
    val showProgress by viewModel.showProgress.collectAsState()

    Scaffold(
        topBar = {
            ShoppingMemoAppBar(
                title = stringResource(R.string.shopping_item_edit_title),
                icon = Icons.Default.ArrowBack,
                onClickIcon = onBack,
            )
        },
    ) {
        CompositionLocalProvider(LocalTagMap provides uiModel.tagsGroupedByType) {
            EditShoppingItemScreenContent(
                defaultShoppingItem = defaultShoppingItem,
                onConfirm = {
                    viewModel.editShoppingItem(it)
                        .invokeOnCompletion {
                            onBack()
                        }
                },
            )
        }
    }

    LoadingDialog(isLoading = showProgress)
}

@Composable
private fun EditShoppingItemScreenContent(
    defaultShoppingItem: ShoppingItem,
    onConfirm: (item: ShoppingItem) -> Unit = {},
) {
    val (shoppingItem, setShoppingItem) = remember(defaultShoppingItem) {
        mutableStateOf(defaultShoppingItem.toEditable())
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
            onClick = { onConfirm(shoppingItem.fix()) },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.End),
        ) {
            Text(stringResource(R.string.shopping_item_edit_button))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditShoppingItemScreenPreview() {
    val shoppingItem = ShoppingItemPreview.sample
    ShoppingMemoAppTheme {
        EditShoppingItemScreenContent(shoppingItem)
    }
}
