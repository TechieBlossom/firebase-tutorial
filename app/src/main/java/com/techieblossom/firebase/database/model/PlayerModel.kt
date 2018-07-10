package com.techieblossom.firebase.database.model

class PlayerModel {

    var name: String? = null
    var country: String? = null
    override fun toString(): String {
        return "PlayerModel(name=$name, country=$country)"
    }
}
