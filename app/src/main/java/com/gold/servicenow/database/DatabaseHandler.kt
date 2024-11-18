package com.gold.servicenow.database

import com.gold.servicenow.DataGenerator
import com.google.firebase.firestore.FirebaseFirestore

class DatabaseHandler {


    companion object {
        private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
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

        const val ENTERTAINMENT_ID = "entertainment_id"
        const val ENTERTAINMENT_NAME = "entertainment_name"
        const val ENTERTAINMENT_IMAGE = "entertainment_image"
        const val ENTERTAINMENT_DESCRIPTION = "entertainment_description"
        const val ENTERTAINMENT_PRICE = "entertainment_price"
        const val ENTERTAINMENT_LOCATION = "entertainment_location"
        const val ENTERTAINMENT_CONTACT = "entertainment_contact"
        const val ENTERTAINMENT_DETAIL1 = "entertainment_detail1"
        const val ENTERTAINMENT_DETAIL2 = "entertainment_detail2"

        const val FOOD_ID = "food_id"
        const val FOOD_NAME = "food_name"
        const val FOOD_IMAGE = "food_image"
        const val FOOD_DESCRIPTION = "food_description"
        const val FOOD_PRICE = "food_price"
        const val FOOD_RESTAURANT = "food_restaurant"
        const val FOOD_DETAIL1 = "food_detail1"
        const val FOOD_DETAIL2 = "food_detail2"

        const val MEDICINE_ID = "medicine_id"
        const val MEDICINE_NAME = "medicine_name"
        const val MEDICINE_IMAGE = "medicine_image"
        const val MEDICINE_DESCRIPTION = "medicine_description"
        const val MEDICINE_PRICE = "medicine_price"
        const val MEDICINE_DOSAGE = "medicine_dosage"
        const val MEDICINE_SIDE_EFFECTS = "medicine_side_effects"
        const val MEDICINE_DETAIL1 = "medicine_detail1"
        const val MEDICINE_DETAIL2 = "medicine_detail2"
    }

    // Add a new food document
    fun addFood(data: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        firestore.collection(FOOD_COLLECTION)
            .add(data)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    // Get all food documents
    fun getFood(onSuccess: (List<Map<String, Any>>) -> Unit, onFailure: (Exception) -> Unit) {
        firestore.collection(FOOD_COLLECTION)
            .get()
            .addOnSuccessListener { result ->
                val dataList = result.documents.map { it.data!! }
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
    fun getMedicine(onSuccess: (List<Map<String, Any>>) -> Unit, onFailure: (Exception) -> Unit) {
        firestore.collection(MEDICINE_COLLECTION)
            .get()
            .addOnSuccessListener { result ->
                val dataList = result.documents.map { it.data!! }
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
    fun getEntertainment(onSuccess: (List<Map<String, Any>>) -> Unit, onFailure: (Exception) -> Unit) {
        firestore.collection(ENTERTAINMENT_COLLECTION)
            .get()
            .addOnSuccessListener { result ->
                val dataList = result.documents.map { it.data!! }
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
                "image" to food.imageId,
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
                "name" to medicine.name,
                "image" to medicine.imageId,
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
                "name" to entertainment.name,
                "image" to entertainment.imageId,
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
