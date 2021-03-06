package io.github.warahiko.shoppingmemokmmapplication.android.ui.shoppingitem.list

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import io.github.warahiko.shoppingmemokmmapplication.android.R
import io.github.warahiko.shoppingmemokmmapplication.android.ui.theme.ShoppingMemoAppTheme
import io.github.warahiko.shoppingmemokmmapplication.data.model.ShoppingItem
import io.github.warahiko.shoppingmemokmmapplication.data.model.preview.ShoppingItemPreview

@Composable
fun ArchivedShoppingItemList(
    shoppingItems: Map<String, List<ShoppingItem>>,
    modifier: Modifier = Modifier,
    onRestore: (item: ShoppingItem) -> Unit = {},
    onDelete: (item: ShoppingItem) -> Unit = {},
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
        shoppingItems.forEach { (date, items) ->
            stickyHeader {
                Column(
                    modifier = Modifier
                        .background(color = MaterialTheme.colors.background)
                        .fillMaxWidth(),
                ) {
                    Text(
                        date,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .height(48.dp)
                            .padding(vertical = 8.dp)
                            .padding(start = 16.dp),
                    )
                    Divider(color = MaterialTheme.colors.onBackground)
                }
            }
            itemsIndexed(items, key = { _, item -> item.id }) { index, item ->
                ItemRow(
                    item = item,
                    modifier = Modifier.padding(start = 16.dp),
                    onRestore = onRestore,
                    onDelete = onDelete,
                )
                if (index < items.size - 1) {
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
private fun ItemRow(
    item: ShoppingItem,
    modifier: Modifier = Modifier,
    onRestore: (item: ShoppingItem) -> Unit = {},
    onDelete: (item: ShoppingItem) -> Unit = {},
) {
    var showOperation by remember { mutableStateOf(false) }
    var dropdownOffset by remember { mutableStateOf(Offset.Zero) }

    Box(modifier = modifier) {
        ShoppingItemRow(
            shoppingItem = item,
            checkBoxIsVisible = false,
            onLongPress = { offset ->
                showOperation = true
                dropdownOffset = offset
            },
        )
        DropdownMenu(
            expanded = showOperation,
            onDismissRequest = { showOperation = false },
            offset = LocalDensity.current.run {
                DpOffset(dropdownOffset.x.toDp(), 0.dp)
            },
        ) {
            DropdownMenuItem(onClick = { onRestore(item) }) {
                Text(stringResource(R.string.shopping_item_list_operation_restore))
            }
            DropdownMenuItem(onClick = { onDelete(item) }) {
                Text(stringResource(R.string.shopping_item_list_operation_delete))
            }
            Divider()
            DropdownMenuItem(onClick = { showOperation = false }) {
                Text(stringResource(R.string.cancel))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArchivedShoppingItemListPreview() {
    val items = ShoppingItemPreview.sampleMap
    ShoppingMemoAppTheme {
        ArchivedShoppingItemList(items)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ArchivedShoppingItemListDarkPreview() {
    val items = ShoppingItemPreview.sampleMap
    ShoppingMemoAppTheme {
        ArchivedShoppingItemList(items)
    }
}
