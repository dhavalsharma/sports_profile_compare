package com.dhavalsharma.compare;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HeroFragment extends Fragment {
    private ImageView playerImageView;
    private TextView nameTextView;
    private ImageButton trophyImage;
    private ImageButton viewProfileButton;
    private int drawableId;

    public HeroFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hero, container, false);
        playerImageView = (ImageView) view.findViewById(R.id.imageViewPlayer);
        nameTextView = (TextView) view.findViewById(R.id.textViewName);
        trophyImage = (ImageButton) view.findViewById(R.id.trophyImage);
        viewProfileButton = (ImageButton) view.findViewById(R.id.viewAccountId);
        return view;
    }


    /*
    helper to set image of drawable
    */
    public void setImageDrawable(int drawableIdParam) {
        drawableId = drawableIdParam;
        Drawable drawable = ContextCompat.getDrawable(getActivity(), drawableId);
        playerImageView.setImageDrawable(drawable);
    }

    public void setName(final String name) {
        nameTextView.setText(name);
        nameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show profile
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("drawableId", drawableId);
                startActivity(intent);
            }
        });
        viewProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show profile
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("drawableId", drawableId);
                startActivity(intent);
            }
        });
    }

    public void showCup(boolean show) {
        if (show) {
            trophyImage.setVisibility(View.VISIBLE);
        } else {
            trophyImage.setVisibility(View.GONE);
        }
    }

}
