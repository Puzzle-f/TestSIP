package com.example.testsip.repository.usersrepo

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.testsip.data.Users
import com.example.testsip.data.UsersItem
import com.google.gson.Gson
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class UserApiService {
    suspend fun requestUsers(context: Context): String {
        val queue = Volley.newRequestQueue(context)
        val result = suspendCancellableCoroutine<String> { continuation ->
            val request = StringRequest(
                Request.Method.GET,
                "https://jsonplaceholder.typicode.com/users",
                { result ->
                    continuation.resume(result)
                },
                { error ->
                    continuation.resumeWithException(error)
                }
            )
            queue.add(request)
        }
        return result
    }
}

class UserParser {
    fun parseUsers(json: String): List<UsersItem> {
        return Gson().fromJson(json, Users::class.java)
    }
}

class UserDataRepository {
    private val dataSourceUser = UserApiService()

    suspend fun getUsers(context: Context): List<UsersItem> {
        val json = dataSourceUser.requestUsers(context)
        return UserParser().parseUsers(json)
    }
}