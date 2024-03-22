package com.example.testsip.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UsersItem(
    val id: Int,
    val name: String,
    val username: String,
    val phone: String,
    val email: String,
    val website: String,
    val address: Address,
    val company: Company
) : Parcelable