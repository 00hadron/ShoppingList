package ru.hadron.shoppinglist.ui
/**
 * Класс ShoppingViewModel дает возможность Activity и фрагментам сохранять необходимые
 * им объекты живыми при повороте экрана.
 * Имплементрирует ViewModel
 */
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.hadron.shoppinglist.data.db.entities.ShoppingItem
import ru.hadron.shoppinglist.data.repositories.ShoppingRepository

class ShoppingViewModel (private val repository: ShoppingRepository) : ViewModel() {
    fun upsert(item: ShoppingItem) = GlobalScope.launch { repository.upsert(item) }

    fun delete(item: ShoppingItem) = GlobalScope.launch { repository.delete(item) }

    fun getAllShoppingItems() = repository.getAllShoppingItems()
}