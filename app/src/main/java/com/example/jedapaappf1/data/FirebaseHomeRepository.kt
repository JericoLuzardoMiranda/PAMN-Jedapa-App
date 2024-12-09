package com.example.jedapaappf1.data

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await

class FirebaseHomeRepository : HomeRepository {
    val db = Firebase.firestore
    override suspend fun getNews(callback: (List<News>) -> Unit) {
        coroutineScope {
            val result = db.collection("news").get().await()
            val items = result.documents.mapNotNull { doc ->
                async {
                    val id = doc.id
                    val content = doc.get("content") as? Map<String, String>?: emptyMap()
                    val description = doc.getString("description") ?: "Unknown"
                    val imageRef1 = doc.getString("imageKotlin1") ?: "Unknown"
                    val imageRef2 = doc.getString("imageKotlin2") ?: "Unknown"
                    val title = doc.getString("title") ?: "Unknown"
                    val isMain = doc.getBoolean("isMain") ?: false
                    try {
                        News(
                            id = id,
                            newsTitle1 = content["newsTitle1"] ?: "Unknown",
                            newsTitle2 = content["newsTitle2"] ?: "Unknown",
                            paragraph1 = content["paragraph1"] ?: "Unknown",
                            paragraph2 = content["paragraph2"] ?: "Unknown",
                            paragraph3 = content["paragraph3"] ?: "Unknown",
                            paragraph4 = content["paragraph4"] ?: "Unknown",
                            paragraph5 = content["paragraph5"] ?: "Unknown",
                            description = description,
                            imageRef1 = imageRef1,
                            imageRef2 = imageRef2,
                            title = title,
                            isMain = isMain
                        )
                    } catch (e: Exception) {
                        null
                    }
                }
            }.awaitAll().filterNotNull()
            callback(items)
        }
    }
}