package mjabellanosa02.gmail.com;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mjabellanosa02.gmail.com.Adapter.View_Items_Recycler_Adapter;
import mjabellanosa02.gmail.com.Model.ItemCategories;

import java.util.ArrayList;

public class MenuCategoriesFragment extends Fragment {
    private ArrayList<ItemCategories> itemCategories = new ArrayList<>();
    RecyclerView recyclerView;

    public MenuCategoriesFragment(){}
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.menucategoriesfragment, container, false);
            recyclerView = (RecyclerView) view.findViewById(R.id.viewStoreItems_recyclerViewItems);

            View_Items_Recycler_Adapter viewItemsRecyclerAdapter = new View_Items_Recycler_Adapter(itemCategories,getContext());

            recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),2));
            recyclerView.setAdapter(viewItemsRecyclerAdapter);

            return view;

        }

        @Override
        public  void onCreate(@Nullable Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            itemCategories =new ArrayList<>();
            itemCategories.add(new ItemCategories("salad Menu",R.drawable.food));
            itemCategories.add(new ItemCategories("Noodles Category",R.drawable.food));
            itemCategories.add(new ItemCategories("Potato Menu",R.drawable.food));
            itemCategories.add(new ItemCategories("Burger Menu",R.drawable.food));
        }
}
