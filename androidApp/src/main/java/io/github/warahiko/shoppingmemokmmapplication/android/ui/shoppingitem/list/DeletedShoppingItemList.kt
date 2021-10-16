package io.github.warahiko.shoppingmemokmmapplication.android.ui.shoppingitem.list

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.warahiko.shoppingmemokmmapplication.android.R
import io.github.warahiko.shoppingmemokmmapplication.android.ui.preview.ShoppingItemPreview
import io.github.warahiko.shoppingmemokmmapplication.android.ui.theme.ShoppingMemoAppTheme
import io.github.warahiko.shoppingmemokmmapplication.data.model.ShoppingItem

@Composable
fun DeletedShoppingItemList(
    shoppingItems: List<ShoppingItem>,
    modifier: Modifier = Modifier,
//    onRestore: (item: ShoppingItem) -> Unit = {},
//    onDeleteCompletely: () -> Unit = {},
) {
    if (shoppingItems.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Text(
                stringResource(id = R.string.shopping_item_list_empty),
                modifier = Modifier.align(Alignment.Center),
            )
        }
        return
    }

    LazyColumn(
        modifier = modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        items(shoppingItems.size, key = { shoppingItems[it].id }) { index ->
            val item = shoppingItems[index]
            ItemRow(
                item = item,
//                onRestore = onRestore,
            )
            if (index < shoppingItems.size - 1) {
                Divider(
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.alpha(0.5f),
                )
            }
        }
//        item {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//            ) {
//                Button(
//                    onClick = onDeleteCompletely,
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .height(60.dp)
//                        .padding(8.dp),
//                ) {
//                    Text(stringResource(R.string.home_list_delete_completely_button))
//                }
//            }
//        }
    }
}

@Composable
private fun ItemRow(
    item: ShoppingItem,
//    onRestore: (item: ShoppingItem) -> Unit = {},
) {
//    var showOperation by remember { mutableStateOf(false) }
//    var dropdownOffset by remember { mutableStateOf(Offset.Zero) }

    Box {
        ShoppingItemRow(
            shoppingItem = item,
            checkBoxIsVisible = false,
//            onLongPress = { offset ->
//                showOperation = true
//                dropdownOffset = offset
//            },
        )
//        DropdownMenu(
//            expanded = showOperation,
//            onDismissRequest = { showOperation = false },
//            offset = LocalDensity.current.run {
//                DpOffset(dropdownOffset.x.toDp(), 0.dp)
//            },
//        ) {
//            DropdownMenuItem(onClick = { onRestore(item) }) {
//                Text(stringResource(R.string.home_operation_restore))
//            }
//            Divider()
//            DropdownMenuItem(onClick = { showOperation = false }) {
//                Text(stringResource(R.string.cancel))
//            }
//        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DeletedShoppingItemListPreview() {
    val items = ShoppingItemPreview.getSampleList()
    ShoppingMemoAppTheme {
        DeletedShoppingItemList(items)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DeletedShoppingItemListDarkPreview() {
    val items = ShoppingItemPreview.getSampleList()
    ShoppingMemoAppTheme {
        DeletedShoppingItemList(items)
    }
}
