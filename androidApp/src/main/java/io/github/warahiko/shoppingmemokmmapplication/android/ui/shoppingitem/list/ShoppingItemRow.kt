package io.github.warahiko.shoppingmemokmmapplication.android.ui.shoppingitem.list

import android.content.res.Configuration
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.warahiko.shoppingmemokmmapplication.android.ui.preview.ShoppingItemPreview
import io.github.warahiko.shoppingmemokmmapplication.android.ui.theme.ShoppingMemoAppTheme
import io.github.warahiko.shoppingmemokmmapplication.data.model.ShoppingItem

@Composable
fun ShoppingItemRow(
    shoppingItem: ShoppingItem,
    modifier: Modifier = Modifier,
    checkBoxIsVisible: Boolean = true,
    onClick: () -> Unit = {},
    onLongPress: (offset: Offset) -> Unit = {},
) {
    var isExpanded by remember { mutableStateOf(false) }
    ShoppingItemRowContent(
        shoppingItem = shoppingItem,
        isExpanded = isExpanded,
        modifier = modifier,
        checkBoxIsVisible = checkBoxIsVisible,
        onClick = onClick,
        onClickMemo = {
            isExpanded = !isExpanded
        },
        onLongPress = onLongPress,
    )
}

@Composable
private fun ShoppingItemRowContent(
    shoppingItem: ShoppingItem,
    isExpanded: Boolean,
    modifier: Modifier = Modifier,
    checkBoxIsVisible: Boolean = true,
    onClick: () -> Unit = {},
    onClickMemo: () -> Unit = {},
    onLongPress: (offset: Offset) -> Unit = {},
) {
    val transition = updateTransition(targetState = isExpanded, label = "expand")
    val memoHeight by transition.animateDp(label = "memoHeight") {
        if (it) 32.dp else 0.dp
    }

    val interactionSource = remember { MutableInteractionSource() }

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
                .indication(
                    interactionSource = interactionSource,
                    indication = LocalIndication.current,
                )
                .pointerInput(shoppingItem) {
                    detectTapGestures(
                        onTap = { onClick() },
                        onLongPress = { onLongPress(it) },
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
            if (checkBoxIsVisible) {
                Checkbox(
                    shoppingItem.isDone,
                    onCheckedChange = null,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically),
                )
            } else {
                Spacer(Modifier.width(8.dp))
            }
            Text(
                shoppingItem.name,
                textDecoration = if (shoppingItem.isDone) TextDecoration.LineThrough else null,
                color = if (shoppingItem.isDone) Color.Gray else Color.Unspecified,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
                    .align(Alignment.CenterVertically),
            )
            Text(
                shoppingItem.count.toString(),
                textDecoration = if (shoppingItem.isDone) TextDecoration.LineThrough else null,
                color = if (shoppingItem.isDone) Color.Gray else Color.Unspecified,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically),
            )
            MemoIcon(
                isExpandedTransition = transition,
                memoExists = shoppingItem.memo.isNotBlank(),
                onClickMemo = onClickMemo,
                modifier = Modifier.align(Alignment.CenterVertically),
            )
        }
        Row(modifier = Modifier.height(memoHeight)) {
            Icon(
                imageVector = Icons.Default.Description,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.Top),
            )
            Text(
                shoppingItem.memo,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.Top),
            )
        }
    }
}

@Composable
private fun MemoIcon(
    isExpandedTransition: Transition<Boolean>,
    memoExists: Boolean,
    onClickMemo: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val memoIconRotate by isExpandedTransition.animateFloat(label = "memoIconRotate") {
        if (it) 360f else 0f
    }
    val memoIconAlpha by isExpandedTransition.animateFloat(label = "memoIconAlpha") {
        if (it) 0f else 1f
    }
    val iconTint = if (memoExists) MaterialTheme.colors.secondary else Color.Gray

    BoxWithConstraints(
        modifier = modifier
            .fillMaxHeight()
            .aspectRatio(1f),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .then(
                    if (memoExists) Modifier.clickable(
                        onClick = onClickMemo,
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(radius = maxHeight / 2),
                    )
                    else Modifier
                )
                .padding(8.dp)
                .rotate(memoIconRotate),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.alpha(memoIconAlpha),
            )
            Icon(
                imageVector = Icons.Default.ArrowDropUp,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.alpha(1 - memoIconAlpha),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShoppingItemRowPreview() {
    val item = ShoppingItemPreview.getSample()
    ShoppingMemoAppTheme {
        ShoppingItemRowContent(item, isExpanded = false)
    }
}

@Preview(showBackground = true)
@Composable
private fun ShoppingItemRowCheckBoxInvisiblePreview() {
    val item = ShoppingItemPreview.getSample()
    ShoppingMemoAppTheme {
        ShoppingItemRowContent(item, isExpanded = false, checkBoxIsVisible = false)
    }
}

@Preview(showBackground = true)
@Composable
private fun ShoppingItemRowExpandedPreview() {
    val item = ShoppingItemPreview.getSample()
    ShoppingMemoAppTheme {
        ShoppingItemRowContent(item, isExpanded = true)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ShoppingItemRowExpandedDarkPreview() {
    val item = ShoppingItemPreview.getSample()
    ShoppingMemoAppTheme {
        ShoppingItemRowContent(item, isExpanded = true)
    }
}
