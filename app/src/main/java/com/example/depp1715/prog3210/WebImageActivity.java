package com.example.depp1715.prog3210;


import java.io.InputStream;
import java.net.URL;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.View;

public class WebImageActivity extends Activity implements View.OnClickListener {
    private String IMG_URL = "https://lh3.googleusercontent.com/7bA4pn3zyapbAZ96QDskeqmNb50RzBtr0VL1xx9MXxTdCWxQ0wExZg3-57idSqbQM2YU1cgQA7-Fw0JdtOJgvuTL7OO1lBYyrAB6zWG7yf8K8sMvHWXQVCmfu_rirGlU1_2W9ZD-N0u-cZR8U-vN75tqX0RK0AJGMi4WqKLa-mxQpCTdWyE0yfX9CAy-UzczYbqyTB9yXr2UOxgx2c43Cf19E3wEbdhiMlehuzk0CPVO2GRPBWoNIq2j3e2K1uGPO3-WzqUII2ToQxG_MvEwOPeRIptfhpG19rE07iQevvTB5NwBRxi3X9CkoWgdOK9fvUJvWvqgn4aXKMeKtyJJoGCfEP8OC90DEMMiemOvST9Vi9vG1pERpXscS6XbSbmFJ383q-LgdRqR75wFlEZnISVjqgzoPanLrsruQ352VhKyzC4F5wBm_E9eLH-e8Uy5tBiFiQcMJXnrkeb-vzccnC4YaSVKp642WhPK9UvLPhOTHEFt2MmfaoAu-6PuWubPfhrOHtrVhivpO2z6pHZPXKebAQRUh2nwB4txSlc82AqkyA17Ml7BJTQaZt7F36XZPDiu3jhco4T4tou-3Cz0VOVz0XRqKVMmYOaB6IVODHXPjX5nUfu37GpNW4gOQUkmtg1Llg3S_YiXYb4sXLLRzEEnruJMxrj4OA=w2273-h1515-no";
    private Button btnShowPic;
    private ImageView imgCutePic;
    private Bitmap bitmap;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_image);
        imgCutePic = findViewById(R.id.imgCutePic);

        btnShowPic = (Button)findViewById(R.id.btnShowPic);

        //Show the image by default
        //new LoadImage().execute(IMG_URL);

        btnShowPic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        new LoadImage().execute(IMG_URL);
    }

    private class LoadImage extends AsyncTask<String, String, Bitmap>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(WebImageActivity.this);
            pDialog.setMessage("Loading Image ....");
            pDialog.show();
        }
        protected Bitmap doInBackground(String... args)
        {
            try
            {
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return bitmap;
        }
        protected void onPostExecute(Bitmap image)
        {
            if(image != null)
            {
                imgCutePic.setImageBitmap(image);
                pDialog.dismiss();
            }
            else
            {
                pDialog.dismiss();
                Toast.makeText(WebImageActivity.this, "Image does not exist or network error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}