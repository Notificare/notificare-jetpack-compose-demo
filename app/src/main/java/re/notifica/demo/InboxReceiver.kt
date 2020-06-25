package re.notifica.demo

import re.notifica.Notificare
import re.notifica.app.DefaultIntentReceiver

class InboxReceiver: DefaultIntentReceiver() {

    override fun onReady() {
        super.onReady()

        if (Notificare.shared().isNotificationsEnabled) {
            Notificare.shared().enableNotifications()
        }

        if (Notificare.shared().isLocationUpdatesEnabled) {
            Notificare.shared().enableLocationUpdates()
        }
    }
}
