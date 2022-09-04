package com.tas.collagemaker.photoeditor.piccollage.photoframe.layoutfragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.tas.collagemaker.photoeditor.piccollage.photoframe.R;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.gradientfragment.GradientModel;

import java.util.ArrayList;

public class LayoutFragment extends Fragment {


    public RecyclerView recyclerView;
    private LayoutAdapter recyclerViewAdapter;
    private ArrayList<LayoutModel> imgList;
    private TypedArray gFrame;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_layout, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_viewLayout);
        lovGridFrame();
        setUpRecyclerView();

        return view;
    }


    private void setUpRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        recyclerViewAdapter = new LayoutAdapter(this.getActivity().getApplicationContext(), imgList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    private void lovGridFrame() {

        imgList = new ArrayList<LayoutModel>();
        gFrame = getResources().obtainTypedArray(R.array.gridArray);
        for (int i = 0; i < gFrame.length(); i++) {
            imgList.add(new LayoutModel(gFrame.getResourceId(i, 0)));

        }
    }
}