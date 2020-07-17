package mx.rokegcode.ordermanagement.receiver

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import mx.rokegcode.ordermanagement.R
import mx.rokegcode.ordermanagement.util.CHANNEL_ID
import mx.rokegcode.ordermanagement.view.activity.LoginActivity

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val logIn = Intent(context, LoginActivity::class.java)
        logIn.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

        val logInPending = PendingIntent.getActivity(context, 0, logIn, 0)

        if (context != null) {
            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.logo_company)
                .setContentTitle(intent?.extras?.getString("title"))
                .setContentText(intent?.extras?.getString("content"))
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(intent?.extras?.getString("big_content"))
                )
                .addAction(
                    android.R.drawable.btn_dialog,
                    context.getString(R.string.go_to_login),
                    logInPending
                )
                .setAutoCancel(true)

            with(NotificationManagerCompat.from(context)) {
                notify(intent?.extras?.getInt("notification_id")!!, builder.build())
            }
        }
    }

}