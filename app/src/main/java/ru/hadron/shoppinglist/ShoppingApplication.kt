package ru.hadron.shoppinglist
/**
 * Класс ShoppingApplication  - формализация зависимостей с Kodein
 * (Объявить в манифесте)
 */
import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import ru.hadron.shoppinglist.data.db.ShoppingDataBase
import ru.hadron.shoppinglist.data.repositories.ShoppingRepository
import ru.hadron.shoppinglist.ui.ShoppingViewModelFactory

class ShoppingApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@ShoppingApplication))

        bind() from singleton { ShoppingDataBase(
            instance()) }

        bind() from singleton { ShoppingRepository(
            instance()) }

        bind() from provider {
            ShoppingViewModelFactory(
                instance()) }
    }
}