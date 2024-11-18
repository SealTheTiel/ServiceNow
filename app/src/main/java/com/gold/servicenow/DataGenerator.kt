package com.gold.servicenow

import com.gold.servicenow.entertainment.Entertainment
import com.gold.servicenow.food.Food
import com.gold.servicenow.medicine.Medicine

class DataGenerator {
    companion object {
        private val foodname1: Food = Food(
            1,
            "https://i.imgur.com/lRL2qj6.jpeg",
            "Chicken Adobo",
            50.0f,
            "Angrydobo",
            "Soy Sauce, Vinegar, Garlic, Bay Leaves, Pepper",
            "Restaurant",
            "Ingredients"
        )
        private val foodname2: Food = Food(
            2,
            "https://i.imgur.com/uVXzA1Z.jpeg",
            "Pancit Malabon",
            60.0f,
            "Pancit Malabon Express",
            "Shrimp, Pork, Vegetables, Rice Noodles",
            "Restaurant",
            "Ingredients"
        )
        private val foodname3: Food = Food(
            3,
            "https://i.imgur.com/K53ZLHY.jpeg",
            "Pancit Bihon",
            40.0f,
            "Little Quaipo",
            "Pork, Vegetables, Rice Noodles",
            "Restaurant",
            "Ingredients"
        )
        private val foodname4: Food = Food(
            4,
            "https://i.imgur.com/lwUKFNt.jpeg",
            "Lechon Kawali",
            70.0f,
            "Mang Inasal",
            "Pork Belly, Salt, Pepper, Oil",
            "Restaurant",
            "Ingredients"
        )
        private val foodname5: Food = Food(
            5,
            "https://i.imgur.com/DqDEFWJ.jpeg",
            "Sinigang na Baboy",
            80.0f,
            "Max's Restaurant",
            "Pork, Tamarind, Vegetables",
            "Restaurant",
            "Ingredients"
        )
        private val foodname6: Food = Food(
            6,
            "https://i.imgur.com/tZxIKPW.jpeg",
            "Kare-Kare",
            90.0f,
            "Aristocrat",
            "Oxtail, Tripe, Vegetables, Peanut Sauce",
            "Restaurant",
            "Ingredients"
        )
        private val foodname7: Food = Food(
            7,
           "https://i.imgur.com/lq2N6kB.jpeg",
            "Bicol Express",
            60.0f,
            "Mesa",
            "Pork, Coconut Milk, Chili Peppers",
            "Restaurant",
            "Ingredients"
        )

        fun getFoodName(): ArrayList<Food> {
            return arrayListOf(foodname1, foodname2, foodname3, foodname4, foodname5, foodname6, foodname7)
        }

        private val medicine1: Medicine = Medicine(
            1,
            "https://i.imgur.com/9Pt5J7q.png",
            "Paracetamol",
            10.0f,
            "Fever, Pain Reliever",
            "Dosage",
            "1-2 tablets every 4-6 hours",
            "Side Effects",
            "Nausea, Allergic Reactions"
        )
        private val medicine2: Medicine = Medicine(
            2,
            "https://i.imgur.com/qgAdW9w.jpeg",
            "Loperamide",
            20.0f,
            "Diarrhea",
            "Dosage",
            "2 tablets after first loose stool, 1 tablet after each loose stool",
            "Side Effects",
            "Constipation, Dizziness"
        )
        private val medicine3: Medicine = Medicine(
            3,
            "https://i.imgur.com/pDf3dGH.jpeg",
            "Cetirizine",
            30.0f,
            "Allergies",
            "Dosage",
            "1 tablet daily",
            "Side Effects",
            "Drowsiness, Dry Mouth"
        )
        private val medicine4: Medicine = Medicine(
            4,
            "https://i.imgur.com/UrCc2TA.png",
            "Omeprazole",
            40.0f,
            "Acid Reflux",
            "Dosage",
            "1 tablet daily",
            "Side Effects",
            "Headache, Diarrhea"
        )
        private val medicine5: Medicine = Medicine(
            5,
            "https://i.imgur.com/2JL6TfZ.png",
            "Simvastatin",
            50.0f,
            "High Cholesterol",
            "Dosage",
            "1 tablet daily",
            "Side Effects",
            "Muscle Pain, Liver Damage"
        )
        private val medicine6: Medicine = Medicine(
            6,
            "https://i.imgur.com/irpUbOo.jpeg",
            "Metformin",
            60.0f,
            "Type 2 Diabetes",
            "Dosage",
            "1 tablet daily",
            "Side Effects",
            "Nausea, Diarrhea"
        )

        fun getMedicine(): ArrayList<Medicine> {
            return arrayListOf(medicine1, medicine2, medicine3, medicine4, medicine5, medicine6)
        }

        private val entertainment1: Entertainment = Entertainment(
            1,
            "https://i.imgur.com/PdzwEz8.jpeg",
            "Spa",
            100.0f,
            "Relaxing Massage",
            "Makati",
            "09123456789",
            "Location",
            "Contact"
        )
        private val entertainment2: Entertainment = Entertainment(
            2,
            "https://i.imgur.com/6JxyEZu.jpeg",
            "Gym",
            200.0f,
            "Workout",
            "Quezon City",
            "09123456789",
            "Location",
            "Contact"
        )
        private val entertainment3: Entertainment = Entertainment(
            3,
            "https://i.imgur.com/I4186Jk.jpeg",
            "Swimming Pool",
            150.0f,
            "Swimming",
            "Pasig",
            "09123456789",
            "Location",
            "Contact"
        )
        private val entertainment4: Entertainment = Entertainment(
            4,
            "https://i.imgur.com/5puq68J.jpeg",
            "Sauna",
            250.0f,
            "Steam Bath",
            "Taguig",
            "09123456789",
            "Location",
            "Contact"
        )
        private val entertainment5: Entertainment = Entertainment(
            5,
            "https://i.imgur.com/q7meECV.jpeg",
            "Yoga",
            300.0f,
            "Meditation",
            "Mandaluyong",
            "09123456789",
            "Location",
            "Contact"
        )
        private val entertainment6: Entertainment = Entertainment(
            6,
            "https://i.imgur.com/rLiiP5i.jpeg",
            "Karaoke",
            50.0f,
            "Singing",
            "Manila",
            "09123456789",
            "Location",
            "Contact"
        )

        fun getLeisure(): ArrayList<Entertainment> {
            return arrayListOf(entertainment1, entertainment2, entertainment3, entertainment4, entertainment5, entertainment6)
        }

    }
}