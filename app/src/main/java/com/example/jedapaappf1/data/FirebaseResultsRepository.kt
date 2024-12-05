package com.example.jedapaappf1.data

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class FirebaseResultsRepository : ResultsRepository {
    val db = Firebase.firestore
    override suspend fun getDrivers(callback: (List<DriverResult>) -> Unit) {
        coroutineScope {
            val result = db.collection("driversResults").get().await()
            val items = result.documents.mapNotNull { doc ->
                async {
                    val id = doc.id
                    val driver = doc.getString("Driver") ?: "Unknown"
                    val points = doc.getLong("Points")?.toInt() ?: 0
                    val nationality = doc.getString("Nationality") ?: "Unknown"
                    try {
                        DriverResult(
                            id = id,
                            driver = driver,
                            points = points,
                            nationality = nationality
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