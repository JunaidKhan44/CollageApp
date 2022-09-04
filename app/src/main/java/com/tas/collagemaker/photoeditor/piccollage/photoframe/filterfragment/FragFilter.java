package com.tas.collagemaker.photoeditor.piccollage.photoframe.filterfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.tas.collagemaker.photoeditor.piccollage.photoframe.R;

import java.util.ArrayList;


public class FragFilter extends Fragment {

    public RecyclerView recyclerView;
    private FragFilterAdapter recyclerViewAdapter;
    ArrayList<FragFilterModel> filterModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_frag_filter, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_viewforfilter);

        filterModels = new ArrayList<FragFilterModel>();
        filterModels.add(new FragFilterModel(R.drawable.none, "None"));
        filterModels.add(new FragFilterModel(R.drawable.oil, "Oil"));
        filterModels.add(new FragFilterModel(R.drawable.blur, "Blur"));
        filterModels.add(new FragFilterModel(R.drawable.grey, "Grey"));

        setUpRecyclerView();
        return view;
    }

    private void setUpRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recyclerViewAdapter = new FragFilterAdapter(this.getActivity().getApplicationContext(), filterModels);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }
}