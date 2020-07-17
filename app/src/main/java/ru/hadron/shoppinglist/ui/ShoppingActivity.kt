package ru.hadron.shoppinglist.ui
/**
 * ShoppingActivity - экран со списком покупок.
 * ViewModel помещается в provider, подключается adapter, observer
 */

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

import ru.hadron.shoppinglist.R
import ru.hadron.shoppinglist.adapters.ShoppingItemAdapter
import ru.hadron.shoppinglist.data.db.entities.ShoppingItem
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
/* автоматически не импортируется, это для строки override val kodein by kodein()*/
import org.kodein.di.android.kodein

/* эти импорты стали не нужны после применения kodein*/
import ru.hadron.shoppinglist.data.repositories.ShoppingRepository
import ru.hadron.shoppinglist.data.db.ShoppingDataBase

class ShoppingActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()
    lateinit var viewModel: ShoppingViewModel
    /**
     *  Зависимости без использования kodein были такими:
     * val database = ShoppingDataBase(this)
     * val repository = ShoppingRepository(database)
     * val factory = ShoppingViewModelFactory(repository)
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            AddShoppingItemDialog(
                this,
                object : AddDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }).show()
        }
    }
}
