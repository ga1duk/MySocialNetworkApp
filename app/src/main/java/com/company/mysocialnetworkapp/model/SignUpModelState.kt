package com.company.mysocialnetworkapp.model

data class SignUpModelState(
    val networkError: Boolean = false,
    val emptyFieldsError: Boolean = false,
    val loginOrPassError: Boolean = false,
    val unknownError: Boolean = false,
    val internalServerError: Boolean = false,
    val passwordsNotMatchError: Boolean = false
)
