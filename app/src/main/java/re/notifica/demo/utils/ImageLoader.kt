package re.notifica.demo.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.core.ContextAmbient
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

@Composable
fun loadImageUrl(url: String): UiState<Bitmap> {
    var bitmapState: UiState<Bitmap> by state<UiState<Bitmap>> { UiState.Loading }

    Glide.with(ContextAmbient.current)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState = UiState.Success(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })

    return bitmapState
}
