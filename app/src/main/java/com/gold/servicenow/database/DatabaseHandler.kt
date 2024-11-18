package com.gold.servicenow.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler (context: Context) : SQLiteOpenHelper (context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "ServiceNowDatabase"
        private const val ENTERTAINMENT_TABLE = "entertainment_table"
        private const val FOOD_TABLE = "food_table"
        private const val MEDICINE_TABLE = "medicine_table"

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

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_ENTERTAINMENT_TABLE = (  "CREATE TABLE $ENTERTAINMENT_TABLE (" +
                                            "$ENTERTAINMENT_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                            "$ENTERTAINMENT_NAME TEXT, " +
                                            "$ENTERTAINMENT_IMAGE INTEGER, " +
                                            "$ENTERTAINMENT_DESCRIPTION TEXT, " +
                                            "$ENTERTAINMENT_PRICE REAL, " +
                                            "$ENTERTAINMENT_LOCATION TEXT, " +
                                            "$ENTERTAINMENT_CONTACT TEXT, " +
                                            "$ENTERTAINMENT_DETAIL1 TEXT, " +
                                            "$ENTERTAINMENT_DETAIL2 TEXT)")

        val CREATE_FOOD_TABLE = (           "CREATE TABLE $FOOD_TABLE (" +
                                            "$FOOD_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                            "$FOOD_NAME TEXT, " +
                                            "$FOOD_DESCRIPTION TEXT, " +
                                            "$FOOD_RESTAURANT TEXT, " +
                                            "$FOOD_PRICE REAL, " +
                                            "$FOOD_IMAGE INTEGER, " +
                                            "$FOOD_DETAIL1 TEXT, " +
                                            "$FOOD_DETAIL2 TEXT)")

        val CREATE_MEDICINE_TABLE = (       "CREATE TABLE $MEDICINE_TABLE (" +
                                            "$MEDICINE_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                            "$MEDICINE_NAME TEXT, " +
                                            "$MEDICINE_DESCRIPTION TEXT, " +
                                            "$MEDICINE_PRICE REAL, " +
                                            "$MEDICINE_IMAGE INTEGER, " +
                                            "$MEDICINE_DOSAGE TEXT, " +
                                            "$MEDICINE_SIDE_EFFECTS TEXT, " +
                                            "$MEDICINE_DETAIL1 TEXT, " +
                                            "$MEDICINE_DETAIL2 TEXT)")

        db?.execSQL(CREATE_ENTERTAINMENT_TABLE)
        db?.execSQL(CREATE_FOOD_TABLE)
        db?.execSQL(CREATE_MEDICINE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $ENTERTAINMENT_TABLE")
        db!!.execSQL("DROP TABLE IF EXISTS $FOOD_TABLE")
        db!!.execSQL("DROP TABLE IF EXISTS $MEDICINE_TABLE")

        onCreate(db)
    }
}