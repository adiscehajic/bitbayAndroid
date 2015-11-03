package ba.bitcamp.android.bitbay;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Kerim on 3.11.2015.
 */
public class LoadImage extends AsyncTask<String, String, Bitmap> {

        private ImageView img;
        public LoadImage(ImageView imgView){
            img = imgView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Bitmap doInBackground(String... args) {
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {
            img.setImageBitmap(image);

        }
}
