package com.antherx.prasenjithiwale.ihero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.antherx.prasenjithiwale.ihero.activity.SwipeDeckAdapter;
import com.daprlabs.aaron.swipedeck.SwipeDeck;

import java.util.ArrayList;
import java.util.List;

public class SwipeCard extends AppCompatActivity {

    SwipeDeck swipeDeck;
    ArrayList testData;
    SwipeDeckAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_card);

        swipeDeck = (SwipeDeck)findViewById(R.id.swipe_deck);
         testData = new ArrayList<>();
        testData.add("0");
        testData.add("1");
        testData.add("2");
        testData.add("3");
        testData.add("4");

        adapter = new SwipeDeckAdapter(testData, this);
        if (swipeDeck != null){
            swipeDeck.setAdapter(adapter);
        }

        swipeDeck.setCallback(new SwipeDeck.SwipeDeckCallback() {
            @Override
            public void cardSwipedLeft(long stableId) {
                Toast.makeText(SwipeCard.this, "Card swiped Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void cardSwipedRight(long stableId) {
                Toast.makeText(SwipeCard.this, "Card swiped Right", Toast.LENGTH_SHORT).show();
            }
        });

        swipeDeck.setLeftImage(R.id.left_image);
        swipeDeck.setRightImage(R.id.right_image);


        ImageView goBack = (ImageView) findViewById(R.id.add);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}



