package ru.hadron.shoppinglist.data.db
/**
 * Абстрактный класс ShoppingDataBase создает один экземпляр бд.
 * Наследует RoomDatabase, аннотирован @Database.
 * RoomDatabase provides direct access to the underlying database implementation but
 * you should prefer using Dao classes.
 */
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.hadron.shoppinglist.data.db.entities.ShoppingItem

@Database(entities = [ShoppingItem::class], version = 1)
abstract class ShoppingDataBase : RoomDatabase() {
    abstract fun getShoppingDao(): ShoppingDao

    companion object {
        @Volatile
        private var instance: ShoppingDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK) {
                instance?: createDatabase(context).also { instance = it }
            }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ShoppingDataBase::class.java, "ShoppingDB.db").build()
    }
}