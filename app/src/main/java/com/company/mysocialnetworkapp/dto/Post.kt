package com.company.mysocialnetworkapp.dto

data class Post(
    val id: Long,
    val author: String,
    val authorId: String,
    val authorAvatar: String? = null,
    val authorJob: String? = null,
    val content: String,
    val published: String,
    val coords: Coordinates? = null,
    val link: String? = null,
    val likeOwnerIds: List<Int> = emptyList(),
    val mentionIds: List<Int> = emptyList(),
    val likedByMe: Boolean,
    val attachment: Attachment? = null,
    val ownedByMe: Boolean,
    val users: UserPreview
)

data class Coordinates(
    val lat: String,
    val long: String
)

data class Attachment(
    val url: String,
    val type: AttachmentType,
)

enum class AttachmentType {
    IMAGE,
    VIDEO,
    AUDIO
}

data class UserPreview(
    val name: String,
    val avatar: String? = null
)
