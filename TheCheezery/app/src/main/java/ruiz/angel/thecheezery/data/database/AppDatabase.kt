package ruiz.angel.thecheezery.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ruiz.angel.thecheezery.data.database.dao.ComboDao
import ruiz.angel.thecheezery.data.database.dao.ProductComboDao
import ruiz.angel.thecheezery.data.database.dao.ProductDao
import ruiz.angel.thecheezery.data.database.entity.ProductEntity
import ruiz.angel.thecheezery.data.database.entity.ComboEntity
import ruiz.angel.thecheezery.data.database.entity.ProductComboEntity

@Database(
    entities = [
        ProductEntity::class,
        ComboEntity::class,
        ProductComboEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun comboDao(): ComboDao
    abstract fun productComboDao(): ProductComboDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cheezery.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}