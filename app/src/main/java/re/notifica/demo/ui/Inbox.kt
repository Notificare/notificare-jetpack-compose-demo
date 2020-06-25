package re.notifica.demo.ui

import android.app.Activity
import androidx.compose.Composable
import androidx.lifecycle.LiveData
import androidx.ui.core.Alignment
import androidx.ui.core.ContentScale
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.foundation.lazy.LazyColumnItems
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.ColorFilter
import androidx.ui.graphics.asImageAsset
import androidx.ui.layout.*
import androidx.ui.layout.RowScope.gravity
import androidx.ui.livedata.observeAsState
import androidx.ui.material.Divider
import androidx.ui.material.EmphasisAmbient
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ProvideEmphasis
import androidx.ui.res.vectorResource
import androidx.ui.text.font.FontWeight
import androidx.ui.text.style.TextOverflow
import androidx.ui.unit.dp
import re.notifica.Notificare
import re.notifica.demo.R
import re.notifica.demo.extensions.asTimeAgo
import re.notifica.demo.extensions.color
import re.notifica.demo.utils.UiState
import re.notifica.demo.utils.loadImageUrl
import re.notifica.model.NotificareInboxItem
import java.util.*

@Composable
fun Inbox(liveData: LiveData<SortedSet<NotificareInboxItem>>) {
    val state = liveData.observeAsState()
    val inboxItems = state.value

    when {
        inboxItems?.isEmpty() == true -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalGravity = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "No messages found",
                    style = MaterialTheme.typography.caption
                )
            }
        }
        inboxItems?.isNotEmpty() == true -> {
            LazyColumnItems(items = inboxItems.toList()) {
                InboxItem(item = it)
                Divider()
            }
        }
    }
}

@Composable
private fun InboxItem(item: NotificareInboxItem) {
    val context = ContextAmbient.current

    Row(
        modifier = Modifier
            .clickable(onClick = {
                Notificare.shared().openInboxItem(context as Activity, item)
            })
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AttachmentSection(item)

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = item.title ?: "---",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )

            ProvideEmphasis(emphasis = EmphasisAmbient.current.medium) {
                Text(
                    text = item.message,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body2
                )
            }
        }

        Column(
            horizontalGravity = Alignment.End
        ) {
            ProvideEmphasis(emphasis = EmphasisAmbient.current.medium) {
                Text(
                    text = item.timestamp.asTimeAgo(),
                    style = MaterialTheme.typography.caption,
                    maxLines = 1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            if (!item.status) {
                Box(
                    modifier = Modifier.size(8.dp),
                    shape = CircleShape,
                    backgroundColor = Color(0, 122, 255)
                )
            }
        }
    }
}

@Composable
private fun AttachmentSection(item: NotificareInboxItem) {
    when (val state = item.attachment?.uri?.let { loadImageUrl(url = it) }) {
        null -> {
            Box(
                modifier = Modifier
                    .size(width = 4.dp, height = 48.dp)
                    .gravity(Alignment.CenterVertically),
                backgroundColor = item.color,
                shape = RoundedCornerShape(4.dp)
            )
        }
        is UiState.Loading -> {
            Box(
                modifier = Modifier.size(width = 80.dp, height = 60.dp),
                shape = RoundedCornerShape(4.dp),
                border = Border(2.dp, item.color)
            )
        }
        is UiState.Success -> {
            Box(
                modifier = Modifier.size(width = 80.dp, height = 60.dp),
                shape = RoundedCornerShape(4.dp),
                border = Border(2.dp, item.color)
            ) {
                Image(
                    asset = state.data.asImageAsset(),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
        is UiState.Error -> {
            Box(
                modifier = Modifier.size(width = 80.dp, height = 60.dp),
                shape = RoundedCornerShape(4.dp),
                border = Border(2.dp, item.color),
                gravity = Alignment.Center
            ) {
                Image(
                    asset = vectorResource(R.drawable.ic_baseline_error_24),
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.error)
                )
            }
        }
    }
}
