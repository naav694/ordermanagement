package mx.rokegcode.ordermanagement.util

import android.net.Uri

//Database
const val DATABASE_VERSION = 5
const val DATABASE_NAME = "order_db"

//Provider
const val CONTENT_AUTHORITY = "mx.rokegcode.ordermanagement.provider"
val BASE_CONTENT_URI: Uri = Uri.parse("content://$CONTENT_AUTHORITY")
const val PATH_ORDER = "mail"
const val PATH_APPEND_ID = "/#"

//Notifications
const val CHANNEL_ID = "ORDER_MANAGEMENT_NOTIFICATION"

//SESSION
const val SESSION_USER = "SESSION_USER"
const val REMEMBER_SESSION = "REMEMBER_SESSION"