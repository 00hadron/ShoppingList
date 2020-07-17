package ru.hadron.shoppinglist.ui
/**
 *Класс AddShoppingItemDialog - окно диалога добавления позиции
 */
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import kotlinx.android.synthetic.main.dialog_add_shopping_item.*
import ru.hadron.shoppinglist.R
import ru.hadron.shoppinglist.data.db.entities.ShoppingItem

class AddShoppingItemDialog(context: Context, private var addDialogListener: AddDialogListener) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_add_shopping_item)

        tvAdd.setOnClickListener {
            val name = etName.text.toString()
            val amount: Int

            try {
                amount = etAmount.text.toString().toInt()
            }
            catch (e: NumberFormatException) {
                Toast.makeText(context, context.getString(R.string.enter_amount_toast), Toast.LENGTH_SHORT).show()
               return@setOnClickListener
            }

            if (name.isNullOrEmpty()) {
                Toast.makeText(context, context.getString(R.string.enter_name_toast), Toast.LENGTH_SHORT).show()
               return@setOnClickListener
            }

            val item = ShoppingItem(name, amount)
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }


        tvCancel.setOnClickListener {
            cancel()
        }
    }
}