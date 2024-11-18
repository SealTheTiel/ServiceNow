package com.gold.servicenow.medicine

class Medicine (id:Int, imageUrl: String, name: String, price: Float, description: String, detail1:String, dosage: String, detail2:String, sideEffects: String) {
    var id: Int = id
        private set
    var imageUrl = imageUrl
        private set

    var name = name
        private set

    var price = price
        private set

    var description = description
        private set

    var dosage = dosage
        private set

    var sideEffects = sideEffects
        private set

    var detail1 = detail1
        private set

    var detail2 = detail2
        private set


}