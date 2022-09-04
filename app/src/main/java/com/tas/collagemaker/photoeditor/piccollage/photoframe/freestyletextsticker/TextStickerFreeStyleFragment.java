package com.tas.collagemaker.photoeditor.piccollage.photoframe.freestyletextsticker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.tas.collagemaker.photoeditor.piccollage.photoframe.R;

import java.util.ArrayList;

public class TextStickerFreeStyleFragment extends Fragment {


    public RecyclerView recyclerView;
    private TextStickerFreeStyleAdapter recyclerViewAdapter;
    ArrayList<TextStickerFreeStyleModel> modelList;
    private String[] fontsArray;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_textsticker_free_style, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_viewtextfree);
        lovFonts();
        setUpRecyclerView();
        return view;

    }


    private void setUpRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recyclerViewAdapter = new TextStickerFreeStyleAdapter(this.getActivity().getApplicationContext(), modelList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    private void lovFonts(){
        modelList = new ArrayList<TextStickerFreeStyleModel>();
        fontsArray= getResources().getStringArray(R.array.fontsArray);
        for (int i = 0; i < fontsArray.length; i++) {
            modelList.add(new TextStickerFreeStyleModel(fontsArray[i]));
        }
    }
}