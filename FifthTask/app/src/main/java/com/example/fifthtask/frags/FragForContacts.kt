package com.example.fifthtask.frags

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fifthtask.InterfaceClick
import com.example.fifthtask.MainActivity.Companion.CONTACTS
import com.example.fifthtask.Person
import com.example.fifthtask.databinding.FragmentFragForContactsBinding
import java.lang.RuntimeException

class FragForContacts : Fragment() {
    lateinit var binding: FragmentFragForContactsBinding
    private var contactArray: ArrayList<Person>? = null
    private lateinit var onButtonListener: InterfaceClick.ClickedItemHandler

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contactArray = requireArguments().getParcelableArrayList(CONTACTS)
        onButtonListener = context as InterfaceClick.ClickedItemHandler
        if (contactArray == null)
            throw RuntimeException("Error!")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFragForContactsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        for (contact in contactArray!!)
            onStartContact(contact, view)
    }

    @SuppressLint("SetTextI18n")
    fun onStartContact(person: Person, view: View) {
        view.findViewById<TextView>(person.textViewNameId).text =
            person.name + " " + person.secondName
        view.findViewById<TextView>(person.textViewPhoneId).text = person.phone
        view.findViewById<ConstraintLayout>(person.viewId).setOnClickListener {
            onButtonListener.openContactDetails(person)
        }
    }



    companion object {
        @JvmStatic
        fun newInstance(personList: ArrayList<Person>): FragForContacts {
            val contactFragArray = FragForContacts()
            contactFragArray.arguments = Bundle().also {
                it.putParcelableArrayList(CONTACTS, personList)
            }
            return contactFragArray
        }
    }
}