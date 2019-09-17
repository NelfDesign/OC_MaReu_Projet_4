package fr.nelfdesign.mareu.Controllers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.nelfdesign.mareu.Models.Reunion;
import fr.nelfdesign.mareu.R;

/**
 * Created by Nelfdesign at 15/09/2019
 * fr.nelfdesign.mareu.Controllers
 */
public class ReunionListAdapter extends RecyclerView.Adapter<ReunionListAdapter.ViewHolder> {

    List<Reunion> mReunionList;

    public ReunionListAdapter(List<Reunion> reunionList) {
        mReunionList = reunionList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_salle)
        public ImageView mImageSalle;
        @BindView(R.id.text_reu)
        public TextView mTextReu;
        @BindView(R.id.date_reunion)
        public TextView mDate;
        @BindView(R.id.hour_reunion)
        public TextView mHour;
        @BindView(R.id.text_mail)
        public TextView mTextMail;
        @BindView(R.id.icon_delete)
        public ImageButton mDeleteButton;
        //constructor
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reunion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Reunion reunion = mReunionList.get(position);
        viewHolder.mTextReu.setText(reunion.getReunionObject());
        if (reunion.getDate() == null){
            return;
        }else{
            viewHolder.mDate.setText(reunion.getDate());
        }
        viewHolder.mHour.setText("-" + reunion.getTime() + " h");

        Glide.with(viewHolder.mImageSalle.getContext())
        .load(reunion.getRoom())
        .apply(RequestOptions.circleCropTransform())
        .into(viewHolder.mImageSalle);

        if (reunion.getMail() == null){
            return;
        }else {
            viewHolder.mTextMail.setText(reunion.getMail().toString());
        }

    }

    @Override
    public int getItemCount() {
        return this.mReunionList.size();
    }

}
