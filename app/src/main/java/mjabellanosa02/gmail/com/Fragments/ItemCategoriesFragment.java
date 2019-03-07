package mjabellanosa02.gmail.com.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mjabellanosa02.gmail.com.Adapter.View_ItemsCategories_Recycler_Adapter;
import mjabellanosa02.gmail.com.Model.Items;
import mjabellanosa02.gmail.com.R;

import java.util.ArrayList;

public class ItemCategoriesFragment extends Fragment {
    private ArrayList<Items> items = new ArrayList<>();
    RecyclerView ItemRecycler;
    public ItemCategoriesFragment(){}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.saladmenucategoriesfragment, container, false);
        ItemRecycler = (RecyclerView) view.findViewById(R.id.RecyclerView_saladCategoriesrecycler);
        View_ItemsCategories_Recycler_Adapter viewItemsRecyclerAdapter = new View_ItemsCategories_Recycler_Adapter(items,getContext());
        ItemRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        ItemRecycler.setAdapter(viewItemsRecyclerAdapter);
        return view;

    }
    @Override
    public  void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        items =new ArrayList<>();
        items.add(new Items("Fried Rice with Sauce",0.0,R.drawable.food));
        items.add(new Items("Vegetable Salads",0.0,R.drawable.food));

    }
}
