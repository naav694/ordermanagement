package mx.rokegcode.ordermanagement.viewmodel

import androidx.lifecycle.ViewModel
import mx.rokegcode.ordermanagement.support.interfaces.ISessionHelper

open class BaseViewModel(val sessionHelper: ISessionHelper): ViewModel() {
}