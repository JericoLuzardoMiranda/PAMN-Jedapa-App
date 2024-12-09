package com.example.jedapaappf1.data

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.getField
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await

class FirebaseCalendarRepository : CalendarRepository{
    val db = Firebase.firestore
    override suspend fun getCircuits(callback: (List<Circuit>) -> Unit) {
        coroutineScope {
            val result = db.collection("calendar").get().await()
            val items = result.documents.mapNotNull { doc ->
                async {
                    val id = doc.id
                    val name = doc.getString("name") ?: "Unknown"
                    val location = doc.getString("location") ?: "Unknown"
                    val schedule = doc.get("schedule") as? Map<String, String>?: emptyMap()
                    try {
                        Circuit(
                            id = id,
                            name = name,
                            location = location,
                            practice1 = schedule["practice1"] ?: "Unknown",
                            practice2 = schedule["practice2"] ?: "Unknown",
                            practice3 = schedule["practice3"] ?: "Unknown",
                            qualifying = schedule["qualifying"] ?: "Unknown",
                            race = schedule["race"] ?: "Unknown"
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