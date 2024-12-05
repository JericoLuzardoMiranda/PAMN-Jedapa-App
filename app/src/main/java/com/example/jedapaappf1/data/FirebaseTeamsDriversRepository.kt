package com.example.jedapaappf1.data

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await

class FirebaseTeamsDriversRepository : TeamsDriversRepository {
    val db = Firebase.firestore
    override suspend fun getDrivers(callback: (List<Driver>) -> Unit) {
        coroutineScope {
            val result = db.collection("drivers").get().await()
            val items = result.documents.mapNotNull { doc ->
                async {
                    val id = doc.id
                    val name = doc.getString("name") ?: "Unknown"
                    val description = doc.getString("description") ?: "Unknown"
                    val imageRef = doc.getString("image") ?: "Unknown"
                    try {
                        Driver(
                            id = id,
                            name = name,
                            description = description,
                            imageRef = imageRef
                        )
                    } catch (e: Exception) {
                        null
                    }
                }
            }.awaitAll().filterNotNull()
            callback(items)
        }
    }

    override suspend fun getTeams(callback: (List<Team>) -> Unit) {
        coroutineScope {
            val result = db.collection("teams").get().await()
            val items = result.documents.mapNotNull { doc ->
                async {
                    val id = doc.id
                    val name = doc.getString("name") ?: "Unknown"
                    val description = doc.getString("description") ?: "Unknown"
                    val imageRef = doc.getString("image") ?: "Unknown"
                    try {
                        Team(
                            id = id,
                            name = name,
                            description = description,
                            imageRef = imageRef
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