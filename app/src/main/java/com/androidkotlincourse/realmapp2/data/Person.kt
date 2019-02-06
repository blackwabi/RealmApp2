package com.androidkotlincourse.realmapp2.data

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Person: RealmObject() {
    @PrimaryKey
    var name: String? = null
    var age: Int? = null
    var ownedDogs: RealmList<Dog> = RealmList()
}

