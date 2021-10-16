package io.github.warahiko.shoppingmemokmmapplication.android.ui.shoppingitem.list

import android.content.res.Configuration
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
import androidx.compose.material.Scaffold
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
fun MainShoppingItemList(
    shoppingItems: Map<String, List<ShoppingItem>>,
    modifier: Modifier = Modifier,
//    onClickAddButton: () -> Unit = {},
//    onClickItemRow: (item: ShoppingItem) -> Unit = {},
//    onEdit: (item: ShoppingItem) -> Unit = {},
//    onArchive: (item: ShoppingItem) -> Unit = {},
//    onDelete: (item: ShoppingItem) -> Unit = {},
//    onArchiveAll: () -> Unit = {},
) {
//    val isAnyItemDone = remember(shoppingItems) {
//        shoppingItems.values.flatten().any { it.isDone }
//    }

    Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = onClickAddButton,
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = null,
//                )
//            }
//        },
    ) {
        if (shoppingItems.isEmpty()) {
            Box(
                // SwipeToRefresh のためにverticalScroll を設定
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                Text(
                    stringResource(id = R.string.shopping_item_list_empty),
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            return@Scaffold
        }

        LazyColumn(
            modifier = modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            shoppingItems.forEach { (type, items) ->
                stickyHeader {
                    Column(
                        modifier = Modifier
                            .background(color = MaterialTheme.colors.background)
                            .fillMaxWidth(),
                    ) {
                        Text(
                            type,
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
//                        onClickItemRow = { onClickItemRow(item) },
//                        onEdit = { onEdit(item) },
//                        onArchive = { onArchive(item) },
//                        onDelete = { onDelete(item) },
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
//            item {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp),
//                ) {
//                    Button(
//                        onClick = onArchiveAll,
//                        enabled = isAnyItemDone,
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .height(60.dp)
//                            .padding(8.dp),
//                    ) {
//                        Text(stringResource(R.string.home_list_archive_button))
//                    }
//                }
//            }
        }
    }
}

@Composable
private fun ItemRow(
    item: ShoppingItem,
    modifier: Modifier = Modifier,
//    onClickItemRow: () -> Unit = {},
//    onEdit: () -> Unit = {},
//    onArchive: () -> Unit = {},
//    onDelete: () -> Unit = {},
) {
//    var showOperation by remember { mutableStateOf(false) }
//    var dropdownOffset by remember { mutableStateOf(Offset.Zero) }

    Box(modifier = modifier) {
        ShoppingItemRow(
            shoppingItem = item,
//            onClick = onClickItemRow,
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
//            DropdownMenuItem(onClick = onEdit) {
//                Text(stringResource(R.string.home_operation_edit))
//            }
//            if (item.isDone) {
//                DropdownMenuItem(onClick = onArchive) {
//                    Text(stringResource(R.string.home_operation_archive))
//                }
//            }
//            DropdownMenuItem(onClick = onDelete) {
//                Text(stringResource(R.string.home_operation_delete))
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
private fun ShoppingListPreview() {
    val items = ShoppingItemPreview.getSampleMap()
    ShoppingMemoAppTheme {
        MainShoppingItemList(items)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ShoppingListDarkPreview() {
    val items = ShoppingItemPreview.getSampleMap()
    ShoppingMemoAppTheme {
        MainShoppingItemList(items)
    }
}
