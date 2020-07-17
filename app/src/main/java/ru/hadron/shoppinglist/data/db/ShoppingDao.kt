package ru.hadron.shoppinglist.data.db
/**
 * Интерфейс ShoppingDao описывает методы для работы с бд через Room, cоотносит методы с запросами
 */
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.*
import ru.hadron.shoppinglist.data.db.entities.ShoppingItem

@Dao
interface ShoppingDao {
    @Insert(onConflict = REPLACE)
    suspend fun upsert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems(): LiveData<List<ShoppingItem>>
}