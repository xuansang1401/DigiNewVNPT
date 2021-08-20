 package com.gpaddy.baseandroid.theu.ttvnpt;

 import android.content.Intent;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.EditText;
 import android.widget.ImageView;
 import android.widget.Toast;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.appcompat.widget.Toolbar;
 import androidx.recyclerview.widget.GridLayoutManager;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

 import java.util.ArrayList;
 import java.util.Date;

 import com.gpaddy.baseandroid.R;
 import com.gpaddy.baseandroid.theu.ttvnpt.*;
 import com.gpaddy.baseandroid.theu.model.*;
 import com.gpaddy.baseandroid.theu.DAO.*;
 import com.gpaddy.baseandroid.theu.adapter.IClickListener;
 import com.gpaddy.baseandroid.theu.adapter.*;
 import com.gpaddy.baseandroid.theu.api.*;
 import retrofit2.Call;
 import retrofit2.Callback;
 import retrofit2.Response;

 public class timkiem extends AppCompatActivity {
     Toolbar toolbar;
     EditText ed1;
     ImageView bt;
     RecyclerView recytuhot,recytimkiemganday;

     private RecyclerView.LayoutManager layoutManager1,layoutManager2;

     //TextView tv1,tv2,tv3;
     //TextView ls1,ls2,ls3,ls4,ls5,ls6,ls7,ls8,ls9,ls10,ls11,ls12;



     public static final String apiKey ="3d30621d12254ce5864d0bad40094a88";
     private ArrayList<Article> arciles = new ArrayList<>();


 //    ArrayList<website> ws = new ArrayList<>();
 //    ArrayList<website> arrayList = new ArrayList<>();

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_timkiem);
         toolbar = findViewById(R.id.toolbarsearch);
         ed1 = findViewById(R.id.edtk);
         bt = findViewById(R.id.imgsearch);

         recytuhot=findViewById(R.id.recytukhoahot);
         recytimkiemganday=findViewById(R.id.recytimkiemganday);

         layoutManager1 = new LinearLayoutManager(getApplicationContext());
         recytuhot.setLayoutManager(layoutManager1);

         layoutManager2 = new LinearLayoutManager(getApplicationContext());
         recytimkiemganday.setLayoutManager(layoutManager2);

         ArrayList<String>arr=new ArrayList<>();
         arr.add("Covid");
         arr.add("Việt Nam covid");
         arr.add("Virus");
         arr.add("Prime");
         arr.add("Anthony Fauci");
         arr.add("Breakdown");
         arr.add("Wisconsin");
         arr.add("Washington");
         arr.add("Coronavirus");
         arr.add("Taehyung");
         arr.add("Nepal");
         arr.add("Animal");
         testAdapter testAdapter=new testAdapter(getApplicationContext(),arr);
         //recyclerView.setHasFixedSize(true);
         recytuhot.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
         recytuhot.setAdapter(testAdapter);
         testAdapter.notifyDataSetChanged();

         ArrayList<String>arr1=new ArrayList<>();
         ArrayList<KeySearch> keySearches= (ArrayList<KeySearch>) DatabaseNews.getInstance(this).daoNews().getListKeySearch();
         if (keySearches.size()>0) {
             for (KeySearch keySearch : keySearches) {
                 arr1.add(keySearch.getKey());
             }
             testAdapter testAdapter1 = new testAdapter(getApplicationContext(), arr1);
             recytimkiemganday.setAdapter(testAdapter1);
             recytimkiemganday.setHasFixedSize(true);
             recytimkiemganday.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
             testAdapter1.notifyDataSetChanged();
         }

         ActionToolbar();

         bt.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String tk=ed1.getText().toString();
                 LoadJson(tk);
                 KeySearch keySearch=new KeySearch(ed1.getText().toString(),new Date().getTime());

                 DatabaseNews.getInstance(getApplicationContext()).daoNews().deleteKeySearch(tk);
 //                ArrayList<KeySearch> dsSearch= (ArrayList<KeySearch>) DatabaseNews.getInstance(getApplicationContext()).daoNews().checkKeySearch(tk);
 //                if (dsSearch.size()>0){
 //                    for(KeySearch k:dsSearch) {
 //                        DatabaseNews.getInstance(getApplicationContext()).daoNews().deleteKeySearch(k);
 //                    }
 //                }

                 DatabaseNews.getInstance(getApplicationContext()).daoNews().insertKeySearch(keySearch);
             }
         });


     }
     private void ActionToolbar() {
         setSupportActionBar(toolbar);
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         toolbar.setNavigationOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(timkiem.this,MainActivity.class);
                 startActivity(intent);//artA

                 finish();

             }
         });
     }
     public void LoadJson(final String keyword) {
         ApiInterface apiInterface = ApiClient.getApiClientSearch().create(ApiInterface.class);
         Call<News> call;

         call=apiInterface.getNews(keyword,"vi","vnexpress.net",apiKey);
         // call = apiInterface.getNews("us","business", apiKey);
         call.enqueue(new Callback<News>() {
             @Override
             public void onResponse(Call<News> call, Response<News> response) {
                 if(response.isSuccessful()&&response.body().getArticles()!=null){
                     arciles= (ArrayList<Article>) response.body().getArticles();
                     Intent intent=new Intent(timkiem.this,hienthidanhsachtimkiem.class);// intent.putExtra("keysearch",keyword);
                     intent.putExtra("keysearch",keyword);
                     intent.putExtra("list",arciles);
                     startActivity(intent);

                 }
                 else{
                     Toast.makeText(timkiem.this,"No result",Toast.LENGTH_LONG).show();
                 }
             }

             @Override
             public void onFailure(Call<News> call, Throwable t) {

             }
         });

     }
 }

//het roi nha
 //        tv1=findViewById(R.id.tv1);
//        tv2=findViewById(R.id.tv2);
//        tv3=findViewById(R.id.tv3);

//        ls1=findViewById(R.id.ls1);
//        ls2=findViewById(R.id.ls2);
//        ls3=findViewById(R.id.ls3);
//        ls4=findViewById(R.id.ls4);
//        ls5=findViewById(R.id.ls5);
//        ls6=findViewById(R.id.ls6);
//        ls7=findViewById(R.id.ls7);
//        ls8=findViewById(R.id.ls8);
//        ls9=findViewById(R.id.ls9);
//        ls10=findViewById(R.id.ls10);
//        ls11=findViewById(R.id.ls11);
//        ls12=findViewById(R.id.ls12);

//        ArrayList<KeySearch> keySearches= (ArrayList<KeySearch>) DatabaseNews.getInstance(this).daoNews().getListKeySearch();
//        if(keySearches.size()>0) {
//            if(keySearches.get(0)!=null){
//                ls1.setText(keySearches.get(0).getKey());
//            }

//            ls2.setText(keySearches.get(1).getKey());
//            ls3.setText(keySearches.get(2).getKey());
//            ls4.setText(keySearches.get(3).getKey());
//            ls5.setText(keySearches.get(4).getKey());
//            ls6.setText(keySearches.get(5).getKey());
//            ls7.setText(keySearches.get(6).getKey());
//            ls8.setText(keySearches.get(7).getKey());
//            ls9.setText(keySearches.get(8).getKey());
//            ls10.setText(keySearches.get(9).getKey());
//            ls11.setText(keySearches.get(10).getKey());
//            ls12.setText(keySearches.get(11).getKey());
 //  }

//        ls1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoadJson(ls1.getText().toString()+"");
//            }
//        });
//        ls2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoadJson(ls2.getText().toString()+"");
//            }
//        });
//        ls3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoadJson(ls3.getText().toString()+"");
//            }
//        });
//        ls4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoadJson(ls4.getText().toString()+"");
//            }
//        });
//        ls5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoadJson(ls5.getText().toString()+"");
//            }
//        });
//        ls6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoadJson(ls6.getText().toString()+"");
//            }
//        });
//        ls7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoadJson(ls7.getText().toString()+"");
//            }
//        });
//        ls8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoadJson(ls8.getText().toString()+"");
//            }
//        });
//        ls9.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoadJson(ls9.getText().toString()+"");
//            }
//        });
//        ls10.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoadJson(ls10.getText().toString()+"");
//            }
//        });
//        ls11.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoadJson(ls11.getText().toString()+"");
//            }
//        });
//        ls12.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoadJson(ls12.getText().toString()+"");
//            }
//        });

//        tv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoadJson(tv1.getText().toString()+"");
//            }
//        });
//
//        tv2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoadJson(tv2.getText().toString()+"");
//            }
//        });
//
//        tv3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoadJson(tv3.getText().toString()+"");
//            }
//        });
 //======================================================================================================
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                new readData().execute("https://vnexpress.net/rss/tin-moi-nhat.rss");
//            }
//        });
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //closeKeyboard();
//                String searchTerm = tv1.getText().toString();
//
//                // Log.d(TAG, "onClick: searchTerm:" + searchTerm);
//                // if search i empty, just refresh the list
//                if (searchTerm.equals("") || searchTerm.equals(" ")) {
//                    return;
////                if (isNetworkAvailable()) {
////                    trimmedRSSObjectList.clear();
////                    setUpRecyclerView();
//                    //            }
//                } else {
//                    //  Log.d(TAG, "onClick: Refresh button: after check: " + searchTerm);
//                    filter(searchTerm);
//                }
//            }
//
//
//        });

//        ActionToolbar();
//        tv1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(timkiem.this,hienthidanhsachtimkiem.class);
//                intent.putExtra("search",tv1.getText().toString());
//                startActivity(intent);
//            }
//        });
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                new readData().execute("https://vnexpress.net/rss/tin-moi-nhat.rss");
//            }
//        });
//
//    }
//    <?xml version="1.0" encoding="utf-8"?>
//<selector xmlns:android="http://schemas.android.com/apk/res/android">
//    <item>
//        <shape android:shape="rectangle">
//            <solid android:color="#ffffff"/>
//            <corners android:radius="6dp" />
//            <stroke
//    android:width="1dp"
//    android:color="#27000000"
//            />
//        </shape>
//    </item>
//</selector>
      //  @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_search,menu);
//        MenuItem item=menu.findItem(R.id.idsearch);
//            SearchView searchView=(SearchView)item.getActionView();
//            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                @Override
//                public boolean onQueryTextSubmit(String query) {
//                    return false;
//                }
//
//                @Override
//                public boolean onQueryTextChange(String newText) {
//                    return false;
//                }
//            });

            ///
//        MenuItem.OnActionExpandListener onActionExpandListener=new MenuItem.OnActionExpandListener() {
//            @Override
//            public boolean onMenuItemActionExpand(MenuItem item) {
//                Toast.makeText(timkiem.this,"ha1",Toast.LENGTH_SHORT).show();
//                return true;
//            }
//
//            @Override
//            public boolean onMenuItemActionCollapse(MenuItem item) {
//                Toast.makeText(timkiem.this,"ha2",Toast.LENGTH_SHORT).show();
//
//                return true;
//            }
//        };
//        menu.findItem(R.id.idsearch).setOnActionExpandListener(onActionExpandListener);
//            SearchView searchView=(SearchView)menu.findItem(R.id.idsearch).getActionView();
//            searchView.setQueryHint("nhap tu tim kiem...");
//        return true;
//    }

  //  @Override
 //   public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch(item.getItemId()) {
//            case R.id.menugiohang:
//                Intent intent=new Intent(getApplicationContext(),GioHang.class);
//                startActivity(intent);
//        }
//        return super.onOptionsItemSelected(item);
//    }

//    class readData extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... strings) {
//            return docNoiDung_Tu_URL(strings[0]);
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            XMLDOMParser parser=new XMLDOMParser();
//            Document document=parser.getDocument(s);
//            NodeList nodeList=document.getElementsByTagName("item");
//            NodeList nodeListDescription=document.getElementsByTagName("description");
//            String title="",link="",hinhanh="",description="",pdate="";
//            for(int i=0;i<nodeList.getLength();i++){
//                String cData=nodeListDescription.item(i+1).getTextContent();
//                Pattern p=Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
//                Matcher matcher=p.matcher(cData);
//                if(matcher.find()){
//                    hinhanh=matcher.group(1);
//
//                }
//                Element element=(Element) nodeList.item(i);
//                title=parser.getValue(element,"title");
//                link=parser.getValue(element,"link");
//                description=parser.getValue(element,"description");
//                pdate=parser.getValue(element,"pubDate");
//
//                arrayList.add(new website(title,link,hinhanh,pdate,description));
//                    Log.d("link",link);
//                    Toast.makeText(MainActivity.this,title,Toast.LENGTH_LONG).show();

//}
//            customadapter=new customadapter(MainActivity.this, android.R.layout.simple_list_item_1,arrayList);
//            lv.setAdapter(customadapter);

            //        Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
//        }
//    }
//    public void filter(String searchTerm) {
//        String noMatches = "no matches";
//        Thread thread =new Thread(() -> {
//            ArrayList<website> temp = new ArrayList<>();
//            temp.clear();
//            for (website object : arrayList) {
//                if (UtilityClass.searchMatch(object, searchTerm)) {
//                    temp.add(object);
//                }
//            }
//
//            // if no entries matches the searchTerm, respond with snackBar message.
//            if (temp.size() == 0 ) {
//                temp.clear();
////                Snackbar.make(getActivity().findViewById(android.R.id.content
////                ), noMatches,Snackbar.LENGTH_LONG).show();
//            }
//            runOnUiThread(() -> {
//                // if no entries matches the searchTerm, do nothing
//                if (temp.size() == 0){
//                    return;
//                }
//                // if some entries matches the searchTerm, update the list
//                ws.clear();
//                ws.addAll(temp);
//                Intent intent=new Intent(timkiem.this,hienthidanhsachtimkiem.class);
//                intent.putExtra("kqtk","hahaha");
//                startActivity(intent);
//
//            });
//        });
//
//        thread.start();
//    }
//    public  String docNoiDung_Tu_URL(String theUrl){
//        StringBuilder content = new StringBuilder();
//        try    {
//            // create a url object
//            URL url = new URL(theUrl);
//
//            // create a urlconnection object
//            URLConnection urlConnection = url.openConnection();
//
//            // wrap the urlconnection in a bufferedreader
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//
//            String line;
//
//            // read from the urlconnection via the bufferedreader
//            while ((line = bufferedReader.readLine()) != null){
//                content.append(line + "\n");
//            }
//            bufferedReader.close();
//        }
//        catch(Exception e)    {
//            e.printStackTrace();
//        }
//        return content.toString();

