package ru.hadron.shoppinglist.data.db.entities
/**
 * Класс ShoppingItem описывает таблицу - название таблицы, поля в бд для Room
 * @Entity маркировка класса как сущности
 * @ColumnInfo маркировка полей бд. В конструкторе связываются поля в бд и переменные
 * @PrimaryKey поле для автогенерации номера записи в бд
 */
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")
class ShoppingItem(
    @ColumnInfo(name = "item_name")
    var name: String,
    @ColumnInfo(name = "item_amount")
    var amount: Int)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}