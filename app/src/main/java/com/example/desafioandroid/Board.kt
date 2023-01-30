package com.example.desafioandroid

class Boards : ArrayList<BoardItem>()

data class BoardItem(
    val id: String,
    val name: String,
    val desc: String,
    val descData: Any,
    val closed: Boolean,
    val creationMethod: Any,
    val dateClosed: Any,
    val dateLastActivity: String,
    val dateLastView: String,
    val datePluginDisable: Any,
    val enterpriseOwned: Boolean,
    val idBoardSource: Any,
    val idEnterprise: Any,
    val idMemberCreator: String,
    val idOrganization: String,
    val idTags: List<Any>,
    val ixUpdate: String,
    val nodeId: String,
    val pinned: Boolean,
    val powerUps: List<String>,
    val premiumFeatures: List<String>,
    val shortLink: String,
    val shortUrl: String,
    val starred: Boolean,
    val subscribed: Boolean,
    val templateGallery: Any,
    val url: String
)
