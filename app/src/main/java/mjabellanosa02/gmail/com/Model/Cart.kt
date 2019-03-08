package mjabellanosa02.gmail.com.Model

class Cart {
    var cart_item_name: String ?= null
    var cart_item_uid: String ?= null
    var cart_uid: String ?= null
    var cart_item_price: Double ?= null
    var cart_item_image_url: String ?= null
    var cart_item_additional_info: ArrayList<String> ?= null
    var cart_item_count: Int ?= null

    constructor()

    constructor(
        cart_item_name: String?,
        cart_item_uid: String?,
        cart_uid: String?,
        cart_item_price: Double?,
        cart_item_image_url: String?,
        cart_item_additional_info: ArrayList<String>?,
        cart_item_count: Int?
    ) {
        this.cart_item_name = cart_item_name
        this.cart_item_uid = cart_item_uid
        this.cart_uid = cart_uid
        this.cart_item_price = cart_item_price
        this.cart_item_image_url = cart_item_image_url
        this.cart_item_additional_info = cart_item_additional_info
        this.cart_item_count = cart_item_count
    }

    constructor(
        cart_item_name: String?,
        cart_item_uid: String?,
        cart_item_price: Double?,
        cart_item_image_url: String?,
        cart_item_additional_info: ArrayList<String>?,
        cart_item_count: Int?
    ) {
        this.cart_item_name = cart_item_name
        this.cart_item_uid = cart_item_uid
        this.cart_item_price = cart_item_price
        this.cart_item_image_url = cart_item_image_url
        this.cart_item_additional_info = cart_item_additional_info
        this.cart_item_count = cart_item_count
    }
}