package com.company.mysocialnetworkapp.model

data class SignInModelState(
    val networkError: Boolean = false,
    val emptyFieldsError: Boolean = false,
    val loginOrPassError: Boolean = false,
    val unknownError: Boolean = false
)