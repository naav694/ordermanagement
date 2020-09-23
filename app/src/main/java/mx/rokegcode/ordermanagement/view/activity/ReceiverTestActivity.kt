package mx.rokegcode.ordermanagement.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_receiver_test.*
import mx.rokegcode.ordermanagement.R

class ReceiverTestActivity : AppCompatActivity() {

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receiver_test)

        btnReceiverTest.setOnClickListener {
            val intent = Intent()
            intent.action = "mx.rokegcode.ordermanagement.receiver.notification"
            intent.putExtra("title", "Amorcito Corazón")
            intent.putExtra("content", "¡Time is about to over for this order!")
            intent.putExtra("notification_id", count++)
            intent.putExtra("big_content", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like)")
            sendBroadcast(intent)
        }
    }
}