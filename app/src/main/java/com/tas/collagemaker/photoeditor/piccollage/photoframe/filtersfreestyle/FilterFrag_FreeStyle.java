package com.tas.collagemaker.photoeditor.piccollage.photoframe.filtersfreestyle;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.tas.collagemaker.photoeditor.piccollage.photoframe.R;

import java.util.ArrayList;


public class FilterFrag_FreeStyle extends Fragment {


    public RecyclerView  recyclerView;
    private FilterFrag_FreestyleAdapter recyclerViewAdapter;
    ArrayList<FilterFrag_FreestyleModel> filterModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       final View  view=inflater.inflate(R.layout.fragment_filter_frag__free_style,container,false);
        recyclerView= (RecyclerView) view.findViewById(R.id.myrecycleviewforfilter);

        filterModels = new ArrayList<FilterFrag_FreestyleModel>();
        filterModels.add(new FilterFrag_FreestyleModel(R.drawable.none, "None"));
        filterModels.add(new FilterFrag_FreestyleModel(R.drawable.oil,  "Oil"));
        filterModels.add(new FilterFrag_FreestyleModel(R.drawable.blur, "Blur"));
        filterModels.add(new FilterFrag_FreestyleModel(R.drawable.grey, "Grey"));

        setUpRecyclerView();
        return view;

    }

    private void setUpRecyclerView() {

        recyclerView.setHasFixedSize(true);
        recyclerViewAdapter=new FilterFrag_FreestyleAdapter(getContext(),filterModels);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

    }
}