package com.example.testsip

data class Users(
    val id: String,
    val name: String,
    val address: Address
)

data class Address(
    val city: String,
    val street: String,
    val zipcode: String,
    val coordinates: Coordinates
)

data class Coordinates(
    val latitude: String,
    val longitude: String
)

fun printNestedDataClass(obj: Any, depth: Int) {
    val indent = " ".repeat(depth *2)
    obj.javaClass.declaredFields.forEach { field ->
        field.isAccessible = true
        if (field.type.name.contains("data")) {
            println("***$indent${field.name}: ${field.get(obj)}")
            printNestedDataClass(field, depth + 1)
        } else {
            println("!!!$indent${field.name}: ${field.get(obj)}")
        }
    }
}

fun main() {
    val user = Users(
        id = "1",
        name = "John",
        address = Address(
            city = "New York",
            street = "Broadway",
            zipcode = "10001",
            coordinates = Coordinates(
                latitude = "40.7128",
                longitude = "-74.0060"
            )
        )
    )

    printNestedDataClass(user, 0)
}