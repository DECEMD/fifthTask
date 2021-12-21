package com.example.fifthtask

interface InterfaceClick {
    interface ClickedItemHandler {
        fun openContactDetails(contact: Person)
        fun updateContact(contact: Person)
    }
}