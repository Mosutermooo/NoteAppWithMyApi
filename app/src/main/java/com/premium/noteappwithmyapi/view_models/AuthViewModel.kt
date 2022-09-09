package com.premium.noteappwithmyapi.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.premium.noteappwithmyapi.models.AuthRequest
import com.premium.noteappwithmyapi.models.AuthResponse
import com.premium.noteappwithmyapi.network.ApiInstance
import com.premium.noteappwithmyapi.network.SeasonManager
import com.premium.noteappwithmyapi.repos.AuthRepositoryImpl
import com.premium.noteappwithmyapi.repos.AuthRepositoryTestImpl
import com.premium.noteappwithmyapi.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    app: Application
) : AndroidViewModel(app) {

    private val seasonManager = SeasonManager(app)
    private val repository: AuthRepositoryImpl = AuthRepositoryImpl(ApiInstance.apiService)
    private val testRepository: AuthRepositoryTestImpl = AuthRepositoryTestImpl()
    val loginState : MutableStateFlow<Resource<AuthResponse>> = MutableStateFlow(Resource.Idle())
    val registerState : MutableStateFlow<Resource<AuthResponse>> = MutableStateFlow(Resource.Idle())

    fun loginUser(
        email_Username: String,
        password: String
    ) = viewModelScope.launch {
        var username: String? = null
        var email: String? = null
        if(email_Username == ""){
            loginState.emit(Resource.Error(null, "Email/Username field empty"))
            return@launch
        }else{
            if(email_Username.contains("@")){
                email = email_Username
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email_Username).matches()){
                    loginState.emit(Resource.Error(null, "Invalid email"))
                    return@launch
                }
            }
            username = email_Username
        }

        if(password == ""){
            loginState.emit(Resource.Error(null, "Password field empty"))
            return@launch
        }

        loginState.emit(Resource.Loading())

        val loginRequest = AuthRequest(
            email ?: "",
            username,
            password
        )
        val response = repository.login(loginRequest)
        if(response.isSuccessful){
            response.body()?.let {
                val token = it.token
                seasonManager.saveToken(token)
                loginState.emit(Resource.Success(it))
            }
        }else{
            val b = response.errorBody()
            when(response.code()){
                400 -> {
                    loginState.emit(Resource.Error(
                        null, "Username or password are too small"
                    ))
                }
                401 -> {
                    loginState.emit(Resource.Error(
                        null, "Invalid credentials"
                    ))
                }
                404 ->{
                    loginState.emit(Resource.Error(
                        null, "Password doesnt match with our password in the database, try again"
                    ))
                }
            }
        }

    }


    fun register(email: String, username: String, password: String) = viewModelScope.launch {
        if(email == ""){
            registerState.emit(Resource.Error(null, "Email field empty"))
            return@launch
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            loginState.emit(Resource.Error(null, "Invalid email"))
            return@launch
        }
        if(username == ""){
            registerState.emit(Resource.Error(null, "Username field empty"))
            return@launch
        }
        if(password == ""){
            registerState.emit(Resource.Error(null, "Password field empty"))
            return@launch
        }
        if(password.length < 4){
            registerState.emit(Resource.Error(null, "Password is too empty"))
            return@launch
        }

        val registerRequest = AuthRequest(
            email, username, password
        )

        val response = repository.register(registerRequest)
        if(response.isSuccessful){
            response.body()?.let {
                registerState.emit(Resource.Success(response.body()!!))
            }
        }else{
            registerState.emit(Resource.Error(null, "${response.code()}"))
        }


    }



}