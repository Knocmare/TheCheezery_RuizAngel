package ruiz.angel.thecheezery.data

object CheezeryContract {
    object CombosEntry{
        const val TABLE_NAME = "Combos"
        const val COLUMN_ID = "idCombo"
        const val COLUMN_NAME = "nameCombo"
        const val COLUMN_PRICE = "priceCombo"
    }

    object ProductsEntry {
        const val TABLE_NAME = "Products"
        const val COLUMN_ID = "idProduct"
        const val COLUMN_NAME = "nameProduct"
        const val COLUMN_PRICE =  "priceProduct"
        const val COLUMN_IMAGE = "imageProduct"
        const val COLUMN_DESCRIPTION = "descriptionProduct"
        const val COLUMN_TYPE = "typeProduct"
    }

    object ProductsComboEntry {
        const val TABLE_NAME = "ProductsCombo"
        const val COLUMN_ID = "idProductCombo"
        const val COLUMN_PRODUCT_ID = "idProduct"
        const val COLUMN_COMBO_ID = "idCombo"
    }
}