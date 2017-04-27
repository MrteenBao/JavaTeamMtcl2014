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

package com.phuvg.myui1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.phuvg.Object.ObjectTemp;
import com.phuvg.adapter.FragmentAdapterWithObject;

import java.util.ArrayList;

public class CheeseListFragment extends Fragment {

//    // List demo
//    ArrayList<ObjectTemp> listItem2 = new ArrayList<ObjectTemp>();
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        initListObject();
//        RecyclerView rv = (RecyclerView) inflater.inflate( R.layout.fragment_cheese_list, container, false);
//        setupRecyclerView(rv);
//        return rv;
//    }
//
//    // Tao list Object demo
//    private void initListObject(){
//        listItem2.clear();
//        ObjectTemp o = new ObjectTemp("Co ho 1", "hinh_1", 1);
//        ObjectTemp o1 = new ObjectTemp("Co ho 2", "hinh_2", 2);
//        ObjectTemp o2 = new ObjectTemp("Co ho 3", "hinh_3", 3);
//        ObjectTemp o3 = new ObjectTemp("Co ho 4", "hinh_4", 4);
//        ObjectTemp o4 = new ObjectTemp("Co ho 5", "hinh_5", 5);
//        ObjectTemp o5 = new ObjectTemp("Co ho 6", "hinh_6", 6);
//        ObjectTemp co = new ObjectTemp("Co ho 1", "hinh_1", 1);
//        ObjectTemp co1 = new ObjectTemp("Co ho 2", "hinh_2", 2);
//        ObjectTemp co2 = new ObjectTemp("Co ho 3", "hinh_3", 3);
//        ObjectTemp co3 = new ObjectTemp("Co ho 4", "hinh_4", 4);
//        ObjectTemp co4 = new ObjectTemp("Co ho 5", "hinh_5", 5);
//        ObjectTemp co5 = new ObjectTemp("Co ho 6", "hinh_6", 6);
//
//        listItem2.add(o);
//        listItem2.add(o1);
//        listItem2.add(o2);
//        listItem2.add(o3);
//        listItem2.add(o4);
//        listItem2.add(o5);
//
//        listItem2.add(co);
//        listItem2.add(co1);
//        listItem2.add(co2);
//        listItem2.add(co3);
//        listItem2.add(co4);
//        listItem2.add(co5);
//
//    }
//
//
//    private void setupRecyclerView(RecyclerView recyclerView) {
//        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
//        recyclerView.setAdapter(new FragmentAdapterWithObject(getActivity(),listItem2));
//
//
//    }


}
