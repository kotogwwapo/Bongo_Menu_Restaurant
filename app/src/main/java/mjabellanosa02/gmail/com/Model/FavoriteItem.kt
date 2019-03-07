package mjabellanosa02.gmail.com.Model

public class FavoriteItem{
    var favorite_item_name: String ?= null
    var favorite_item_price: Double ?= null
    var favorite_item_uid: String?=null
    var favorite_item_image_url: String ?= null
    var favorite_item_menu_category_uid: String ?= null
    constructor()

    constructor(
        favorite_item_name: String?,
        favorite_item_price: Double?,
        favorite_item_uid: String?,
        favorite_item_image_url: String?,
        favorite_item_menu_category_uid: String?
    ) {
        this.favorite_item_name = favorite_item_name
        this.favorite_item_price = favorite_item_price
        this.favorite_item_uid = favorite_item_uid
        this.favorite_item_image_url = favorite_item_image_url
        this.favorite_item_menu_category_uid = favorite_item_menu_category_uid
    }


}