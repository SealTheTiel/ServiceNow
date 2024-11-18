package com.gold.servicenow.entertainment

class Entertainment (entertainment_id:Int, imageId: Int, name: String, price: Float, description: String, location: String, contact: String, detail1:String, detail2: String) {
    var entertainment_id: Int = 0
        private set
    var imageId = imageId
        private set

    var name = name
        private set

    var price = price
        private set

    var description = description
        private set

    var location = location
        private set

    var contact = contact
        private set

    var detail1 = detail1
        private set

    var detail2 = detail2
        private set
}