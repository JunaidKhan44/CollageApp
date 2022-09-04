package com.tas.collagemaker.photoeditor.piccollage.photoframe.freestyletextsticker;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.tas.collagemaker.photoeditor.piccollage.photoframe.R;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.interfaces.IRecyclerClickListener;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.otherclasses.FreeStyle;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork.DeleteIconEvent;


import java.util.ArrayList;

public class TextStickerFreeStyleAdapter extends RecyclerView.Adapter<TextStickerFreeStyleAdapter.ViewHolder> {

    public Context context;
    public ArrayList<TextStickerFreeStyleModel> fragTextModels;
    private int mDefaultColor = R.color.aqua;

    public TextStickerFreeStyleAdapter(Context context, ArrayList<TextStickerFreeStyleModel> fragTextModels) {
        this.context = context;
        this.fragTextModels = fragTextModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowitem_textfree, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.setListener((view, pos) -> {
            if(DeleteIconEvent.flagdeletefree){
                Toast.makeText(context, "some think went wrong...", Toast.LENGTH_SHORT).show();
            }
            else {
                if (FreeStyle.checkEmptyTxtStickerFree) {
                    switch (position) {
                        case 0:
                            FreeStyle.stickerForTextFree.setTypeface(Typeface.createFromAsset(context.getAssets(), "Montserrat-Light.otf"));
                            setFreeStyleStickerFormat();

                            return;
                        case 1:

                            FreeStyle.stickerForTextFree.setTypeface(Typeface.createFromAsset(context.getAssets(), "Montserrat-Bold.otf"));
                            setFreeStyleStickerFormat();

                            return;
                        case 2:

                            FreeStyle.stickerForTextFree.setTypeface(Typeface.createFromAsset(context.getAssets(), "Etalasi.otf"));
                            setFreeStyleStickerFormat();

                            return;
                        case 3:

                            FreeStyle.stickerForTextFree.setTypeface(Typeface.createFromAsset(context.getAssets(), "Oi-Regular.ttf"));
                            setFreeStyleStickerFormat();

                            return;
                        case 4:

                            FreeStyle.stickerForTextFree.setTypeface(Typeface.createFromAsset(context.getAssets(), "OswaldHeavy.ttf"));
                            setFreeStyleStickerFormat();

                            return;
                        case 5:
                            FreeStyle.stickerForTextFree.setTypeface(Typeface.createFromAsset(context.getAssets(), "MaskedHero.ttf"));
                            setFreeStyleStickerFormat();

                            return;
                        case 6:
                            FreeStyle.stickerForTextFree.setTypeface(Typeface.createFromAsset(context.getAssets(), "RodanoItalic.otf"));
                            setFreeStyleStickerFormat();
                            return;

                        default:

                    }
                } else {
                    Toast.makeText(context, "No sticker", Toast.LENGTH_SHORT).show();
                }
            }


        });
        switch (position) {
            case 0:

                Typeface face1 = Typeface.createFromAsset(context.getAssets(),
                        "Montserrat-Light.otf");
                holder.text.setTypeface(face1);
                holder.textStyle.setText(fragTextModels.get(position).getTxtStyle());

                return;
            case 1:
                Typeface face2 = Typeface.createFromAsset(context.getAssets(),
                        "Montserrat-Bold.otf");
                holder.text.setTypeface(face2);
                holder.textStyle.setText(fragTextModels.get(position).getTxtStyle());


                return;
            case 2:

                Typeface face3 = Typeface.createFromAsset(context.getAssets(),
                        "Etalasi.otf");
                holder.text.setTypeface(face3);
                holder.textStyle.setText(fragTextModels.get(position).getTxtStyle());

                return;
            case 3:
                Typeface face4 = Typeface.createFromAsset(context.getAssets(),
                        "Oi-Regular.ttf");
                holder.text.setTypeface(face4);
                holder.textStyle.setText(fragTextModels.get(position).getTxtStyle());
                return;
            case 4:
                Typeface face5 = Typeface.createFromAsset(context.getAssets(),
                        "OswaldHeavy.ttf");
                holder.text.setTypeface(face5);
                holder.textStyle.setText(fragTextModels.get(position).getTxtStyle());
                return;
            case 5:
                Typeface face7 = Typeface.createFromAsset(context.getAssets(),
                        "MaskedHero.ttf");
                holder.text.setTypeface(face7);
                holder.textStyle.setText(fragTextModels.get(position).getTxtStyle());
                return;
            case 6:
                Typeface face8 = Typeface.createFromAsset(context.getAssets(),
                        "RodanoItalic.otf");
                holder.text.setTypeface(face8);
                holder.textStyle.setText(fragTextModels.get(position).getTxtStyle());
                return;

            default:

                holder.textStyle.setText(fragTextModels.get(position).getTxtStyle());
        }
    }

    private void setFreeStyleStickerFormat() {
        FreeStyle.stickerForTextFree.setText(FreeStyle.stickerForTextFree.getText());
        FreeStyle.stickerForTextFree.setTextColor(FreeStyle.localcolor);
        FreeStyle.stickerForTextFree.setTextAlign(Layout.Alignment.ALIGN_CENTER);
        FreeStyle.stickerForTextFree.resizeText();
        FreeStyle.stickerViewFree.addSticker(FreeStyle.stickerForTextFree);
    }

    @Override
    public int getItemCount() {
        return fragTextModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView text, textStyle;
        public RelativeLayout cardText;

        IRecyclerClickListener listener;

        public void setListener(IRecyclerClickListener listener) {
            this.listener = listener;
        }

        public ViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.txt);
            textStyle = itemView.findViewById(R.id.stylename);
            cardText = (RelativeLayout) itemView.findViewById(R.id.cardtextfree);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            listener.onCLick(v, getAdapterPosition());
        }
    }
}
