package mjabellanosa02.gmail.com.Model;

public class Items {
    String item_name = "";
    Double Item_Price;
    int item_display_picture_url;

    public Items(String item_name, Double item_Price, int item_display_picture_url) {
        this.item_name = item_name;
        Item_Price = item_Price;
        this.item_display_picture_url = item_display_picture_url;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Double getItem_Price() {
        return Item_Price;
    }

    public void setItem_Price(Double item_Price) {
        Item_Price = item_Price;
    }

    public int getItem_display_picture_url() {
        return item_display_picture_url;
    }

    public void setItem_display_picture_url(int item_display_picture_url) {
        this.item_display_picture_url = item_display_picture_url;
    }
}
