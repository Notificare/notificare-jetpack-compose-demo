package re.notifica.demo.extensions

import androidx.ui.graphics.Color
import re.notifica.model.NotificareInboxItem
import re.notifica.model.NotificareNotification

val NotificareInboxItem.color: Color
    get() {
        return when (type) {
            NotificareNotification.TYPE_NONE -> Color(244, 67, 54)
            NotificareNotification.TYPE_ALERT -> Color(103, 58, 183)
            NotificareNotification.TYPE_WEBVIEW -> Color(156, 39, 176)
            NotificareNotification.TYPE_URL -> Color(63, 81, 181)
            NotificareNotification.TYPE_URL_SCHEME -> Color(76, 175, 80)
            NotificareNotification.TYPE_IMAGE -> Color(255, 235, 59)
            NotificareNotification.TYPE_VIDEO -> Color(3, 169, 244)
            NotificareNotification.TYPE_MAP -> Color(255, 193, 7)
            NotificareNotification.TYPE_RATE -> Color(255, 152, 0)
            NotificareNotification.TYPE_FORM -> Color(255, 87, 34)
            NotificareNotification.TYPE_PASSBOOK -> Color(244, 67, 54)
            NotificareNotification.TYPE_STORE -> Color(233, 30, 99)
            else -> Color(35, 44, 42)
        }
    }
