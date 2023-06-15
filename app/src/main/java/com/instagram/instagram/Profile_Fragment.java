package com.instagram.instagram;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Profile_Fragment extends Fragment {

    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile_, container, false);
        RecyclerView day_recycler_view_day = view.findViewById(R.id.day_recycler_view_day);
        RecyclerView recycler_view_mother_image = view.findViewById(R.id.recycler_view_mother_image);

        PAdapter adapter = new PAdapter();
        day_recycler_view_day.setAdapter(adapter);
        day_recycler_view_day.setLayoutManager(new LinearLayoutManager(getActivity()));
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        day_recycler_view_day.setLayoutManager(horizontalLayoutManagaer);



         String URL = "https://asifislamjhfgyu.000webhostapp.com/Apps/homeinfo.json";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {

                    for (int x=0; x<response.length(); x++){

                        JSONObject jsonObject = response.getJSONObject(x);
                        String IMAGE = jsonObject.getString("profilepic_url");

                        hashMap = new HashMap<>();
                        hashMap.put("image", IMAGE);
                        arrayList.add(hashMap);


                        MAdapter mAdapter = new MAdapter();
                        recycler_view_mother_image.setAdapter(mAdapter);
                        recycler_view_mother_image.setLayoutManager( new LinearLayoutManager(getActivity()));
                        recycler_view_mother_image.setLayoutManager(new GridLayoutManager(getActivity(), 3));

                    }


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(jsonArrayRequest);


        return view;

    }


    public class PAdapter extends RecyclerView.Adapter<PAdapter.Pholder>{

        @NonNull
        @Override
        public Pholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.day_item, parent, false);




            return new Pholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Pholder holder, int position) {


        }

        @Override
        public int getItemCount() {
            return 10;
        }

        public class Pholder extends RecyclerView.ViewHolder{


            public Pholder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }


    public class MAdapter extends RecyclerView.Adapter<MAdapter.Mholder>{

        public class Mholder extends RecyclerView.ViewHolder{

            ImageView image_cover;
            public Mholder(@NonNull View itemView) {
                super(itemView);

                image_cover = itemView.findViewById(R.id.image_cover);

            }
        }

        @NonNull
        @Override
        public Mholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.moter_image_item, parent, false);

            return new Mholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Mholder holder, int position) {

            HashMap<String, String> hashMap = arrayList.get(position);
            String MY_IMAGE = hashMap.get("image");

            Picasso.get().load(MY_IMAGE).
                    into(holder.image_cover);

            holder.image_cover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }


    }

}