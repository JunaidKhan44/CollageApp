package com.tas.collagemaker.photoeditor.piccollage.photoframe.imagefragment;

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


public class ImageFragment extends Fragment {

    public RecyclerView recyclerView;
    private ImageFragAdapter recyclerViewAdapter;
    private ArrayList<ImageFragmentModel> imgList;

    private TypedArray frames;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_image, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_viewimg);
        imgList=new ArrayList<ImageFragmentModel>();
        imgList.add(new ImageFragmentModel(R.drawable.frame1));
        lovFrames();
        setUpRecyclerView();


        return view;

    }


    private void setUpRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        recyclerViewAdapter = new ImageFragAdapter(this.getActivity().getApplicationContext(), imgList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    private void lovFrames(){
        frames = getResources().obtainTypedArray(R.array.frameArray);
        for (int i = 0; i < frames.length(); i++) {
            imgList.add(new ImageFragmentModel(frames.getResourceId(i,0)));

        }
    }

}