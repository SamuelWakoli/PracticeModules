package com.samwrotethecode.practicemodules.core.model

data class Person(
    val name: String,
    val email: String,
    val age: Int,
    val address: String,
) {
    val favoritePersons: MutableList<Person> = mutableListOf()

    infix fun likes(other: Person) {
        favoritePersons.add(other)
    }

    infix fun dislikes(other: Person) {
        favoritePersons.remove(other)
    }
}

// Hardcoded Current Person
val currentPerson = Person(
    name = "John Doe",
    email = "john.doe@example.com",
    age = 30,
    address = "123 Main St"
)

// Hardcoded list of people
val dummyPeople = listOf(
    Person(
        name = "Alice Smith",
        email = "alice.smith@example.com",
        age = 28,
        address = "123 Main St"
    ),
    Person(
        name = "Bob Johnson",
        email = "bob.johnson@example.com",
        age = 35,
        address = "456 Oak Ave"
    ),
    Person(
        name = "Charlie Brown",
        email = "charlie.brown@example.com",
        age = 22,
        address = "789 Pine Ln"
    ),
    Person(
        name = "Diana Garcia",
        email = "diana.garcia@example.com",
        age = 41,
        address = "101 Maple Dr"
    ),
    Person(
        name = "Ethan Rodriguez",
        email = "ethan.rodriguez@example.com",
        age = 30,
        address = "202 Birch Rd"
    ),
    Person(
        name = "Fiona Miller",
        email = "fiona.miller@example.com",
        age = 25,
        address = "303 Cedar Ct"
    ),
    Person(
        name = "George Wilson",
        email = "george.wilson@example.com",
        age = 50,
        address = "404 Elm Way"
    ),
    Person(
        name = "Hannah Davis",
        email = "hannah.davis@example.com",
        age = 19,
        address = "505 Willow Pl"
    ),
    Person(
        name = "Ian Martinez",
        email = "ian.martinez@example.com",
        age = 33,
        address = "606 Aspen St"
    ),
    Person(
        name = "Julia Lee",
        email = "julia.lee@example.com",
        age = 27,
        address = "707 Spruce Ave"
    ),
    Person(
        name = "Kevin Harris",
        email = "kevin.harris@example.com",
        age = 45,
        address = "808 Poplar Ln"
    ),
    Person(
        name = "Laura Clark",
        email = "laura.clark@example.com",
        age = 29,
        address = "909 Redwood Dr"
    ),
    Person(
        name = "Michael Lewis",
        email = "michael.lewis@example.com",
        age = 38,
        address = "111 Cherry Rd"
    ),
    Person(
        name = "Nora Walker",
        email = "nora.walker@example.com",
        age = 21,
        address = "222 Peach Ct"
    ),
    Person(
        name = "Oscar Hall",
        email = "oscar.hall@example.com",
        age = 55,
        address = "333 Plum Way"
    ),
    Person(
        name = "Penelope Allen",
        email = "penelope.allen@example.com",
        age = 31,
        address = "444 Orange Pl"
    ),
    Person(
        name = "Quentin Young",
        email = "quentin.young@example.com",
        age = 26,
        address = "555 Lemon St"
    ),
    Person(
        name = "Rachel King",
        email = "rachel.king@example.com",
        age = 42,
        address = "666 Lime Ave"
    ),
    Person(
        name = "Samuel Wright",
        email = "samuel.wright@example.com",
        age = 37,
        address = "777 Grape Ln"
    ),
    Person(
        name = "Tiffany Scott",
        email = "tiffany.scott@example.com",
        age = 23,
        address = "888 Kiwi Dr"
    ),
    Person(
        name = "Ulysses Green",
        email = "ulysses.green@example.com",
        age = 48,
        address = "999 Mango Rd"
    ),
    Person(
        name = "Victoria Adams",
        email = "victoria.adams@example.com",
        age = 32,
        address = "121 Berry Ct"
    ),
    Person(
        name = "Walter Baker",
        email = "walter.baker@example.com",
        age = 52,
        address = "232 Fig Way"
    ),
    Person(
        name = "Xenia Campbell",
        email = "xenia.campbell@example.com",
        age = 20,
        address = "343 Date Pl"
    ),
    Person(
        name = "Yvonne Evans",
        email = "yvonne.evans@example.com",
        age = 39,
        address = "454 Elderberry St"
    ),
    Person(
        name = "Zachary Edwards",
        email = "zachary.edwards@example.com",
        age = 24,
        address = "565 Coconut Ave"
    ),
    Person(
        name = "Amber Flores",
        email = "amber.flores@example.com",
        age = 43,
        address = "676 Pineapple Ln"
    ),
    Person(
        name = "Brian Gray",
        email = "brian.gray@example.com",
        age = 36,
        address = "787 Papaya Dr"
    ),
    Person(
        name = "Chloe Hill",
        email = "chloe.hill@example.com",
        age = 34,
        address = "898 Passionfruit Rd"
    )
)
