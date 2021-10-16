package io.github.warahiko.shoppingmemokmmapplication.android.ui.tag.edit

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
import androidx.compose.ui.unit.dp
import io.github.warahiko.shoppingmemokmmapplication.android.R
import io.github.warahiko.shoppingmemokmmapplication.android.ui.common.LoadingDialog
import io.github.warahiko.shoppingmemokmmapplication.android.ui.common.ShoppingMemoAppBar
import io.github.warahiko.shoppingmemokmmapplication.android.ui.common.compositionlocal.LocalTypeList
import io.github.warahiko.shoppingmemokmmapplication.android.ui.tag.common.TagEditor
import io.github.warahiko.shoppingmemokmmapplication.data.model.Tag
import org.koin.androidx.compose.getViewModel

@Composable
fun EditTagScreen(
    defaultTagId: String,
    onBack: () -> Unit,
    viewModel: EditTagScreenViewModel = getViewModel(),
) {
    val defaultTag = remember {
        viewModel.getTag(defaultTagId) ?: run {
            onBack()
            return
        }
    }
    val uiModel by viewModel.uiModel.collectAsState()
    val showProgress by viewModel.showProgress.collectAsState()

    Scaffold(
        topBar = {
            ShoppingMemoAppBar(
                title = stringResource(R.string.tag_edit_title),
                icon = Icons.Default.ArrowBack,
                onClickIcon = onBack,
            )
        },
    ) {
        CompositionLocalProvider(LocalTypeList provides uiModel.types) {
            EditTagScreenContent(
                defaultTag = defaultTag,
                onEdit = {
                    viewModel.editTag(it)
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
private fun EditTagScreenContent(
    defaultTag: Tag,
    onEdit: (tag: Tag) -> Unit,
) {
    val (tag, setTag) = remember { mutableStateOf(defaultTag) }
    val buttonEnabled by remember(tag) {
        derivedStateOf { tag.name.isNotBlank() && tag.type.isNotBlank() }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        TagEditor(
            tag = tag,
            onChangeTag = setTag,
            modifier = Modifier.fillMaxWidth(),
        )
        Button(
            onClick = { onEdit(tag) },
            enabled = buttonEnabled,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.End),
        ) {
            Text(stringResource(R.string.tag_edit_button))
        }
    }
}
