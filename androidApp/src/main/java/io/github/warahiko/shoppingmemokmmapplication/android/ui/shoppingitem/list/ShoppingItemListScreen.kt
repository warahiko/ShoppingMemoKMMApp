package io.github.warahiko.shoppingmemokmmapplication.android.ui.shoppingitem.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.getViewModel

@Composable
fun ShoppingItemListScreen(
//    onClickAddButton: () -> Unit,
//    onEdit: (item: ShoppingItem) -> Unit,
    viewModel: ShoppingItemListScreenViewModel = getViewModel(),
) {
    val uiModel by viewModel.uiModel.collectAsState()
//    val mainShoppingItems by viewModel.mainShoppingItems.collectAsState()
//    val archivedShoppingItems by viewModel.archivedShoppingItems.collectAsState()
//    val deletedShoppingItems by viewModel.deletedShoppingItems.collectAsState()
//    val isRefreshing by viewModel.isRefreshing.collectAsState()
//    val deleteEvent by viewModel.deleteEvent.collectAsState()

    Scaffold(
//        topBar = {
//            ShoppingMemoAppBar(
//                title = stringResource(R.string.home_list_title),
//                icon = Icons.Default.ShoppingCart,
//            )
//        },
    ) {
//        HomeListScreenContent(
//            mainShoppingItems = mainShoppingItems,
//            archivedShoppingItems = archivedShoppingItems,
//            deletedShoppingItems = deletedShoppingItems,
//            isRefreshing = isRefreshing,
//            onClickAddButton = onClickAddButton,
//            onRefresh = viewModel::fetchShoppingList,
//            onClickItemRow = viewModel::changeShoppingItemIsDone,
//            onEdit = onEdit,
//            onArchive = viewModel::archiveShoppingItem,
//            onDelete = viewModel::deleteShoppingItem,
//            onRestore = viewModel::restoreShoppingItem,
//            onArchiveAll = viewModel::archiveAllDone,
//            onDeleteCompletely = viewModel::showDeleteCompletelyConfirmationDialog,
//        )
    }

//    when (deleteEvent) {
//        HomeListScreenViewModel.DeleteEvent.ShowProgressDialog -> {
//            LoadingDialog(isLoading = true)
//        }
//        HomeListScreenViewModel.DeleteEvent.ShowConfirmationDialog -> {
//            DeleteCompletelyDialog(
//                showDialog = true,
//                onConfirm = viewModel::deleteCompletelyShoppingItems,
//                onDismiss = viewModel::dismissDeleteCompletelyConfirmationDialog,
//            )
//        }
//        else -> Unit
//    }

    LaunchedEffect(Unit) {
        viewModel.fetchShoppingItems()
    }
}

//@Composable
//private fun HomeListScreenContent(
//    mainShoppingItems: Map<String, List<ShoppingItem>>,
//    archivedShoppingItems: Map<String, List<ShoppingItem>>,
//    deletedShoppingItems: List<ShoppingItem>,
//    isRefreshing: Boolean,
//    onClickAddButton: () -> Unit,
//    onRefresh: () -> Unit,
//    onClickItemRow: (item: ShoppingItem) -> Unit,
//    onEdit: (item: ShoppingItem) -> Unit,
//    onArchive: (item: ShoppingItem) -> Unit,
//    onDelete: (item: ShoppingItem) -> Unit,
//    onRestore: (item: ShoppingItem) -> Unit,
//    onArchiveAll: () -> Unit,
//    onDeleteCompletely: () -> Unit,
//) {
//    val pagerState = rememberPagerState(pageCount = HomeListTabs.values().size, infiniteLoop = true)
//    val composableScope = rememberCoroutineScope()
//
//    Column {
//        TabRow(
//            selectedTabIndex = pagerState.currentPage,
//            indicator = { tabPositions ->
//                TabRowDefaults.Indicator(
//                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
//                )
//            }
//        ) {
//            HomeListTabs.values().forEachIndexed { index, tabs ->
//                Tab(
//                    selected = pagerState.currentPage == index,
//                    onClick = {
//                        composableScope.launch {
//                            pagerState.animateScrollToPage(index)
//                        }
//                    },
//                    text = {
//                        Text(stringResource(tabs.titleResourceId))
//                    }
//                )
//            }
//        }
//        HorizontalPager(state = pagerState) { page ->
//            SwipeRefresh(
//                state = rememberSwipeRefreshState(isRefreshing),
//                onRefresh = onRefresh,
//            ) {
//                when (HomeListTabs.values()[page]) {
//                    HomeListTabs.Main -> {
//                        MainShoppingItemList(
//                            shoppingItems = mainShoppingItems,
//                            onClickAddButton = onClickAddButton,
//                            onClickItemRow = onClickItemRow,
//                            onEdit = onEdit,
//                            onArchive = onArchive,
//                            onDelete = onDelete,
//                            onArchiveAll = onArchiveAll,
//                        )
//                    }
//                    HomeListTabs.Archived -> {
//                        ArchivedShoppingItemList(
//                            shoppingItems = archivedShoppingItems,
//                            onRestore = onRestore,
//                            onDelete = onDelete,
//                        )
//                    }
//                    HomeListTabs.Deleted -> {
//                        DeletedShoppingItemList(
//                            shoppingItems = deletedShoppingItems,
//                            onRestore = onRestore,
//                            onDeleteCompletely = onDeleteCompletely,
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
