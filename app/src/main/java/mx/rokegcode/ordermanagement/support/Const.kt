package mx.rokegcode.ordermanagement.support

import android.net.Uri

//Database
const val DATABASE_VERSION = 1
const val DATABASE_NAME = "order_db"

//Provider
const val CONTENT_AUTHORITY = "mx.rokegcode.ordermanagement.provider"
val BASE_CONTENT_URI: Uri = Uri.parse("content://$CONTENT_AUTHORITY")
const val PATH_ORDER = "mail"
const val PATH_APPEND_ID = "/#"

//Notifications
const val CHANNEL_ID = "ORDER_MANAGEMENT_NOTIFICATION"