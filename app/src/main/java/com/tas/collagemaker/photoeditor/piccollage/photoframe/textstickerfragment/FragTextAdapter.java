package com.tas.collagemaker.photoeditor.piccollage.photoframe.textstickerfragment;

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
import com.tas.collagemaker.photoeditor.piccollage.photoframe.otherclasses.MainCanvas;

import com.tas.collagemaker.photoeditor.piccollage.photoframe.interfaces.IRecyclerClickListener;
import com.tas.collagemaker.photoeditor.piccollage.photoframe.stickerwork.DeleteIconEvent;

import java.util.ArrayList;

import static com.tas.collagemaker.photoeditor.piccollage.photoframe.otherclasses.MainCanvas.stickerView;

public class FragTextAdapter extends RecyclerView.Adapter<FragTextAdapter.ViewHolder> {

    public Context context;
    public ArrayList<FragTextModel> fragTextModels;

    public FragTextAdapter(Context context, ArrayList<FragTextModel> fragTextModels) {
        this.context = context;
        this.fragTextModels = fragTextModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowitem_text, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.setListener((view, pos) -> {

            if(DeleteIconEvent.flagdelete){
                Toast.makeText(context, "some think went wrong...", Toast.LENGTH_SHORT).show();
            }
            else {
                if (MainCanvas.checkEmptyTxtSticker) {
                    switch (position) {
                        case 0:
                        MainCanvas.stickerForText.setTypeface(Typeface.createFromAsset(context.getAssets(), "Montserrat-Light.otf"));
                         setTextStickerFormat();
                            return;
                        case 1:
                            MainCanvas.stickerForText.setTypeface(Typeface.createFromAsset(context.getAssets(), "Montserrat-Bold.otf"));
                            setTextStickerFormat();
                            return;
                        case 2:
                            MainCanvas.stickerForText.setTypeface(Typeface.createFromAsset(context.getAssets(), "Etalasi.otf"));
                            setTextStickerFormat();
                            return;
                        case 3:
                            MainCanvas.stickerForText.setTypeface(Typeface.createFromAsset(context.getAssets(), "Oi-Regular.ttf"));
                            setTextStickerFormat();
                            return;
                        case 4:
                            MainCanvas.stickerForText.setTypeface(Typeface.createFromAsset(context.getAssets(), "OswaldHeavy.ttf"));
                            setTextStickerFormat();
                            return;
                        case 5:
                            MainCanvas.stickerForText.setTypeface(Typeface.createFromAsset(context.getAssets(), "MaskedHero.ttf"));
                            setTextStickerFormat();
                            return;
                        case 6:
                            MainCanvas.stickerForText.setTypeface(Typeface.createFromAsset(context.getAssets(), "RodanoItalic.otf"));
                            setTextStickerFormat();
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

    private void setTextStickerFormat() {
        MainCanvas.stickerForText.setText(MainCanvas.stickerForText.getText());
        MainCanvas.stickerForText.setTextColor(MainCanvas.localColor);
        MainCanvas.stickerForText.setTextAlign(Layout.Alignment.ALIGN_CENTER);
        MainCanvas.stickerForText.resizeText();
        stickerView.addSticker(MainCanvas.stickerForText);
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
            cardText = (RelativeLayout) itemView.findViewById(R.id.cardtext);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onCLick(v, getAdapterPosition());
        }
    }



}
