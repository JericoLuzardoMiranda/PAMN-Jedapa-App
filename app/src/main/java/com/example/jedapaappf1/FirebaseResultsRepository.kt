package com.example.jedapaappf1

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class FirebaseResultsRepository : ResultsRepository {
    val db = Firebase.firestore
    //val driversRef = db.collection("driversResults")
    override suspend fun getDrivers(callback: (List<DriverResult>) -> Unit) {

        ///TEST///
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
            for (item in items){
                Log.d("driver", item.driver)
            }
            callback(items)
        }


    /*
    // Crear una lista mutable para almacenar los datos
        val driversList = mutableListOf<DriverResult>()

    // Leer datos desde Firebase y almacenarlos en la lista
        driversRef.get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    // Crear un objeto Driver con los datos
                    val driver = DriverResult(
                        id = document.id,
                        driver = document.getString("Driver") ?: "Unknown",
                        points = document.getLong("Points")?.toInt() ?: 0,
                        nationality = document.getString("Nationality") ?: "Unknown"
                    )
                    // Agregar el driver a la lista
                    driversList.add(driver)
                }

                // Puedes ahora usar driversList más adelante en el código
                Log.d(TAG, "Drivers list loaded successfully: $driversList")
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

        return driversList


     */
        //////////






    }
}