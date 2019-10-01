package fr.nelfdesign.mareu.Controllers.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import fr.nelfdesign.mareu.Models.RoomItemSpinner;
import fr.nelfdesign.mareu.R;

/**
 * Created by Nelfdesign at 17/09/2019
 * fr.nelfdesign.mareu.Controllers
 */
public class RoomAdapter extends ArrayAdapter<RoomItemSpinner> {

    public RoomAdapter(@NonNull Context context, ArrayList<RoomItemSpinner> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.room_spinner_row, parent, false);
        }
        ImageView imageView = convertView.findViewById(R.id.image_room);
        TextView textView = convertView.findViewById(R.id.name_room);

        RoomItemSpinner currentItem = getItem(position);

        if (currentItem != null){
            imageView.setImageResource(currentItem.getRoomImage());
            textView.setText(currentItem.getRoomName());
        }

        return convertView;
    }
}
