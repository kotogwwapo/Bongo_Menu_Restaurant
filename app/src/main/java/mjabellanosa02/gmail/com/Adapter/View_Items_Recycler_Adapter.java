package mjabellanosa02.gmail.com.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
//import com.example.morkince.okasyonv2.activities.homepage_supplier_activities.SupplierEditItemDetailsActivity;
//import com.example.morkince.okasyonv2.activities.model.ItemCategories;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.squareup.picasso.Picasso;
import mjabellanosa02.gmail.com.ItemCategoriesFragment;
import mjabellanosa02.gmail.com.Model.ItemCategories;
import mjabellanosa02.gmail.com.R;

import java.util.ArrayList;


public class View_Items_Recycler_Adapter extends RecyclerView.Adapter<View_Items_Recycler_Adapter.ViewHolder>{
    private ArrayList<ItemCategories> itemCategories;
    private Context mContext;
//    private StorageReference mStorageRef;
    String item_name="";


    public View_Items_Recycler_Adapter(ArrayList<ItemCategories> itemCategories, Context mContext) {
        this.itemCategories = itemCategories;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_menu_category, parent, false);
        ViewHolder holder = new ViewHolder(view,mContext, itemCategories);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final View_Items_Recycler_Adapter.ViewHolder holder, int position) {

//        mStorageRef = FirebaseStorage.getInstance().getReference().child("item_images").child(itemCategories.get(position).getItem_uid()).child(itemCategories.get(position).getItem_uid()+"1");
//
//        mStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                Picasso.get().load(uri.toString()).error(R.mipmap.ic_launcher).into(holder.cardviewItem_imageItem);
//            }
//        });


        item_name= itemCategories.get(position).getItem_name();
        holder.cardviewItem_itemName.setText(item_name);
        holder.cardviewItem_imageItem.setImageResource(itemCategories.get(position).getItem_display_picture_url());

    }

    @Override
    public int getItemCount() {
        return itemCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView cardviewItem_itemName;
        ImageView cardviewItem_imageItem;
        CardView parentLayout_Item;
        ArrayList<ItemCategories> itemCategories;
        Context mContext;

        public ViewHolder(View itemView, Context mContext,ArrayList<ItemCategories> itemCategories){
            super(itemView);
            this.itemCategories = itemCategories;
//            this.mContext=mContext;
            itemView.setOnClickListener(this);
            cardviewItem_itemName = itemView.findViewById(R.id.textView_menuCategoryRowCategoryName);
            cardviewItem_imageItem = itemView.findViewById(R.id.imageView_menuCategoryRowImage);
        }


        @Override
        public void onClick(View view) {
        int position = getAdapterPosition();
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            Fragment myFragment = new ItemCategoriesFragment();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.Framelayout_apptabs, myFragment).addToBackStack(null).commit();
        }
    }
}
