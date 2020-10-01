package com.home.test.domain.persistent.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contracts_table")
class ContactEntry(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "address") val address: String?
)