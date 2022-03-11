package models;

import java.io.Serializable;
import java.util.List;

public class BookResponse implements Serializable {

    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    public List<Item> getItems() {
        return items;
    }
}
