package com.example.fifthtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.example.fifthtask.databinding.ActivityMainBinding
import com.example.fifthtask.frags.FragForContacts
import com.example.fifthtask.frags.FragForContactsDetails

class MainActivity : AppCompatActivity(), InterfaceClick.ClickedItemHandler {
    lateinit var binding: ActivityMainBinding
    private var frameLayout: FrameLayout? = null
    private var contactListFragment: FragForContacts? = null
    private var contactsList: ArrayList<Person>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        frameLayout = findViewById(R.id.fragContainer)
        contactsList = onStartContact()
        supportFragmentManager.apply {
            val transaction = beginTransaction()
            contactListFragment = FragForContacts.newInstance(contactsList!!)
            transaction.replace(R.id.fragContainer, contactListFragment!!)
            transaction.commit()
        }
    }
    override fun openContactDetails(person: Person) {
        supportFragmentManager.apply {
            val transaction = beginTransaction()
            val contactDetailFragment = FragForContactsDetails.newInstance(person)
            transaction.replace(R.id.fragContainer, contactDetailFragment)
            transaction.addToBackStack(null);
            transaction.commit()
        }
    }

    override fun updateContact(person: Person) {
        for (c in contactsList!!)
            if (person.viewId == c.viewId) {
                c.name = person.name
                c.secondName = person.secondName
                c.phone = person.phone
            }
    }

    private fun onStartContact(): ArrayList<Person> {
        val contactList = ArrayList<Person>()
        contactList.add(
            Person(
                R.id.contact_1,
                R.id.name_contact_1,
                R.id.phone_contact_1,
                "Теодор",
                "Рузвельт",
                "89996578789"
            )
        )
        contactList.add(
            Person(
                R.id.contact_2,
                R.id.name_contact_2,
                R.id.phone_contact_2,
                "Сергей",
                "Мавроди",
                "89996573131"
            )
        )
        contactList.add(
            Person(
                R.id.contact_3,
                R.id.name_contact_3,
                R.id.phone_contact_3,
                "Леонардо",
                "Давинчи",
                "89996579886"
            )
        )
        contactList.add(
            Person(
                R.id.contact_4,
                R.id.name_contact_4,
                R.id.phone_contact_4,
                "Жанна",
                "Дарк",
                "89996571998"
            )
        )
        return contactList
    }
    companion object {
        const val ITEM_FOR_CONTACTS = "ITEM_FOR_CONTACTS"
        const val CONTACTS = "CONTACTS"
    }
}