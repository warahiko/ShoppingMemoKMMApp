package io.github.warahiko.shoppingmemokmmapplication.android.ui.tag.list

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.warahiko.shoppingmemokmmapplication.android.R
import io.github.warahiko.shoppingmemokmmapplication.data.model.Tag

@Composable
fun DeleteTagConfirmationDialog(
    showDialog: Boolean,
    tag: Tag?,
    onConfirm: (tag: Tag) -> Unit = {},
    onDismiss: (tag: Tag) -> Unit = {},
) {
    if (!showDialog || tag == null) return

    AlertDialog(
        onDismissRequest = { onDismiss(tag) },
        title = {
            Text(stringResource(R.string.tag_delete_dialog_title))
        },
        text = {
            Text(stringResource(R.string.tag_delete_dialog_message, tag.name))
        },
        confirmButton = {
            TextButton(onClick = { onConfirm(tag) }) {
                Text(stringResource(R.string.tag_delete_dialog_ok))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss(tag) }) {
                Text(stringResource(R.string.cancel))
            }
        },
    )
}
