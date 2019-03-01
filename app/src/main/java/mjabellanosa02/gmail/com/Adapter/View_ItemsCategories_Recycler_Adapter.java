package mjabellanosa02.gmail.com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import mjabellanosa02.gmail.com.Model.ItemCategories;
import mjabellanosa02.gmail.com.Model.Items;
import mjabellanosa02.gmail.com.R;

import java.util.ArrayList;

public class View_ItemsCategories_Recycler_Adapter extends RecyclerView.Adapter<View_ItemsCategories_Recycler_Adapter.ViewHolder> {
    private ArrayList<Items> items;
    private Context mContext;
    //    private StorageReference mStorageRef;
    String item_name = "";
    Double item_price;



    public View_ItemsCategories_Recycler_Adapter(ArrayList<Items> items, Context mContext) {
        this.items = items;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public View_ItemsCategories_Recycler_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categoriescontent, parent, false);
        View_ItemsCategories_Recycler_Adapter.ViewHolder holder = new View_ItemsCategories_Recycler_Adapter.ViewHolder(view, mContext, items);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final View_ItemsCategories_Recycler_Adapter.ViewHolder holder, int position) {

//        mStorageRef = FirebaseStorage.getInstance().getReference().child("item_images").child(itemCategories.get(position).getItem_uid()).child(itemCategories.get(position).getItem_uid()+"1");
//
//        mStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                Picasso.get().load(uri.toString()).error(R.mipmap.ic_launcher).into(holder.cardviewItem_imageItem);
//            }
//        });


        item_name = items.get(position).getItem_name();
        item_price=(Double) items.get(position).getItem_Price();
        holder.cardviewItem_itemName.setText(item_name);
        holder.cardviewItem_itemPrice.setText(item_price+"");
        holder.cardviewItem_imageItem.setImageResource(items.get(position).getItem_display_picture_url());

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cardviewItem_itemName;
        TextView cardviewItem_itemPrice;
        ImageView cardviewItem_imageItem;
        CardView parentLayout_Item;
        ArrayList<Items> items;
        Context mContext;

        public ViewHolder(View itemView, Context mContext, ArrayList<Items> items) {
            super(itemView);
            this.items = items;
//            this.mContext=mContext;
//            itemView.setOnClickListener(this);
            cardviewItem_itemName = itemView.findViewById(R.id.textView_foodname);
            cardviewItem_itemPrice = itemView.findViewById(R.id.textView_foodPrice);
            cardviewItem_imageItem = itemView.findViewById(R.id.cardviewItem_imageItem);
        }

//        @Override
//        public void onClick(View view) {
//        int position = getAdapterPosition();
//        ItemCategories item = this.itemCategories.get(position);
//            item_uid=item.getItem_uid();
//            item_name=item.getItem_name();
//            item_price=item.getItem_price() + "";
//            star_rating=item.getItem_average_rating()+"";
//            item_category=item.getItem_category_id()+"";
//            item_description=item.getItem_description()+"";
//            isForSale=item.isItem_for_sale()+"";
//            isPerSquareUnit=item.isItem_is_per_sqr_unit_of_measurement()+"";
//            price_description=item.getItem_price_description()+"";
//            store_id=item.getItem_store_id()+"";
//
//            Intent intent = new Intent(mContext,SupplierEditItemDetailsActivity.class);
//            intent.putExtra("item_uid",item_uid);
//            intent.putExtra("item_name",item_name);
//            intent.putExtra("item_price",item_price);
//            intent.putExtra("star_rating",star_rating);
//            intent.putExtra("item_category",item_category);
//            intent.putExtra("item_description",item_description);
//            intent.putExtra("isForSale",isForSale);
//            intent.putExtra("isPerSquareUnit",isPerSquareUnit);
//            intent.putExtra("price_description",price_description);
//            intent.putExtra("store_id",store_id);
//            this.mContext.startActivity(intent);
//        }
    }
}
