package vn.edu.usth.usthweather;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ForecastFragment extends Fragment {

    public ForecastFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        TextView day = new TextView(getContext());
//        day.setText("thursday");
//
//        ImageView img = new ImageView(getContext());
//        img.setImageResource(R.drawable.sun);
//
//        View v = new View(getContext());
//        v.setBackgroundColor(0x20FF0000);
//
//        LinearLayout view = new LinearLayout(getContext());
//        view.setOrientation(LinearLayout.VERTICAL);
//        view.addView(day);
//        view.addView(img);
//        view.addView(v);
//        return view;
        return inflater.inflate(R.layout.fragment_forecast, container, false);
    }
}