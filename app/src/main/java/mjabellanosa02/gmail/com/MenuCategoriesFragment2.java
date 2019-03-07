package mjabellanosa02.gmail.com;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;
import com.xwray.groupie.ViewHolder;
import mjabellanosa02.gmail.com.GroupieViewHolders.MenuCategoriesViewHolder;
import mjabellanosa02.gmail.com.Model.ItemCategories;
import mjabellanosa02.gmail.com.RecallableClasses.Custom_Progress_Dialog;
import mjabellanosa02.gmail.com.RecallableClasses.PopUpDialogs;
import mjabellanosa02.gmail.com.RecallableClasses.RandomMessages;

import java.util.ArrayList;

public class MenuCategoriesFragment2 extends Fragment {
    private ArrayList<ItemCategories> itemCategories = new ArrayList<>();
    RecyclerView recyclerView;

    public MenuCategoriesFragment2(){}
        @Nullable
        @Override
        public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.menucategoriesfragment, container, false);

        if (isAdded()) {
            recyclerView = (RecyclerView) view.findViewById(R.id.viewStoreItems_recyclerViewItems);
            final GroupAdapter<ViewHolder> groupAdapter = new GroupAdapter<>();

            final Custom_Progress_Dialog dialog = new Custom_Progress_Dialog(getActivity());
            dialog.showDialog("Loading", new RandomMessages().getRandomMessage());

            FirebaseFirestore.getInstance()
                    .collection("Menu_Categories")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                QuerySnapshot result = task.getResult();

                                if (!result.isEmpty() && result != null){
                                    for (DocumentSnapshot doc :result) {
                                        String imageUrl = doc.getString("menu_categories_image_url");
                                        String categoryName = doc.getString("menu_categories_name");

                                        groupAdapter.add(new MenuCategoriesViewHolder(imageUrl, categoryName, getActivity()));
                                    }

                                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));
                                    recyclerView.setAdapter(groupAdapter);
                                    dialog.dissmissDialog();
                                }else {
                                    dialog.dissmissDialog();
                                    new PopUpDialogs(getActivity()).infoDialog("Empty Collection", "There are no items in this category");
                                }
                            } else {
                                //stop progress dialog
                                dialog.dissmissDialog();
                                new PopUpDialogs(getActivity()).errorDialog("Error", "Something went wrong");
                                Log.e("MCF", task.getException().toString());
                            }
                        }
                    });

//            View_Items_Recycler_Adapter viewItemsRecyclerAdapter = new View_Items_Recycler_Adapter(itemCategories, getContext());
//
//            recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));
//            recyclerView.setAdapter(viewItemsRecyclerAdapter);

            groupAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(@NonNull Item item, @NonNull View view) {
//                    MenuCategoriesViewHolder menuCategoriesViewHolder = (MenuCategoriesViewHolder) item;
//                    Intent intent = new Intent(menuCategoriesViewHolder.getContext(), )
//                    menuCategoriesViewHolder.getContext();

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Fragment myFragment = new ItemCategoriesFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.Framelayout_apptabs, myFragment).addToBackStack(null).commit();
                }
            });
        }

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
