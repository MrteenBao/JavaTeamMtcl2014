package com.phuvg.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.phuvg.Object.ObjectTemp;
import com.phuvg.myui1.CheeseDetailActivity;
import com.phuvg.myui1.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Vuong Gia Phu
 */
public class FragmentAdapterWithObject extends RecyclerView.Adapter<FragmentAdapterWithObject.ViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<ObjectTemp> mValues;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public String s1;
        public String s2;
        public int n1;

        public final View mView;
        public final ImageView mImageView;
        public final TextView mTextView;
        public final TextView mTextView2;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.avatar_adapter_list_object);
            mTextView = (TextView) view.findViewById(R.id.text1_item_avatar_size);
            mTextView2 = (TextView) view.findViewById(R.id.text2_item_avatar_size);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextView.getText();
        }
    }

    public ObjectTemp getValueAt(int position) {
        return mValues.get(position);
    }

    // Ham khoi tao cua Adapter
    public FragmentAdapterWithObject(Context context, List<ObjectTemp> items) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        mValues = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Goi ten layout adapter
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_adapter_with_list_object, parent, false);
        view.setBackgroundResource(mBackground);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        ObjectTemp Ob = mValues.get(position);
        holder.s1 = Ob.getS1();
        holder.s2 = Ob.getS2();
        holder.n1 = Ob.getN1();


        // Hien gia tri len TextView
        holder.mTextView.setText( holder.s1);
        holder.mTextView2.setText( holder.s2);

        // list vao tung item
        // Goi Activity len
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, CheeseDetailActivity.class);
                intent.putExtra(CheeseDetailActivity.EXTRA_NAME, holder.s1);
                intent.putExtra("tenHinh", holder.s2);
                context.startActivity(intent);
            }
        });

        // lay Image tu asset folder
        Bitmap b = loadImageFromAsset(this.context, holder.s2);
        holder.mImageView.setImageBitmap(b);

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

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}