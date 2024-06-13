package imd.ntub.myfrags0509

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class ContactDatabaseManager(context: Context) {

    private val dbHelper = ContactDatabaseHelper(context)
    private val database = dbHelper.writableDatabase

    fun addContact(contact: Contact): Long {
        val values = ContentValues().apply {
            put(ContactDatabaseHelper.COLUMN_NAME, contact.name)
            put(ContactDatabaseHelper.COLUMN_PHONE_NUMBER, contact.phoneNumber)
            put(ContactDatabaseHelper.COLUMN_IMAGE_URI, contact.imageUri)
        }
        return database.insert(ContactDatabaseHelper.TABLE_CONTACTS, null, values)
    }

    fun getAllContacts(): List<Contact> {
        val contacts = mutableListOf<Contact>()
        val cursor: Cursor = database.query(
            ContactDatabaseHelper.TABLE_CONTACTS,
            null, null, null, null, null, null
        )
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(ContactDatabaseHelper.COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(ContactDatabaseHelper.COLUMN_NAME))
                val phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(ContactDatabaseHelper.COLUMN_PHONE_NUMBER))
                val imageUri = cursor.getString(cursor.getColumnIndexOrThrow(ContactDatabaseHelper.COLUMN_IMAGE_URI))
                contacts.add(Contact(id, name, phoneNumber, imageUri))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return contacts
    }

    fun deleteContact(id: Long) {
        database.delete(ContactDatabaseHelper.TABLE_CONTACTS, "${ContactDatabaseHelper.COLUMN_ID}=?", arrayOf(id.toString()))
    }
}

