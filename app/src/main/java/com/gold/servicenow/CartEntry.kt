package com.gold.servicenow

class CartEntry {
    var name: String = ""
    var price: Float = 0.0f
    var quantity: Int = 0
    var imageId: Int = 0

    constructor(name: String, price: Float, quantity: Int, imageId: Int) {
        this.name = name
        this.price = price
        this.quantity = quantity
        this.imageId = imageId
    }
}


object CartList {
    val cartList: ArrayList<CartEntry> = ArrayList()

    fun addCartEntry(cartEntry: CartEntry) {
        // check if the item is already in the cart
        for (entry in this.cartList) {
            if (entry.name == cartEntry.name) {
                entry.quantity += cartEntry.quantity
                return
            }
        }
        this.cartList.add(cartEntry)
    }

    fun removeCartEntry(cartEntry: CartEntry) {
        this.cartList.remove(cartEntry)
    }

    fun clearCart() {
        this.cartList.clear()
    }

    fun getCartTotal(): Double {
        var total: Double = 0.0
        for (cartEntry in this.cartList) {
            total += cartEntry.price * cartEntry.quantity
        }
        return total
    }
}