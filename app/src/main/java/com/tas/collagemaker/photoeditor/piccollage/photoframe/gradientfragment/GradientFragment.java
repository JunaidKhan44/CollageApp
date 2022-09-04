package com.tas.collagemaker.photoeditor.piccollage.photoframe.gradientfragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.tas.collagemaker.photoeditor.piccollage.photoframe.R;

import java.util.ArrayList;

public class GradientFragment extends Fragment {

    public RecyclerView recyclerView;
    private GradientAdapter recyclerViewAdapter;
    private ArrayList<GradientModel> gradientColor;
    private TypedArray gradientcolors;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_gradient, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_viewgradient);
        lovgradient();
        setUpRecyclerView();
        return view;
    }


    private void setUpRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        recyclerViewAdapter = new GradientAdapter(this.getActivity().getApplicationContext(), gradientColor);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    private void lovgradient(){

        gradientColor=new ArrayList<GradientModel>();
        gradientcolors= getResources().obtainTypedArray(R.array.gradientArray);
        for (int i = 0; i < gradientcolors.length(); i++) {
            gradientColor.add(new GradientModel(gradientcolors.getResourceId(i,0)));

        }
    }
}