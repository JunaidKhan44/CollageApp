package com.tas.collagemaker.photoeditor.piccollage.photoframe.textstickerfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.tas.collagemaker.photoeditor.piccollage.photoframe.R;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.freestyletextsticker.TextStickerFreeStyleModel;

import java.util.ArrayList;


public class FragText extends Fragment {


    public RecyclerView recyclerView;
    private FragTextAdapter recyclerViewAdapter;
    ArrayList<FragTextModel> textModel;
    private  String[] fontsArray;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_frag_text, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_viewtext);
        lovFonts();
        setUpRecyclerView();
        return view;
    }

    private void setUpRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recyclerViewAdapter = new FragTextAdapter(this.getActivity().getApplicationContext(), textModel);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    private void lovFonts(){
        textModel = new ArrayList<FragTextModel>();
        fontsArray = getResources().getStringArray(R.array.fontsArray);
        for (int i = 0; i < fontsArray.length; i++) {
            textModel.add(new FragTextModel(fontsArray[i]));
        }
    }
}