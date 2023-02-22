package com.barmej.interactiveapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /**
     * Constant for Log messages
     */
    private static final String TAG = MainActivity.class.getSimpleName();

    /**
     * Constant used as bundle key
     */
    private static final String BUNDLE_CURRENT_INDEX = "BUNDLE_CURRENT_INDEX";

    /**
     * Instance of Random class used to generate random index
     */
    private Random mRandom;

    /**
     * ImageView to display activity image
     */
    private ImageView mImageView;

    /**
     * TextView to display activity name
     */
    private TextView mTextView;


    /**
     * Array that hold drawable images ids
     */
    private final int[] mPictures = {
            R.drawable.park,
            R.drawable.beach,
            R.drawable.bike,
            R.drawable.football,
            R.drawable.museum,
            R.drawable.restaurant,
            R.drawable.running,
            R.drawable.shop,
            R.drawable.swimming,
            R.drawable.walking
    };

    /**
     * Array that hold strings resources ids
     */
    private final int[] mNames = {
            R.string.park,
            R.string.beach,
            R.string.bike,
            R.string.football,
            R.string.museum,
            R.string.restaurant,
            R.string.running,
            R.string.shop,
            R.string.swimming,
            R.string.walking
    };

    /**
     * Variable used as index to move through images array
     */
    private int mCurrentIndex = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create new Random object
        mRandom = new Random();
        // Get imageView from view hierarchy
        mImageView = findViewById(R.id.imageView);
        // Get textView from view hierarchy
        mTextView = findViewById(R.id.textView);
        Log.i(TAG, "Created");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(BUNDLE_CURRENT_INDEX)) {
            mCurrentIndex = savedInstanceState.getInt(BUNDLE_CURRENT_INDEX);
            if (mCurrentIndex != -1) {
                showSuggestion();
            }
        }
        Log.i(TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_CURRENT_INDEX, mCurrentIndex);
        Log.i(TAG, "onSaveInstanceState");
    }

    /**
     * Show random activity suggestion
     *
     * @param view the view that's being clicked
     */
    public void showRandomSuggestion(View view) {
        // Generate random index
        mCurrentIndex = mRandom.nextInt(mPictures.length);
        showSuggestion();
    }

    /**
     * Show the next activity suggestion
     *
     * @param view the view that's being clicked
     */
    public void nextSuggestion(View view) {
        // If counter exceed the last index in the array
        if (mCurrentIndex >= mPictures.length - 1) {
            // Reset the counter
            mCurrentIndex = -1;
        }
        // Increase the counter by one
        mCurrentIndex++;
        showSuggestion();
    }

    /**
     * Show the previous activity suggestion
     *
     * @param view the view that's being clicked
     */
    public void previousSuggestion(View view) {
        // If counter reach the first index
        if (mCurrentIndex <= 0) {
            // Jump to last index
            mCurrentIndex = mPictures.length;
        }
        // Decrease the counter by one
        mCurrentIndex--;
        showSuggestion();
    }

    /**
     * Show the activity suggestion
     */
    private void showSuggestion() {
        Drawable imageDrawable = ContextCompat.getDrawable(this, mPictures[mCurrentIndex]);
        mImageView.setImageDrawable(imageDrawable);
        String name = getString(mNames[mCurrentIndex]);
        mTextView.setText(name);
    }

}
