package re.notifica.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import re.notifica.Notificare
import re.notifica.demo.theme.InboxTheme
import re.notifica.demo.ui.Inbox

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            InboxTheme {
                Inbox(Notificare.shared().inboxManager.observableItems)
            }
        }

        if (!Notificare.shared().isNotificationsEnabled) {
            Notificare.shared().enableNotifications()
        }
    }
}
