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
class NewContactDialog : DialogFragment() {

    private lateinit var adapter: ContactAdapter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val layout = layoutInflater.inflate(R.layout.fragment_new_contact, null)

            val contactList = MainActivity.contactList
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerView)
            adapter = ContactAdapter(requireContext())
            recyclerView.adapter = adapter

            val addButton: Button = layout.findViewById(R.id.addButton)
            val cancelButton: Button = layout.findViewById(R.id.cancelButton)
            val editTextFirstName: EditText = layout.findViewById(R.id.editTextFirstName)
            val editTextLastName: EditText = layout.findViewById(R.id.editTextLastName)
            val editTextPhone: EditText = layout.findViewById(R.id.editTextPhone)

            addButton.setOnClickListener {
                val id = contactList.size + MainActivity.size + 1
                val name = editTextFirstName.text.toString()
                val surname = editTextLastName.text.toString()
                val phone = editTextPhone.text.toString()

                val newContact = Contact(id, name, surname, phone)

                contactList.add(newContact)
                adapter.updateData(contactList)
                dialog?.cancel()
            }

            cancelButton.setOnClickListener {
                adapter.updateData(contactList)
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
