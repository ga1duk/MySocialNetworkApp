package com.company.mysocialnetworkapp.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.company.mysocialnetworkapp.dto.Attachment
import com.company.mysocialnetworkapp.dto.AttachmentType
import com.company.mysocialnetworkapp.dto.Post

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val authorId: String,
    val authorAvatar: String? = null,
    val authorJob: String? = null,
    val content: String,
    val published: String,
//    val coords: Coordinates? = null,
    val link: String? = null,
//    val likeOwnerIds: List<Int> = emptyList(),
//    val mentionIds: List<Int> = emptyList(),
    val likedByMe: Boolean,
    @Embedded
    val attachment: AttachmentEmbeddable? = null,
    val ownedByMe: Boolean,
//    val users: UserPreview
) {
    fun toDto() = Post(
        id,
        author,
        authorId,
        authorAvatar,
        authorJob,
        content,
        published,
//        coords,
        link,
//        likeOwnerIds,
//        mentionIds,
        likedByMe,
        attachment?.toDto(),
        ownedByMe
    )

    companion object {
        fun fromDto(dto: Post) =
            PostEntity(
                dto.id, dto.author, dto.authorId, dto.authorAvatar, dto.authorJob, dto.content,
                dto.published, /*dto.coords,*/ dto.link, /*dto.likeOwnerIds, dto.mentionIds,*/
                dto.likedByMe, AttachmentEmbeddable.fromDto(dto.attachment), dto.ownedByMe
            )
    }
}

data class AttachmentEmbeddable(
    var url: String,
    var type: AttachmentType
) {
    fun toDto() = Attachment(url, type)

    companion object {
        fun fromDto(dto: Attachment?) = dto?.let {
            AttachmentEmbeddable(it.url, it.type)
        }
    }
}

fun List<PostEntity>.toDto(): List<Post> = map(PostEntity::toDto)
fun List<Post>.toEntity(): List<PostEntity> = map(PostEntity::fromDto)