package com.gold.servicenow.database

import com.gold.servicenow.DataGenerator
import com.gold.servicenow.entertainment.Entertainment
import com.gold.servicenow.food.Food
import com.gold.servicenow.medicine.Medicine
import com.google.firebase.firestore.FirebaseFirestore

class DatabaseHandler {


    companion object {
        val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        fun addDocument(
            collection: String,
            data: Map<String, Any>,
            onSuccess: () -> Unit,
            onFailure: (Exception) -> Unit
        ) {
            firestore.collection(collection)
                .add(data)
                .addOnSuccessListener {
                    onSuccess() // Callback when the document is added successfully
                }
                .addOnFailureListener { exception ->
                    onFailure(exception) // Callback for errors
                }
        }

        const val ENTERTAINMENT_COLLECTION = "entertainment"
        const val FOOD_COLLECTION = "food"
        const val MEDICINE_COLLECTION = "medicine"
        const val PROFILE_COLLECTION = "profile"
    }

    // Add a new food document
    fun addFood(data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        firestore.collection(FOOD_COLLECTION)
            .add(data)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    // Get all food documents
    fun getFood(onSuccess: (List<Food>) -> Unit, onFailure: (Exception) -> Unit) {
        firestore.collection(FOOD_COLLECTION)
            .get()
            .addOnSuccessListener { result ->
                val dataList = result.documents.mapNotNull { doc ->
                    try {
                        Food(
                            id = doc.get("id") as? Int ?: 0,
                            imageUrl = doc.get("image") as? String ?: "0",
                            name = doc.get("name") as? String ?: "",
                            price = (doc.get("price") as? Double)?.toFloat() ?: 0f,
                            restaurant = doc.get("restaurant") as? String ?: "",
                            description = doc.get("description") as? String ?: "",
                            detail1 = doc.get("detail1") as? String ?: "",
                            detail2 = doc.get("detail2") as? String ?: ""
                        )
                    } catch (e: Exception) {
                        null // Skip invalid documents
                    }
                }
                onSuccess(dataList)
            }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    // Update a specific food document
    fun updateFood(documentId: String, updatedData: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        firestore.collection(FOOD_COLLECTION)
            .document(documentId)
            .update(updatedData)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    // Delete a specific food document
    fun deleteFood(documentId: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        firestore.collection(FOOD_COLLECTION)
            .document(documentId)
            .delete()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    // Add a new medicine document
    fun addMedicine(data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        firestore.collection(MEDICINE_COLLECTION)
            .add(data)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    // Get all medicine documents
    fun getMedicine(onSuccess: (List<Medicine>) -> Unit, onFailure: (Exception) -> Unit) {
        firestore.collection(MEDICINE_COLLECTION)
            .get()
            .addOnSuccessListener { result ->
                val dataList = result.documents.mapNotNull { doc ->
                    try {
                        Medicine(
                            id = doc.get("id") as? Int ?: 0,
                            imageUrl = doc.get("image") as? String ?: "0",
                            name = doc.get("name") as? String ?: "",
                            price = (doc.get("price") as? Double)?.toFloat() ?: 0f,
                            description = doc.get("description") as? String ?: "",
                            dosage = doc.get("dosage") as? String ?: "",
                            sideEffects = doc.get("sideEffects") as? String ?: "",
                            detail1 = doc.get("detail1") as? String ?: "",
                            detail2 = doc.get("detail2") as? String ?: ""
                        )
                    } catch (e: Exception) {
                        null // Skip invalid documents
                    }
                }
                onSuccess(dataList)
            }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    // Update a specific medicine document
    fun updateMedicine(documentId: String, updatedData: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        firestore.collection(MEDICINE_COLLECTION)
            .document(documentId)
            .update(updatedData)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    // Delete a specific medicine document
    fun deleteMedicine(documentId: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        firestore.collection(MEDICINE_COLLECTION)
            .document(documentId)
            .delete()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    // Add a new entertainment document
    fun addEntertainment(data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        firestore.collection(ENTERTAINMENT_COLLECTION)
            .add(data)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    // Get all entertainment documents
    fun getEntertainment(onSuccess: (List<Entertainment>) -> Unit, onFailure: (Exception) -> Unit) {
        firestore.collection(ENTERTAINMENT_COLLECTION)
            .get()
            .addOnSuccessListener { result ->
                val dataList = result.documents.mapNotNull { doc ->
                    try {
                        Entertainment(
                            id = doc.get("id") as? Int ?: 0,
                            name = doc.get("name") as? String ?: "",
                            imageUrl = doc.get("image") as? String ?: "0",
                            description = doc.get("description") as? String ?: "",
                            price = (doc.get("price") as? Double)?.toFloat() ?: 0f,
                            location = doc.get("location") as? String ?: "",
                            contact = doc.get("contact") as? String ?: "",
                            detail1 = doc.get("detail1") as? String ?: "",
                            detail2 = doc.get("detail2") as? String ?: ""
                        )
                    } catch (e: Exception) {
                        null // Skip invalid documents
                    }
                }
                onSuccess(dataList)
            }
            .addOnFailureListener { exception -> onFailure(exception) }
    }


    // Update a specific entertainment document
    fun updateEntertainment(documentId: String, updatedData: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        firestore.collection(ENTERTAINMENT_COLLECTION)
            .document(documentId)
            .update(updatedData)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    // Delete a specific entertainment document
    fun deleteEntertainment(documentId: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        firestore.collection(ENTERTAINMENT_COLLECTION)
            .document(documentId)
            .delete()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }
    fun insertFoodData() {
        val foodList = DataGenerator.getFoodName()
        foodList.forEach { food ->
            val foodData = mapOf(
                "id" to food.id,
                "image" to food.imageUrl,
                "name" to food.name,
                "price" to food.price,
                "restaurant" to food.restaurant,
                "description" to food.description,
                "detail1" to food.detail1,
                "detail2" to food.detail2
            )
            DatabaseHandler.addDocument("food", foodData,
                onSuccess = { println("Food data added: ${food.name}") },
                onFailure = { e -> println("Failed to add food: $e") }
            )
        }
    }

    fun insertMedicineData() {
        val medicineList = DataGenerator.getMedicine()
        medicineList.forEach { medicine ->
            val medicineData = mapOf(
                "id" to medicine.id,
                "name" to medicine.name,
                "image" to medicine.imageUrl,
                "price" to medicine.price,
                "description" to medicine.description,
                "dosage" to medicine.dosage,
                "sideEffects" to medicine.sideEffects,
                "detail1" to medicine.detail1,
                "detail2" to medicine.detail2
            )
            DatabaseHandler.addDocument("medicine", medicineData,
                onSuccess = { println("Medicine data added: ${medicine.name}") },
                onFailure = { e -> println("Failed to add medicine: $e") }
            )
        }
    }

    fun insertEntertainmentData() {
        val entertainmentList = DataGenerator.getLeisure()
        entertainmentList.forEach { entertainment ->
            val entertainmentData = mapOf(
                "id" to entertainment.id,
                "name" to entertainment.name,
                "image" to entertainment.imageUrl,
                "price" to entertainment.price,
                "description" to entertainment.description,
                "location" to entertainment.location,
                "contact" to entertainment.contact,
                "detail1" to entertainment.detail1,
                "detail2" to entertainment.detail2
            )
            DatabaseHandler.addDocument("entertainment", entertainmentData,
                onSuccess = { println("Entertainment data added: ${entertainment.name}") },
                onFailure = { e -> println("Failed to add entertainment: $e") }
            )
        }
    }

}
