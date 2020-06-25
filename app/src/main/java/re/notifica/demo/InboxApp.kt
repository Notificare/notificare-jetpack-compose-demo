package re.notifica.demo

import android.app.Application
import re.notifica.Notificare

class InboxApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Notificare.shared().launch(this)
        Notificare.shared().createDefaultChannel()
        Notificare.shared().intentReceiver = InboxReceiver::class.java
    }
}
