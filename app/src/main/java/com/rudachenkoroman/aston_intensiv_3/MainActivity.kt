package com.rudachenkoroman.aston_intensiv_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.rudachenkoroman.aston_intensiv_3.fragments.NewContactDialog
import com.rudachenkoroman.aston_intensiv_3.adapter.ContactAdapter
import com.rudachenkoroman.aston_intensiv_3.data.ContactSource
import com.rudachenkoroman.aston_intensiv_3.databinding.ActivityMainBinding
import com.rudachenkoroman.aston_intensiv_3.model.Contact

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val contactAdapter = ContactAdapter(this)
    companion object {
        var contactList = ArrayList<Contact>()
        var size = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contactList = ContactSource().loadContacts()
        setAdapter()
        updateCheckedTrash(isClickedTrash = false)

        binding.apply {
            addButton.setOnClickListener {
                NewContactDialog().show(supportFragmentManager, NewContactDialog.TAG)
            }
            trashButton.setOnClickListener {
                updateCheckedTrash(isClickedTrash = true)
            }
            deleteButton.setOnClickListener {
                contactList = contactAdapter.deletedContactList()
                setAdapter()
                updateCheckedTrash(isClickedTrash = false)
            }
            cancelButton.setOnClickListener {
                updateCheckedTrash(isClickedTrash = false)
            }
        }
    }

    private fun setAdapter() {
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.apply {
            adapter = contactAdapter
            layoutManager = linearLayoutManager
            contactAdapter.updateData(contactList)
        }
    }
    private fun updateCheckedTrash(isClickedTrash: Boolean) {
        contactAdapter.setShowCheckbox(isClickedTrash)
        if (isClickedTrash) {
            binding.addButton.visibility = View.INVISIBLE
            binding.deleteButton.visibility = View.VISIBLE
            binding.cancelButton.visibility = View.VISIBLE
            binding.trashButton.visibility = View.INVISIBLE
        } else {
            binding.addButton.visibility = View.VISIBLE
            binding.deleteButton.visibility = View.INVISIBLE
            binding.cancelButton.visibility = View.INVISIBLE
            binding.trashButton.visibility = View.VISIBLE
        }
    }
}