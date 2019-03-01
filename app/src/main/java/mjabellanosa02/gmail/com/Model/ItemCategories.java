package mjabellanosa02.gmail.com.Model;

public class
ItemCategories {

    String item_name = "";
    int item_display_picture_url;


    public ItemCategories() {
    }

    public ItemCategories(String item_name ) {
        this.item_name = item_name;

    }

    public ItemCategories(String item_name, int item_display_picture_url) {
        this.item_name = item_name;
        this.item_display_picture_url = item_display_picture_url;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }


    public int getItem_display_picture_url() {
        return item_display_picture_url;
    }

    public void setItem_display_picture_url(int item_display_picture_url) {
        this.item_display_picture_url = item_display_picture_url;
    }
}

