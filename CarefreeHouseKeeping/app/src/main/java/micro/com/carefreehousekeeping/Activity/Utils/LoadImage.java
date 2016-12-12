package micro.com.carefreehousekeeping.Activity.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Administrator on 2016/12/10.
 */

public class LoadImage extends AsyncTask<String,Void,Bitmap>{

    private ImageView imageView;
    private String url;
    private Context context;



    private LoadImage(){}

    public static void Load(ImageView imageView, String url, Context context) {
        LoadImage loadImage=new LoadImage();
        loadImage.imageView = imageView;
        loadImage.url = url;
        loadImage.context = context;
        loadImage.imageView.setTag(url);
        loadImage.execute(url);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(bitmap!=null)
        {
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bitmap;
        InputStream is=null;
        try {
            is=new URL(url).openConnection().getInputStream();
            InputStream iss=SaveInFile.get(context,url);
            if(AppFinal.memery.get(url)!=null){
                bitmap=AppFinal.memery.get(url);
            }else if(iss!=null){
                bitmap= BitmapFactory.decodeStream(iss);
            }else {
                SaveInFile.save(context,is,url);
                iss=SaveInFile.get(context,url);
                bitmap=BitmapFactory.decodeStream(iss);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
