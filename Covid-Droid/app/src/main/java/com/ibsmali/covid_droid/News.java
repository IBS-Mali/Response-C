package com.ibsmali.covid_droid;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class News extends Base {

    private final static String TAG = Constants.getLogTag("News");

    private ListView mListView;
    //private ArrayAdapter<NewsData> listAdapter ;
    private SimpleAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);

        ImageView myText = (ImageView) findViewById(R.id.icon );
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500); //You can manage the blinking time with this parameter
        anim.setStartOffset(0);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        myText.startAnimation(anim);
//        setupUI();
//        new GetRssFeed().execute("https://facebook-rss.herokuapp.com/rss/130149083720242");

        }

//    public void setupUI(){
//
//        String[] from = {"title", "description", "date"};
//        int[] to = {R.id.title, R.id.description, R.id.dateP};
//
//        mListView = (ListView) findViewById(R.id.lv_resource);
//        List<java.util.Map<String, ?>> data = getData();
//        adapter = new SimpleAdapter(getBaseContext(), data, R.layout.basic_list_item, from, to);
//
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//
//                HashMap<String, Object> hm = (HashMap<String, Object>) adapter.getItem((int) id);
//                String selectId = (String) hm.get("id");
//                Log.d(TAG, selectId + " has selected");
//                // Intent a = new Intent(CultureHome.this, About.class);
//                // startActivity(a);
//            }
//        });
//        mListView.setAdapter(adapter);
//    }
//
//    List<java.util.Map<String, ?>> getData() {
//        List<java.util.Map<String, ?>> list = new ArrayList<java.util.Map<String, ?>>();
//        List<NewsData> reports;
//
//        reports = Select.from(NewsData.class).orderBy("-PUB_DATE").list();
//        for (NewsData rpt: reports){
//            DateFormat df = new SimpleDateFormat("dd/MMMM/yyyy HH:mm:ss");
//            String date = df.format(rpt.getpubDate());
//            Map map = new HashMap();
//            map.put("id", String.valueOf(rpt.getId()));
//            map.put("title", Html.fromHtml(rpt.getTitle()));
//            map.put("description", Html.fromHtml(rpt.getDescription()));
//            map.put("date", date);
//            list.add(map);
//        }
//        return list;
//    }

//    private class GetRssFeed extends AsyncTask<String, Void, Void> {
//
//        private ProgressDialog Dialog = new ProgressDialog(News.this);
//
//        public boolean isOnline() {
//            ConnectivityManager cm =
//                    (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo netInfo = cm.getActiveNetworkInfo();
//            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
//                return true;
//            }
//            return false;
//        }

//        @Override
//        protected Void doInBackground(String... params) {
//            Log.d(TAG, "doInBackground" + " " + params[0]);
//            try {
//                URL url = new URL(params[0]);
//                RssFeed feed = RssReader.read(url);
//
//                ArrayList<RssItem> rssItems = feed.getRssItems();
//                for (RssItem item : rssItems) {
//                    Log.i(TAG, item.getLink());
//                    List news = NewsData.find(NewsData.class, "STATUS = ?", item.getLink());
//                    if(!news.isEmpty()) {
//                        Log.d(TAG, news.size()+ " existe déjà dans la base");
//                    }else {
//                        Log.d(TAG, "Created new ");
//                        NewsData newsData = new NewsData(item.getPubDate(),
//                                item.getTitle(),
//                                item.getDescription(),
//                                item.getContent(),
//                                item.getLink());
//                        newsData.save();
//                    }
//                }
//            } catch (Exception e) {
//                Log.v(TAG, "RssFeed " + String.valueOf(e));
//            }
//            return null;
//        }

//        @Override
//        protected void onPreExecute() {
//            // Loading
//            if (isOnline()){
//                Dialog.setMessage("Chargement en cours ...");
//                Dialog.setCancelable(false);
//                Dialog.show();
//            }
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            if (isOnline()) {
//                // after completed finished the progressbar
//                Dialog.dismiss();
//                adapter.notifyDataSetChanged();
//                mListView.setAdapter(adapter);
////                setupUI();
//            }else{
//                Toast.makeText(News.this, "Vous êtes en mode hors connexion",
//                        Toast.LENGTH_LONG).show();
//            }
//        }
//    }

}
