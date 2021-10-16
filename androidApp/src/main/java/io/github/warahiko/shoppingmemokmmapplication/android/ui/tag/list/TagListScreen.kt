package io.github.warahiko.shoppingmemokmmapplication.android.ui.tag.list

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
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
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Label
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.github.warahiko.shoppingmemokmmapplication.android.R
import io.github.warahiko.shoppingmemokmmapplication.android.ui.common.ShoppingMemoAppBar
import io.github.warahiko.shoppingmemokmmapplication.data.model.Tag
import org.koin.androidx.compose.getViewModel

@Composable
fun TagListScreen(
//    onClickAddButton: () -> Unit,
//    onEdit: (tag: Tag) -> Unit,
    viewModel: TagListScreenViewModel = getViewModel(),
) {
    val uiModel by viewModel.uiModel.collectAsState()
//    val isRefreshing by viewModel.isRefreshing.collectAsState()
//    val deleteEvent by viewModel.deleteEvent.collectAsState()

    Scaffold(
        topBar = {
            ShoppingMemoAppBar(
                title = stringResource(R.string.tag_list_title),
                icon = Icons.Default.Label,
            )
        },
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = onClickAddButton,
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = null,
//                )
//            }
//        }
    ) {
        TagListScreenContent(
            tags = uiModel.tagsGroupedByType,
//            isRefreshing = isRefreshing,
//            onRefresh = viewModel::fetchTags,
//            onEdit = onEdit,
//            onDelete = viewModel::showDeleteTagConfirmationDialog,
        )
    }

//    @Suppress("UnnecessaryVariable")
//    when (val event = deleteEvent) {
//        TagListScreenViewModel.DeleteEvent.ShowProgressDialog -> {
//            LoadingDialog(isLoading = true)
//        }
//        is TagListScreenViewModel.DeleteEvent.ShowConfirmationDialog -> {
//            DeleteTagConfirmationDialog(
//                showDialog = true,
//                tag = event.tag,
//                onConfirm = viewModel::deleteTag,
//                onDismiss = { viewModel.dismissDeleteTagConfirmationDialog() },
//            )
//        }
//        else -> Unit
//    }
}

@Composable
private fun TagListScreenContent(
    tags: Map<String, List<Tag>>,
    modifier: Modifier = Modifier,
//    isRefreshing: Boolean = false,
//    onRefresh: () -> Unit = {},
//    onEdit: (tag: Tag) -> Unit = {},
//    onDelete: (tag: Tag) -> Unit = {},
) {
    SwipeRefresh(
//        state = rememberSwipeRefreshState(isRefreshing),
//        onRefresh = onRefresh,
        state = rememberSwipeRefreshState(false),
        onRefresh = {}
    ) {
        if (tags.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                Text(
                    stringResource(id = R.string.tag_list_empty),
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            return@SwipeRefresh
        }

        LazyColumn(
            modifier = modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            tags.forEach { (type, list) ->
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
                itemsIndexed(list, key = { _, item -> item.id }) { index, item ->
                    ItemRow(
                        tag = item,
                        modifier = Modifier.padding(start = 16.dp),
//                        onEdit = onEdit,
//                        onDelete = onDelete,
                    )
                    if (index < list.size - 1) {
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
}

@Composable
private fun ItemRow(
    tag: Tag,
    modifier: Modifier = Modifier,
//    onEdit: (tag: Tag) -> Unit = {},
//    onDelete: (tag: Tag) -> Unit = {},
) {
    var showOperation by remember { mutableStateOf(false) }
    var dropdownOffset by remember { mutableStateOf(Offset.Zero) }

    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth()
            .indication(
                interactionSource = interactionSource,
                indication = LocalIndication.current,
            )
            .pointerInput(tag) {
                detectTapGestures(
                    onLongPress = {
                        showOperation = true
                        dropdownOffset = it
                    },
                    onPress = {
                        val press = PressInteraction.Press(it)
                        interactionSource.emit(press)
                        val result = if (tryAwaitRelease()) {
                            PressInteraction.Release(press)
                        } else {
                            PressInteraction.Cancel(press)
                        }
                        interactionSource.emit(result)
                    }
                )
            },
    ) {
        Text(
            tag.name,
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterStart)
        )
        DropdownMenu(
            expanded = showOperation,
            onDismissRequest = { showOperation = false },
            offset = LocalDensity.current.run {
                DpOffset(dropdownOffset.x.toDp(), 0.dp)
            },
        ) {
//            DropdownMenuItem(onClick = { onEdit(tag) }) {
//                Text(stringResource(R.string.tag_list_operation_edit))
//            }
//            DropdownMenuItem(onClick = { onDelete(tag) }) {
//                Text(stringResource(R.string.tag_list_operation_delete))
//            }
            Divider()
            DropdownMenuItem(onClick = { showOperation = false }) {
                Text(stringResource(R.string.cancel))
            }
        }
    }
}
