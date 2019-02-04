package com.androidkotlincourse.realmapp2.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Dog: RealmObject() {
    @PrimaryKey
    var name: String? = null
    var age: Int? = null
    var color: String? = null
}