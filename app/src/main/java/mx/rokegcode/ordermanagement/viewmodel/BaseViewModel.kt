package mx.rokegcode.ordermanagement.viewmodel

import androidx.lifecycle.ViewModel
import mx.rokegcode.ordermanagement.util.interfaces.ISessionHelper

open class BaseViewModel(val sessionHelper: ISessionHelper): ViewModel() {
}