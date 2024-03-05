package com.rudachenkoroman.aston_intensiv_3.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.rudachenkoroman.aston_intensiv_3.MainActivity
import com.rudachenkoroman.aston_intensiv_3.R
import com.rudachenkoroman.aston_intensiv_3.adapter.ContactAdapter
import com.rudachenkoroman.aston_intensiv_3.model.Contact

class EditContactDialog(private val contact: Contact) : DialogFragment() {

    private lateinit var adapter: ContactAdapter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val layout = layoutInflater.inflate(R.layout.fragment_edit_contact, null)

            val contactList = MainActivity.contactList
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerView)
            adapter = recyclerView.adapter as ContactAdapter
            recyclerView.adapter = adapter

            val editButton: Button = layout.findViewById(R.id.editButton)
            val cancelButton: Button = layout.findViewById(R.id.cancelButton)
            val editTextFirstName: EditText = layout.findViewById(R.id.editTextFirstName)
            val editTextLastName: EditText = layout.findViewById(R.id.editTextLastName)
            val editTextPhone: EditText = layout.findViewById(R.id.editTextPhone)

            editTextFirstName.setText(contact.firstName)
            editTextLastName.setText(contact.lastName)
            editTextPhone.setText(contact.phone)

            editButton.setOnClickListener {
                val newContact = Contact(
                    contact.id,
                    editTextFirstName.text.toString(),
                    editTextLastName.text.toString(),
                    editTextPhone.text.toString()
                )

                contactList[contact.id - MainActivity.size - 1] = newContact
                adapter.updateData(contactList)
                dialog?.cancel()
            }

            cancelButton.setOnClickListener {
                dialog?.cancel()
            }
            builder.setView(layout)
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        const val TAG = "NewContactDialog"
    }
}
