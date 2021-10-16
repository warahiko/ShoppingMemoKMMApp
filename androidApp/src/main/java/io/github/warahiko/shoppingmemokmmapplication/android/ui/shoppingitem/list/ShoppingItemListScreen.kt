package io.github.warahiko.shoppingmemokmmapplication.android.ui.shoppingitem.list

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.github.warahiko.shoppingmemokmmapplication.android.R
import io.github.warahiko.shoppingmemokmmapplication.android.ui.common.LoadingDialog
import io.github.warahiko.shoppingmemokmmapplication.android.ui.common.ShoppingMemoAppBar
import io.github.warahiko.shoppingmemokmmapplication.data.model.ShoppingItem
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun ShoppingItemListScreen(
    onClickAddButton: () -> Unit,
    onEdit: (item: ShoppingItem) -> Unit,
    viewModel: ShoppingItemListScreenViewModel = getViewModel(),
) {
    val uiModel by viewModel.uiModel.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val deleteEvent by viewModel.deleteEvent.collectAsState()

    Scaffold(
        topBar = {
            ShoppingMemoAppBar(
                title = stringResource(R.string.shopping_item_list_title),
                icon = Icons.Default.ShoppingCart,
            )
        },
    ) {
        HomeListScreenContent(
            uiModel = uiModel,
            isRefreshing = isRefreshing,
            onClickAddButton = onClickAddButton,
            onRefresh = viewModel::refreshShoppingItems,
            onClickItemRow = viewModel::changeShoppingItemIsDone,
            onEdit = onEdit,
            onArchive = viewModel::archiveShoppingItem,
            onDelete = viewModel::deleteShoppingItem,
            onRestore = viewModel::restoreShoppingItem,
            onArchiveAll = viewModel::archiveAllDone,
            onDeleteCompletely = viewModel::showDeleteCompletelyConfirmationDialog,
        )
    }

    when (deleteEvent) {
        ShoppingItemListScreenViewModel.DeleteEvent.ShowProgressDialog -> {
            LoadingDialog(isLoading = true)
        }
        ShoppingItemListScreenViewModel.DeleteEvent.ShowConfirmationDialog -> {
            DeleteCompletelyDialog(
                showDialog = true,
                onConfirm = viewModel::deleteCompletelyShoppingItems,
                onDismiss = viewModel::dismissDeleteCompletelyConfirmationDialog,
            )
        }
        else -> Unit
    }

    LaunchedEffect(Unit) {
        viewModel.fetchShoppingItems()
    }
}

@Composable
private fun HomeListScreenContent(
    uiModel: ShoppingItemListScreenViewModel.UiModel,
    isRefreshing: Boolean = false,
    onClickAddButton: () -> Unit = {},
    onRefresh: () -> Unit = {},
    onClickItemRow: (item: ShoppingItem) -> Unit = {},
    onEdit: (item: ShoppingItem) -> Unit = {},
    onArchive: (item: ShoppingItem) -> Unit = {},
    onDelete: (item: ShoppingItem) -> Unit = {},
    onRestore: (item: ShoppingItem) -> Unit = {},
    onArchiveAll: () -> Unit = {},
    onDeleteCompletely: () -> Unit = {},
) {
    val pagerState = rememberPagerState(pageCount = ShoppingItemListTab.values().size, infiniteLoop = true)
    val composableScope = rememberCoroutineScope()

    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }
        ) {
            ShoppingItemListTab.values().forEachIndexed { index, tabs ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        composableScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(stringResource(tabs.titleResId))
                    }
                )
            }
        }
        HorizontalPager(state = pagerState) { page ->
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing),
                onRefresh = onRefresh,
            ) {
                when (ShoppingItemListTab.values()[page]) {
                    ShoppingItemListTab.Main -> {
                        MainShoppingItemList(
                            shoppingItems = uiModel.mainShoppingItems,
                            onClickAddButton = onClickAddButton,
                            onClickItemRow = onClickItemRow,
                            onEdit = onEdit,
                            onArchive = onArchive,
                            onDelete = onDelete,
                            onArchiveAll = onArchiveAll,
                        )
                    }
                    ShoppingItemListTab.Archived -> {
                        ArchivedShoppingItemList(
                            shoppingItems = uiModel.archivedShoppingItems,
                            onRestore = onRestore,
                            onDelete = onDelete,
                        )
                    }
                    ShoppingItemListTab.Deleted -> {
                        DeletedShoppingItemList(
                            shoppingItems = uiModel.deletedShoppingItems,
                            onRestore = onRestore,
                            onDeleteCompletely = onDeleteCompletely,
                        )
                    }
                }
            }
        }
    }
}
