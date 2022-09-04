package com.tas.collagemaker.photoeditor.piccollage.photoframe.colorfragment;

import static com.tas.collagemaker.photoeditor.piccollage.photoframe.otherclasses.FreeStyle.context;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.tas.collagemaker.photoeditor.piccollage.photoframe.R;

import java.util.ArrayList;

public class ColorFragment extends Fragment {

    public RecyclerView recyclerView;
    private ColorFragmentAdapter recyclerViewAdapter;
    ArrayList<ColorModel> mycolor;
    private int[] colors;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_color, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_viewcolor);
        lovColor();
        setUpRecyclerView();
        return view;
    }


    private void setUpRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recyclerViewAdapter = new ColorFragmentAdapter(this.getActivity().getApplicationContext(), mycolor);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    private void lovColor(){
        mycolor = new ArrayList<ColorModel>();
        colors = context.getResources().getIntArray(R.array.colorArray);
        for (int i = 0; i < colors.length; i++) {
            mycolor.add(new ColorModel(colors[i]));
        }
    }

}