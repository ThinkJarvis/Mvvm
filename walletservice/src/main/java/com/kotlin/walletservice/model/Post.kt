package  com.kotlin.walletservice.model


data class Post(var userId: String, var id: String, var title: String, var body: String) {

    fun toPosts() = Post(userId, id, title, body)
}