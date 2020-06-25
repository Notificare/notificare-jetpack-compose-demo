package re.notifica.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxSize
import re.notifica.Notificare
import re.notifica.demo.theme.InboxTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            InboxTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalGravity = Alignment.CenterHorizontally
                ) {
                    Text("Hello world")
                }
            }
        }

        if (!Notificare.shared().isNotificationsEnabled) {
            Notificare.shared().enableNotifications()
        }
    }
}
