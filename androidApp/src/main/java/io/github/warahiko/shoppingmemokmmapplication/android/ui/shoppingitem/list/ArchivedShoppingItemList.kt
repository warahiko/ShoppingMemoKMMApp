package io.github.warahiko.shoppingmemokmmapplication.android.ui.shoppingitem.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.unit.dp
import io.github.warahiko.shoppingmemokmmapplication.android.R
import io.github.warahiko.shoppingmemokmmapplication.data.model.ShoppingItem

@Composable
fun ArchivedShoppingItemList(
    shoppingItems: Map<String, List<ShoppingItem>>,
    modifier: Modifier = Modifier,
//    onRestore: (item: ShoppingItem) -> Unit = {},
//    onDelete: (item: ShoppingItem) -> Unit = {},
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
//                    onRestore = onRestore,
//                    onDelete = onDelete,
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
//    onRestore: (item: ShoppingItem) -> Unit = {},
//    onDelete: (item: ShoppingItem) -> Unit = {},
) {
//    var showOperation by remember { mutableStateOf(false) }
//    var dropdownOffset by remember { mutableStateOf(Offset.Zero) }

    Box(modifier = modifier) {
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
//            DropdownMenuItem(onClick = { onDelete(item) }) {
//                Text(stringResource(R.string.home_operation_delete))
//            }
//            Divider()
//            DropdownMenuItem(onClick = { showOperation = false }) {
//                Text(stringResource(R.string.cancel))
//            }
//        }
    }
}

//@Preview
//@Composable
//private fun ArchivedShoppingItemListPreview() {
//    val items = ShoppingItem.getSampleMap()
//    ShoppingMemoAppTheme {
//        Surface {
//            ArchivedShoppingItemList(items)
//        }
//    }
//}
//
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//private fun ArchivedShoppingItemListDarkPreview() {
//    val items = ShoppingItem.getSampleMap()
//    ShoppingMemoAppTheme {
//        Surface {
//            ArchivedShoppingItemList(items)
//        }
//    }
//}
