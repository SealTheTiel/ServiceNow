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
        private val foodname8: Food = Food(
            8,
            "",
            "Tinolang Manok",
            55.0f,
            "Home-Cooked Meals",
            "Chicken, Papaya, Ginger, Spinach",
            "Restaurant",
            "Ingredients"
        )
        private val foodname9: Food = Food(
            9,
            "",
            "Ginataang Gulay",
            50.0f,
            "Lutong Bahay",
            "Vegetables, Coconut Milk, Shrimp",
            "Restaurant",
            "Ingredients"
        )
        private val foodname10: Food = Food(
            10,
            "",
            "Pinakbet",
            45.0f,
            "Ilocos Cuisine",
            "Vegetables, Bagoong, Pork",
            "Restaurant",
            "Ingredients"
        )
        private val foodname11: Food = Food(
            11,
            "",
            "Bulalo",
            100.0f,
            "Tagaytay Eats",
            "Beef Shank, Corn, Vegetables",
            "Restaurant",
            "Ingredients"
        )
        private val foodname12: Food = Food(
            12,
            "",
            "Siomai",
            35.0f,
            "Dimsum House",
            "Ground Pork, Shrimp, Carrots",
            "Snack",
            "Ingredients"
        )
        private val foodname13: Food = Food(
            13,
            "",
            "Paksiw na Bangus",
            50.0f,
            "Lutong Isda",
            "Milkfish, Vinegar, Garlic, Onion",
            "Restaurant",
            "Ingredients"
        )
        private val foodname14: Food = Food(
            14,
            "",
            "Monggo Guisado",
            40.0f,
            "Healthy Filipino Meals",
            "Mung Beans, Pork, Spinach",
            "Restaurant",
            "Ingredients"
        )
        private val foodname15: Food = Food(
            15,
            "",
            "Nilagang Baka",
            90.0f,
            "Comfort Food",
            "Beef, Potatoes, Cabbage, Corn",
            "Restaurant",
            "Ingredients"
        )
        private val foodname16: Food = Food(
            16,
            "",
            "Lumpiang Sariwa",
            50.0f,
            "Filipino Favorites",
            "Vegetables, Wrapper, Peanut Sauce",
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
        private val medicine7: Medicine = Medicine(
            7,
            "",
            "Losartan",
            70.0f,
            "High Blood Pressure",
            "Dosage",
            "1 tablet daily",
            "Side Effects",
            "Dizziness, Fatigue"
        )
        private val medicine8: Medicine = Medicine(
            8,
            "",
            "Amlodipine",
            80.0f,
            "Hypertension",
            "Dosage",
            "1 tablet daily",
            "Side Effects",
            "Swelling, Dizziness"
        )
        private val medicine9: Medicine = Medicine(
            9,
            "",
            "Aspirin",
            90.0f,
            "Pain, Blood Thinner",
            "Dosage",
            "1 tablet every 4-6 hours as needed",
            "Side Effects",
            "Stomach Upset, Bleeding"
        )
        private val medicine10: Medicine = Medicine(
            10,
            "",
            "Captopril",
            100.0f,
            "High Blood Pressure",
            "Dosage",
            "1 tablet daily",
            "Side Effects",
            "Cough, Dizziness"
        )
        private val medicine11: Medicine = Medicine(
            11,
            "",
            "Metoprolol",
            110.0f,
            "Heart Problems",
            "Dosage",
            "1 tablet daily",
            "Side Effects",
            "Tiredness, Low Heart Rate"
        )
        private val medicine12: Medicine = Medicine(
            12,
            "",
            "Clopidogrel",
            120.0f,
            "Blood Clot Prevention",
            "Dosage",
            "1 tablet daily",
            "Side Effects",
            "Bruising, Bleeding"
        )
        private val medicine13: Medicine = Medicine(
            13,
            "",
            "Furosemide",
            130.0f,
            "Fluid Retention, High Blood Pressure",
            "Dosage",
            "1 tablet daily",
            "Side Effects",
            "Frequent Urination, Dizziness"
        )
        private val medicine14: Medicine = Medicine(
            14,
            "",
            "Allopurinol",
            140.0f,
            "Gout",
            "Dosage",
            "1 tablet daily",
            "Side Effects",
            "Rash, Nausea"
        )
        private val medicine15: Medicine = Medicine(
            15,
            "",
            "Hydrochlorothiazide",
            150.0f,
            "High Blood Pressure, Fluid Retention",
            "Dosage",
            "1 tablet daily",
            "Side Effects",
            "Low Potassium, Dizziness"
        )
        private val medicine16: Medicine = Medicine(
            16,
            "",
            "Atorvastatin",
            160.0f,
            "High Cholesterol",
            "Dosage",
            "1 tablet daily",
            "Side Effects",
            "Muscle Pain, Stomach Upset"
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
        private val entertainment7: Entertainment = Entertainment(
            7,
            "",
            "Movie Theater",
            120.0f,
            "Watching Movies",
            "Cebu City",
            "09123456789",
            "Location",
            "Contact"
        )
        private val entertainment8: Entertainment = Entertainment(
            8,
            "",
            "Bowling",
            180.0f,
            "Bowling Games",
            "Davao City",
            "09123456789",
            "Location",
            "Contact"
        )


        fun getLeisure(): ArrayList<Entertainment> {
            return arrayListOf(entertainment1, entertainment2, entertainment3, entertainment4, entertainment5, entertainment6)
        }

    }
}