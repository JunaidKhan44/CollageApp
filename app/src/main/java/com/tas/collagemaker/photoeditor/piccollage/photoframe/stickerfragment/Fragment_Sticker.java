package com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerfragment;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.tas.collagemaker.photoeditor.piccollage.photoframe.R;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.gradientfragment.GradientModel;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.textstickerfragment.FragTextModel;

import java.util.ArrayList;


public class Fragment_Sticker extends Fragment {

    public RecyclerView recyclerView;

    private FragStickerAdapter recyclerViewAdapter;
    private ArrayList<FragStickerModel> stickerList;
    private TypedArray stickerArrays;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment__sticker, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_viewsticker);
        lovStickers();
        setUpRecyclerView();
        return view;
    }

    private void setUpRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        recyclerViewAdapter = new FragStickerAdapter(this.getActivity().getApplicationContext(), stickerList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    private void lovStickers(){
        stickerList=new ArrayList<FragStickerModel>();
        stickerArrays= getResources().obtainTypedArray(R.array.gradientArray);
        for (int i = 0; i < stickerArrays.length(); i++) {
            stickerList.add(new FragStickerModel(stickerArrays.getResourceId(i,0)));

        }

    }

}