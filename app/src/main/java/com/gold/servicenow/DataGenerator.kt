package com.gold.servicenow

class DataGenerator {
    companion object {
        private val foodname1: FoodName = FoodName(R.drawable.food1, "Chicken Adobo", 50.0f, "Angrydobo", "Soy Sauce, Vinegar, Garlic, Bay Leaves, Pepper")
        private val foodname2: FoodName = FoodName(R.drawable.food2, "Pancit Malabon", 60.0f, "Pancit Malabon Express", "Shrimp, Pork, Vegetables, Rice Noodles")
        private val foodname3: FoodName = FoodName(R.drawable.food3, "Pancit Bihon", 40.0f, "Little Quaipo", "Pork, Vegetables, Rice Noodles")
        private val foodname4: FoodName = FoodName(R.drawable.food4, "Lechon Kawali", 70.0f, "Mang Inasal", "Pork Belly, Salt, Pepper, Oil")
        private val foodname5: FoodName = FoodName(R.drawable.food5, "Sinigang na Baboy", 80.0f, "Max's Restaurant", "Pork, Tamarind, Vegetables")
        private val foodname6: FoodName = FoodName(R.drawable.food6, "Kare-Kare", 90.0f, "Aristocrat", "Oxtail, Tripe, Vegetables, Peanut Sauce")
        private val foodname7: FoodName = FoodName(R.drawable.food7, "Bicol Express", 60.0f, "Mesa", "Pork, Coconut Milk, Chili Peppers")

        fun getFoodName(): List<FoodName> {
            return arrayListOf(foodname1, foodname2, foodname3, foodname4, foodname5, foodname6, foodname7)
        }

        private val medicine1: Medicine = Medicine(R.drawable.medicine1, "Paracetamol", 10.0f, "Fever, Pain Reliever", "1-2 tablets every 4-6 hours", "Nausea, Allergic Reactions")
        private val medicine2: Medicine = Medicine(R.drawable.medicine2, "Loperamide", 20.0f, "Diarrhea", "2 tablets after the first loose stool", "Constipation, Dizziness")
        private val medicine3: Medicine = Medicine(R.drawable.medicine3, "Cetirizine", 30.0f, "Allergies", "1 tablet daily", "Drowsiness, Dry Mouth")
        private val medicine4: Medicine = Medicine(R.drawable.medicine4, "Omeprazole", 40.0f, "Acid Reflux", "1 tablet daily", "Headache, Diarrhea")
        private val medicine5: Medicine = Medicine(R.drawable.medicine5, "Simvastatin", 50.0f, "High Cholesterol", "1 tablet daily", "Muscle Pain, Liver Damage")
        private val medicine6: Medicine = Medicine(R.drawable.medicine6, "Metformin", 60.0f, "Type 2 Diabetes", "1 tablet daily", "Nausea, Diarrhea")

        fun getMedicine(): List<Medicine> {
            return arrayListOf(medicine1, medicine2, medicine3, medicine4, medicine5, medicine6)
        }

        private val leisure1: Leisure = Leisure(R.drawable.leisure1, "Spa", 100.0f, "Relaxing Massage", "Makati", "09123456789")
        private val leisure2: Leisure = Leisure(R.drawable.leisure2, "Gym", 200.0f, "Workout", "Quezon City", "09123456789")
        private val leisure3: Leisure = Leisure(R.drawable.leisure3, "Swimming Pool", 150.0f, "Swimming", "Pasig", "09123456789")
        private val leisure4: Leisure = Leisure(R.drawable.leisure4, "Sauna", 250.0f, "Steam Bath", "Taguig", "09123456789")
        private val leisure5: Leisure = Leisure(R.drawable.leisure5, "Yoga", 300.0f, "Meditation", "Mandaluyong", "09123456789")
        private val leisure6: Leisure = Leisure(R.drawable.leisure6, "Karaoke", 50.0f, "Singing", "Manila", "09123456789")

        fun getLeisure(): List<Leisure> {
            return arrayListOf(leisure1, leisure2, leisure3, leisure4, leisure5, leisure6)
        }

    }
}