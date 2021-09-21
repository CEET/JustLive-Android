package com.sunnyweather.android.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sunnyweather.android.logic.Repository

class LoginViewModel : ViewModel() {
    data class LoginRequest(val username: String, val password: String)
    data class RegisterRequest(val username: String, val nickName: String, val password: String)

    private val loginLiveData = MutableLiveData<LoginRequest>()
    private val registerLiveData = MutableLiveData<RegisterRequest>()

    val loginResponseLiveDate = Transformations.switchMap(loginLiveData) {
            value -> Repository.login(value.username, value.password)
    }
    val registerResponseLiveDate = Transformations.switchMap(registerLiveData) {
            value -> Repository.register(value.username, value.nickName, value.password)
    }

    fun doLogin(username: String, password: String) {
        loginLiveData.value = LoginRequest(username, password)
    }

    fun doRegister(username: String, password: String, nickName: String) {
        registerLiveData.value = RegisterRequest(username, nickName, password)
    }
}