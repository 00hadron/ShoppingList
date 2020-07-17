package ru.hadron.shoppinglist.ui

import ru.hadron.shoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}