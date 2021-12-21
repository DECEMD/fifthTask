package com.example.fifthtask.frags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.fifthtask.InterfaceClick
import com.example.fifthtask.MainActivity.Companion.ITEM_FOR_CONTACTS
import com.example.fifthtask.Person
import com.example.fifthtask.R
import com.example.fifthtask.databinding.FragmentFragForContactsDetailsBinding
import java.lang.RuntimeException


class FragForContactsDetails : Fragment() {
    lateinit var binding: FragmentFragForContactsDetailsBinding
    private var person: Person? = null
    private lateinit var onButtonListener: InterfaceClick.ClickedItemHandler
    private var editName: EditText? = null
    private var editSecondName: EditText? = null
    private var editPhone: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        person = requireArguments().getParcelable(ITEM_FOR_CONTACTS)
        onButtonListener = context as InterfaceClick.ClickedItemHandler
        if (person == null)
            throw RuntimeException("Error!")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFragForContactsDetailsBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editName = binding.nameEditText
        editSecondName = binding.surnameEditText
        editPhone = binding.phoneEditText

        editName?.setText(person?.name)
        editSecondName?.setText(person?.secondName)
        editPhone?.setText(person?.phone)

        view.findViewById<Button>(R.id.saveButton).setOnClickListener {
            updateContact()
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    override fun onPause() {
        super.onPause()
        updateContact()
    }

    private fun updateContact() {
        person?.name = editName?.text.toString()
        person?.secondName = editSecondName?.text.toString()
        person?.phone = editPhone?.text.toString()
        onButtonListener.updateContact(person!!)
    }

    companion object {
        fun newInstance(person: Person): FragForContactsDetails {
            val contactDetailsFragment = FragForContactsDetails()
            contactDetailsFragment.arguments = Bundle().also {
                it.putParcelable(ITEM_FOR_CONTACTS, person)
            }
            return contactDetailsFragment
        }
    }
}