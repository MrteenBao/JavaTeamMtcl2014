/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vinhtq.myui1;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;

public class CheeseDetailActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "cheese_name";
    TextView txtContent1;

    String tenHinh;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtContent1 = (TextView) findViewById(R.id.txtContent1);
        Intent intent = getIntent();
        final String cheeseName = intent.getStringExtra(EXTRA_NAME);
        tenHinh = intent.getStringExtra("tenHinh");

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(cheeseName);

        loadBackdrop();

        txtContent1.setText("" +
                " bla bla bla bla bla bla bla bla " +
                " bla bla bla bla bla bla bla bla bla bla bla" +
                " bla bla bla bla bla bla bla bla bla bla bla" +
                " bla bla bla bla bla bla bla bla bla bla bla" +
                " bla bla bla bla bla");
    }

    // Thiet lap anh banner cho Activity
    private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Bitmap b = loadImageFromAsset(CheeseDetailActivity.this, tenHinh);
        imageView.setImageBitmap(b);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    // Ham lay Image tu asset folder
    private Bitmap loadImageFromAsset(Context context, String tenHinh){
        InputStream istr = null;
        try {
            AssetManager assetManager = context.getAssets();
            istr = assetManager.open(tenHinh+".jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(istr);
        return bitmap;
    }
}
