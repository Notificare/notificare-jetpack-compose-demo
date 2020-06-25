package re.notifica.demo.extensions

import java.time.Duration
import java.time.Period
import java.time.ZoneId
import java.util.*

fun Date.asTimeAgo(): String {
    val start = this.toInstant().atZone(ZoneId.systemDefault())
    val end = Calendar.getInstance().time.toInstant().atZone(ZoneId.systemDefault())

    val period = Period.between(start.toLocalDate(), end.toLocalDate())

    if (period.years >= 1) {
        return "${period.years}Y"
    }

    if (period.months >= 1) {
        return "${period.months}M"
    }

    if (period.days >= 1) {
        return "${period.days}D"
    }

    val duration = Duration.between(start, end)

    if (duration.toDays() >= 1) {
        return "${duration.toDays()}D"
    }

    if (duration.toHours() >= 1) {
        return "${duration.toHours()} hr"
    }

    if (duration.toMinutes() >= 1) {
        return "${duration.toMinutes()} min"
    }

    if (duration.seconds >= 3) {
        return "${duration.seconds} sec"
    }

    return "now"
}
