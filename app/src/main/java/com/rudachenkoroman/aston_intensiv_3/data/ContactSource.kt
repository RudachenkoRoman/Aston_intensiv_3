package com.rudachenkoroman.aston_intensiv_3.data

import com.rudachenkoroman.aston_intensiv_3.model.Contact

class ContactSource {

    fun loadContacts(): ArrayList<Contact> {
        return fillContacts()
    }

    private fun fillContacts() : ArrayList<Contact> {
        val mutableListContact = mutableListOf<Contact>()
        val listContact = arrayListOf<Contact>()
        var count = 1
        val firstNames = "FirstName "
        val lastNames = "Lastname "
        val phones = "+0 (000) 000-00-"

        for (i in 0 until 100) {
            val contact = Contact(i+1, firstNames + count, lastNames + count, phones + count)
            count++
            mutableListContact.add(contact)
        }
        listContact.addAll(mutableListContact)
        return listContact
    }
}