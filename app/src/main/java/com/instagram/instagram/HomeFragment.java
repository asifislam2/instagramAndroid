package com.instagram.instagram;

import static androidx.browser.customtabs.CustomTabsClient.getPackageName;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeFragment extends Fragment {

    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap = new HashMap<>();
    ArrayList<HashMap<String, String>> arrayListone = new ArrayList<>();
    HashMap<String, String> hashMapone = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView day_recycler_view = view.findViewById(R.id.day_recycler_view);
        RecyclerView recycler_view_mother = view.findViewById(R.id.recycler_view_mother);


        String URL = "https://asifislamjhfgyu.000webhostapp.com/Apps/instainfo.json";
       JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
           @Override
           public void onResponse(JSONArray response) {


               try {

                   for (int x=0; x<response.length(); x++){

                       JSONObject jsonObject = response.getJSONObject(x);
                       String DAY_IMG = jsonObject.getString("coverimage");
                       String USER_NAME = jsonObject.getString("dayname");

                       hashMap = new HashMap<>();
                       hashMap.put("DAY_IMAGE", DAY_IMG);
                       hashMap.put("USER_NAME", USER_NAME);
                       arrayList.add(hashMap);

                       XAdapter adapter = new XAdapter();
                       day_recycler_view.setAdapter(adapter);
                       day_recycler_view.setLayoutManager( new LinearLayoutManager(getActivity()));
                       LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                       day_recycler_view.setLayoutManager(horizontalLayoutManagaer);

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

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);



        String url = "https://asifislamjhfgyu.000webhostapp.com/Apps/homeinfo.json";
        JsonArrayRequest jsonArrayRequestone = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {

                    for (int i=0; i<response.length(); i++){

                        JSONObject jsonObject = response.getJSONObject(i);
                        String PROFILE_IMAGE = jsonObject.getString("profilepic_url");
                        String PROFILE_NAME = jsonObject.getString("profile_name");
                        String LIKE = jsonObject.getString("like");
                        String DESCRIPTION = jsonObject.getString("description");


                        hashMapone = new HashMap<>();
                        hashMapone.put("profile_image", PROFILE_IMAGE);
                        hashMapone.put("profile_name", PROFILE_NAME);
                        hashMapone.put("like", LIKE);
                        hashMapone.put("des", DESCRIPTION);
                        arrayListone.add(hashMapone);

                        MAdapter mAdapter = new MAdapter();
                        recycler_view_mother.setAdapter(mAdapter);
                        recycler_view_mother.setLayoutManager( new LinearLayoutManager(getActivity()));


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


        RequestQueue requestQueueone = Volley.newRequestQueue(getActivity());
        requestQueueone.add(jsonArrayRequestone);





        return view;

    }

    public class XAdapter extends RecyclerView.Adapter<XAdapter.XHolder>{

        public class XHolder extends RecyclerView.ViewHolder{

            CircleImageView item_day_image;
            TextView user_name;
            public XHolder(@NonNull View itemView) {
                super(itemView);

                item_day_image = itemView.findViewById(R.id.item_day_image);
                user_name = itemView.findViewById(R.id.user_name);
            }
        }

        @NonNull
        @Override
        public XHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.item_day, parent, false);


            return new XHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull XHolder holder, int position) {

            HashMap<String, String> hashMap = arrayList.get(position);
            String DAY_IMAGES = hashMap.get("DAY_IMAGE");
            String USER_NAME = hashMap.get("USER_NAME");

            holder.user_name.setText(USER_NAME);

            Picasso.get().load(DAY_IMAGES)
                    .placeholder(R.drawable.loading)
                    .into(holder.item_day_image);


            holder.item_day_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    LoadImage.TITLE_NAME = USER_NAME;
                    Bitmap bitmap =( (BitmapDrawable)holder.item_day_image.getDrawable() ).getBitmap();
                    LoadImage.MY_BITMAP = bitmap;

                    Bitmap bitmapone =( (BitmapDrawable)holder.item_day_image.getDrawable() ).getBitmap();
                    LoadImage.MY_BITMAPONE = bitmapone;

                    Intent intent = new Intent(getActivity(), LoadImage.class);
                    startActivity(intent);


                }
            });

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

    }

    // first adapter end here


    public class MAdapter extends RecyclerView.Adapter<MAdapter.MHolder>{

        public class MHolder extends RecyclerView.ViewHolder{

            CircleImageView item_profile_image;
            TextView profile_name, likes,show_desc, textView;

            ImageView post_image, share_image, react_image;

            RelativeLayout mother_layout;
            public MHolder(@NonNull View itemView) {

                super(itemView);

                item_profile_image = itemView.findViewById(R.id.item_profile_image);
                profile_name = itemView.findViewById(R.id.profile_name);
                post_image = itemView.findViewById(R.id.post_image);
                likes = itemView.findViewById(R.id.likes);
                show_desc = itemView.findViewById(R.id.show_desc);
                share_image = itemView.findViewById(R.id.share_image);

                textView  = itemView.findViewById(R.id.show_desc);
                mother_layout  = itemView.findViewById(R.id.mother_layout);
                react_image  = itemView.findViewById(R.id.react_image);



            }
        }

        @NonNull
        @Override
        public MHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.item_mother, parent, false);


            RelativeLayout mother_layout = view.findViewById(R.id.mother_layout);




            return new MHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MHolder holder, int position) {

            HashMap<String, String > hashMapone = arrayListone.get(position);
            String PROFILE_IMAGE = hashMapone.get("profile_image");
            String PROFILE_NAME = hashMapone.get("profile_name");
            String LIKE = hashMapone.get("like");
            String DESCRIPTION = hashMapone.get("des");

            Picasso.get().load(PROFILE_IMAGE)
                    .into(holder.item_profile_image);

            Picasso.get().load(PROFILE_IMAGE)
                    .placeholder(R.drawable.loading)
                    .into(holder.post_image);

            holder.profile_name.setText(PROFILE_NAME);
            holder.likes.setText(LIKE);
            holder.show_desc.setText(DESCRIPTION);

            holder.share_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    BitmapDrawable bitmapDrawable = (BitmapDrawable)holder.post_image.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();

                    shareImageandText(bitmap);


                }
            });


            holder.react_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (holder.react_image.getTag() !=null && holder.react_image.getTag().toString().contains("unclick")){

                        holder.react_image.setImageResource(R.drawable.deeplove);
                        holder.react_image.setTag("active");

                    }else {

                        holder.react_image.setImageResource(R.drawable.loveresize);
                        holder.react_image.setTag("unclick");

                    }






                }
            });

            String originalText = holder.textView.getText().toString();

            holder.textView.setOnClickListener(new View.OnClickListener() {
                boolean isExpanded = false;

                @Override
                public void onClick(View v) {
                    isExpanded = !isExpanded;

                    if (isExpanded) {
                        TransitionManager.beginDelayedTransition(holder.mother_layout, new AutoTransition());
                        holder.textView.setText(originalText);
                        holder.textView.setEllipsize(null);  // Remove the ellipsis
                        holder.textView.setSingleLine(false); // Allow multiple lines
                    } else {
                        TransitionManager.beginDelayedTransition(holder.mother_layout, new AutoTransition());
                        holder.textView.setEllipsize(TextUtils.TruncateAt.END); // Reapply ellipsis
                        holder.textView.setSingleLine(true); // Restrict to single line
                        holder.textView.setText(originalText);
                    }
                }
            });



        }

        @Override
        public int getItemCount() {
            return arrayListone.size();
        }


    }

    private void shareImageandText(Bitmap bitmap) {
        Uri uri = getmageToShare(bitmap);
        Intent intent = new Intent(Intent.ACTION_SEND);

        // putting uri of image to be shared
        intent.putExtra(Intent.EXTRA_STREAM, uri);

        // adding text to share
        intent.putExtra(Intent.EXTRA_TEXT, "Sharing Image");

        // Add subject Here
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");

        // setting type to image
        intent.setType("image/png");

        // calling startactivity() to share
        startActivity(Intent.createChooser(intent, "Share Via"));
    }

    // Retrieving the url to share
    private Uri getmageToShare(Bitmap bitmap) {
        File imagefolder = new File(getCacheDir(), "images");
        Uri uri = null;
        try {
            imagefolder.mkdirs();
            File file = new File(imagefolder, "shared_image.png");
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream);
            outputStream.flush();
            outputStream.close();
            uri = FileProvider.getUriForFile(getActivity(), "com.anni.shareimage.fileprovider", file);
        } catch (Exception e) {

        }
        return uri;
    }
    private File getCacheDir() {
        return null;
    }


}