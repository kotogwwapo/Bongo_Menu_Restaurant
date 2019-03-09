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
import mjabellanosa02.gmail.com.R;

public class HotDealsFragment extends Fragment {
    public HotDealsFragment(){}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_trending_hot_deals, container, false);
        return view;

    }
}
