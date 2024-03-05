package com.rudachenkoroman.aston_intensiv_3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rudachenkoroman.aston_intensiv_3.fragments.EditContactDialog
import com.google.android.material.card.MaterialCardView
import com.rudachenkoroman.aston_intensiv_3.MainActivity
import com.rudachenkoroman.aston_intensiv_3.R
import com.rudachenkoroman.aston_intensiv_3.diffUtil.ContactDiffUtilCallback
import com.rudachenkoroman.aston_intensiv_3.model.Contact

class ContactAdapter(
    private val context: Context
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private var contactList = ArrayList<Contact>()
    private var deletedContact = ArrayList<Contact>()
    private var showCheckbox = false

    class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contactFirstName: TextView = view.findViewById(R.id.firstNameText)
        val contactLastName: TextView = view.findViewById(R.id.lastNameText)
        val contactId: TextView = view.findViewById(R.id.idText)
        val contactPhone: TextView = view.findViewById(R.id.phoneText)
        val cardView: MaterialCardView = view.findViewById(R.id.contactCardView)
        val checkBox: CheckBox = view.findViewById(R.id.checkBox)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.contact_item_layout, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contactCurrent = contactList[position]
        val resource = context.resources

        holder.apply {
            contactFirstName.text = contactCurrent.firstName
            contactLastName.text = contactCurrent.lastName
            contactId.text = resource.getString(R.string.id_100, contactCurrent.id.toString())
            contactPhone.text = resource.getString(R.string.phone, contactCurrent.phone)
            checkBox.isVisible = showCheckbox

            cardView.setOnClickListener {
                val editContactDialog = EditContactDialog(contactCurrent)
                val activity =  itemView.context as MainActivity
                editContactDialog.show(activity.supportFragmentManager, EditContactDialog.TAG)
            }
            checkBox.isVisible = showCheckbox
            checkBox.setOnClickListener {
                if (checkBox.isChecked){
                    deletedContact.add(contactCurrent)
                }
                else{
                    deletedContact.remove(contactCurrent)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    fun setShowCheckbox(isShow: Boolean) {
        showCheckbox = isShow
        this.notifyItemRangeChanged(0, contactList.size)
    }

    fun deletedContactList(): ArrayList<Contact> {
        val newList = this.contactList.minus(deletedContact.toSet())
        MainActivity.size = deletedContact.size
        deletedContact.clear()
        return newList as ArrayList<Contact>
    }

    fun updateData(newContactList: ArrayList<Contact>) {
        val diffUtilCallBack = ContactDiffUtilCallback(contactList, newContactList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallBack)
        contactList.clear()
        contactList.addAll(newContactList)
        diffResult.dispatchUpdatesTo(this)
    }
}

