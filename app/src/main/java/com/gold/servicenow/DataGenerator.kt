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
            "https://i.imgur.com/qAfCtD4.jpg",
            "Tinolang Manok",
            55.0f,
            "Home-Cooked Meals",
            "Chicken, Papaya, Ginger, Spinach",
            "Restaurant",
            "Ingredients"
        )
        private val foodname9: Food = Food(
            9,
            "https://i.imgur.com/LkIER09.jpg",
            "Ginataang Gulay",
            50.0f,
            "Lutong Bahay",
            "Vegetables, Coconut Milk, Shrimp",
            "Restaurant",
            "Ingredients"
        )
        private val foodname10: Food = Food(
            10,
            "https://i.imgur.com/EfpUl6o.jpg",
            "Pinakbet",
            45.0f,
            "Ilocos Cuisine",
            "Vegetables, Bagoong, Pork",
            "Restaurant",
            "Ingredients"
        )
        private val foodname11: Food = Food(
            11,
            "https://i.imgur.com/GA6H8WI.jpg",
            "Bulalo",
            100.0f,
            "Tagaytay Eats",
            "Beef Shank, Corn, Vegetables",
            "Restaurant",
            "Ingredients"
        )
        private val foodname12: Food = Food(
            12,
            "https://i.imgur.com/xOAdij2.jpg",
            "Siomai",
            35.0f,
            "Dimsum House",
            "Ground Pork, Shrimp, Carrots",
            "Snack",
            "Ingredients"
        )
        private val foodname13: Food = Food(
            13,
            "https://i.imgur.com/zTYjLyQ.jpg",
            "Paksiw na Bangus",
            50.0f,
            "Lutong Isda",
            "Milkfish, Vinegar, Garlic, Onion",
            "Restaurant",
            "Ingredients"
        )
        private val foodname14: Food = Food(
            14,
            "https://i.imgur.com/vT4w59J.jpg",
            "Monggo Guisado",
            40.0f,
            "Healthy Filipino Meals",
            "Mung Beans, Pork, Spinach",
            "Restaurant",
            "Ingredients"
        )
        private val foodname15: Food = Food(
            15,
            "https://i.imgur.com/2ASKKLE.jpg",
            "Nilagang Baka",
            90.0f,
            "Comfort Food",
            "Beef, Potatoes, Cabbage, Corn",
            "Restaurant",
            "Ingredients"
        )
        private val foodname16: Food = Food(
            16,
            "https://i.imgur.com/Hi033c3.jpg",
            "Lumpiang Sariwa",
            50.0f,
            "Filipino Favorites",
            "Vegetables, Wrapper, Peanut Sauce",
            "Restaurant",
            "Ingredients"
        )

        fun getFoodName(): ArrayList<Food> {
            return arrayListOf(foodname1, foodname2, foodname3, foodname4, foodname5, foodname6, foodname7, foodname8, foodname9, foodname10, foodname11, foodname12, foodname13, foodname14, foodname15, foodname16)
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
            "https://i.imgur.com/zDykw0Y.jpg",
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
            "https://i.imgur.com/jKDfrlM.jpg",
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
            "https://i.imgur.com/IJMhe8S.jpg",
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
            "https://i.imgur.com/WMflprc.jpg",
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
            "https://i.imgur.com/54drB7f.jpg",
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
            "https://i.imgur.com/C0typ1p.jpg",
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
            "https://i.imgur.com/mEmNxhJ.jpg",
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
            "https://i.imgur.com/mzgJqdB.jpg",
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
            "https://i.imgur.com/LIieo3k.jpg",
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
            "https://i.imgur.com/5bu0Jnu.jpg",
            "Atorvastatin",
            160.0f,
            "High Cholesterol",
            "Dosage",
            "1 tablet daily",
            "Side Effects",
            "Muscle Pain, Stomach Upset"
        )

        fun getMedicine(): ArrayList<Medicine> {
            return arrayListOf(medicine1, medicine2, medicine3, medicine4, medicine5, medicine6, medicine7, medicine8, medicine9, medicine10, medicine11, medicine12, medicine13, medicine14, medicine15, medicine16)
        }

        private val entertainment1: Entertainment = Entertainment(
            1,
            "https://i.imgur.com/PdzwEz8.jpeg",
            "Spa Coupon",
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
            "Gym Coupon",
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
            "Swimming Pool Coupon",
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
            "Sauna Coupon",
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
            "Yoga Coupon",
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
            "Karaoke Coupon",
            50.0f,
            "Singing",
            "Manila",
            "09123456789",
            "Location",
            "Contact"
        )
        private val entertainment7: Entertainment = Entertainment(
            7,
            "https://i.imgur.com/ij1t28B.jpg",
            "Movie Theater Coupon",
            120.0f,
            "Watching Movies",
            "Cebu City",
            "09123456789",
            "Location",
            "Contact"
        )
        private val entertainment8: Entertainment = Entertainment(
            8,
            "https://i.imgur.com/vTqvtX2.jpg",
            "Bowling Coupon",
            180.0f,
            "Bowling Games",
            "Davao City",
            "09123456789",
            "Location",
            "Contact"
        )
        private val entertainment9: Entertainment = Entertainment(
            9,
            "https://i.imgur.com/LJ5bk40.jpg",
            "Afternoon Tea Coupon",
            300.0f,
            "Enjoy Tea and Snacks",
            "Makati",
            "09123456789",
            "Location",
            "Contact"
        )

        private val entertainment10: Entertainment = Entertainment(
            10,
            "https://i.imgur.com/4lDJMaU.jpg",
            "Herbal Gardening Workshop Coupon",
            200.0f,
            "Learn to Grow Herbs",
            "Quezon City",
            "09123456789",
            "Location",
            "Contact"
        )

        private val entertainment11: Entertainment = Entertainment(
            11,
            "https://i.imgur.com/GeEr6Cd.jpg",
            "Guided Nature Walk Coupon",
            150.0f,
            "Relaxing Walk in the Park",
            "Tagaytay",
            "09123456789",
            "Location",
            "Contact"
        )

        private val entertainment12: Entertainment = Entertainment(
            12,
            "https://i.imgur.com/IyAOq4O.jpg",
            "Bingo Night Coupon",
            100.0f,
            "Play and Socialize",
            "Pasig",
            "09123456789",
            "Location",
            "Contact"
        )

        private val entertainment13: Entertainment = Entertainment(
            13,
            "https://i.imgur.com/m9kBUPq.jpg",
            "Classical Music Concert Coupon",
            500.0f,
            "Relaxing Live Orchestra",
            "Manila",
            "09123456789",
            "Location",
            "Contact"
        )

        private val entertainment14: Entertainment = Entertainment(
            14,
            "https://i.imgur.com/m298GWQ.jpg",
            "Light Exercise Class Coupon",
            250.0f,
            "Gentle Movements and Stretches",
            "Tacloban",
            "09123456789",
            "Location",
            "Contact"
        )

        private val entertainment15: Entertainment = Entertainment(
            15,
            "https://i.imgur.com/UOzXNgl.jpg",
            "Local History Tour Coupon",
            350.0f,
            "Explore Historical Landmarks",
            "Manila",
            "09123456789",
            "Location",
            "Contact"
        )

        private val entertainment16: Entertainment = Entertainment(
            16,
            "https://i.imgur.com/gPBNBxQ.jpg",
            "Handicrafts Workshop Coupon",
            300.0f,
            "Learn Knitting or Weaving",
            "Cebu City",
            "09123456789",
            "Location",
            "Contact"
        )

        fun getLeisure(): ArrayList<Entertainment> {
            return arrayListOf(entertainment1, entertainment2, entertainment3, entertainment4, entertainment5, entertainment6, entertainment7, entertainment8, entertainment9, entertainment10, entertainment11, entertainment12, entertainment13, entertainment14, entertainment15, entertainment16)
        }

    }
}