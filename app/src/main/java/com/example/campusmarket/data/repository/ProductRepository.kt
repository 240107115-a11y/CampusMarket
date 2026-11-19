package com.example.campusmarket.data.repository

import com.example.campusmarket.R
import com.example.campusmarket.data.model.Product
import com.example.campusmarket.data.model.ProductCategory

object ProductRepository {
    val sampleProducts = listOf(
        Product(
            id = 1,
            title = "Calculus Early Transcendentals",
            price = 45.0,
            category = ProductCategory.TEXTBOOKS,
            condition = "Like New",
            sellerName = "Amina K.",
            sellerContact = "amina.k@sdu.edu.kz",
            location = "Main Library Floor 2",
            description = "8th edition by James Stewart. Barely used with no highlights or markings. Includes online access code card.",
            imageRes = R.drawable.ic_textbook
        ),
        Product(
            id = 2,
            title = "TI-84 Plus Graphing Calculator",
            price = 55.0,
            category = ProductCategory.ELECTRONICS,
            condition = "Good",
            sellerName = "Daniyar M.",
            sellerContact = "daniyar.m@sdu.edu.kz",
            location = "Block C Cafeteria",
            description = "Reliable graphing calculator for Calculus and Linear Algebra. Comes with fresh batteries and slide case.",
            imageRes = R.drawable.ic_calculator
        ),
        Product(
            id = 3,
            title = "Dimmable LED Desk Lamp",
            price = 18.0,
            category = ProductCategory.DORM,
            condition = "Like New",
            sellerName = "Sulaimon Q.",
            sellerContact = "240107115@sdu.edu.kz",
            location = "Dormitory 2 Room 314",
            description = "Adjustable neck desk lamp with 3 color temperatures and built-in USB charging port for phone.",
            imageRes = R.drawable.ic_lamp
        ),
        Product(
            id = 4,
            title = "Compact Dorm Mini Fridge",
            price = 70.0,
            category = ProductCategory.DORM,
            condition = "Good",
            sellerName = "Eldar T.",
            sellerContact = "eldar.t@sdu.edu.kz",
            location = "Dormitory 1 Lobby",
            description = "Quiet 45L compact refrigerator with small freezer compartment. Cleaned and defrosted, ready for dorm room.",
            imageRes = R.drawable.ic_fridge
        ),
        Product(
            id = 5,
            title = "Sony Noise Cancelling Headphones",
            price = 65.0,
            category = ProductCategory.ELECTRONICS,
            condition = "Very Good",
            sellerName = "Aruzhan S.",
            sellerContact = "aruzhan.s@sdu.edu.kz",
            location = "Student Center",
            description = "Over-ear Bluetooth headphones with active noise cancellation. Great for library study sessions. 30h battery life.",
            imageRes = R.drawable.ic_headphones
        ),
        Product(
            id = 6,
            title = "Ergonomic Mesh Study Chair",
            price = 40.0,
            category = ProductCategory.DORM,
            condition = "Good",
            sellerName = "Madi B.",
            sellerContact = "madi.b@sdu.edu.kz",
            location = "Dormitory 2 Lounge",
            description = "Breathable mesh back chair with lumbar support and pneumatic height adjustment. Wheels glide smoothly.",
            imageRes = R.drawable.ic_chair
        ),
        Product(
            id = 7,
            title = "Waterproof Laptop Backpack 25L",
            price = 25.0,
            category = ProductCategory.SUPPLIES,
            condition = "New",
            sellerName = "Dias R.",
            sellerContact = "dias.r@sdu.edu.kz",
            location = "Block A Main Entrance",
            description = "Durable backpack with padded sleeve for up to 15.6 inch laptop, water bottle side pockets and anti-theft zipper.",
            imageRes = R.drawable.ic_backpack
        )
    )

    fun getProductById(id: Int): Product? {
        return sampleProducts.find { it.id == id }
    }
}
