package com.example.asus.myapplication.Fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.asus.myapplication.Activity.WriteActivity;
import com.example.asus.myapplication.Model.Note;
import com.example.asus.myapplication.Model.Notelab;
import com.example.asus.myapplication.Model.VerticalImageSpan;
import com.example.asus.myapplication.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by asus on 2016/10/30.
 */
public class WriteFragment extends Fragment {
    private  EditText editText;
    private static final String IMAGE_UNSPECIFIED = "image/*";
    private final int IMAGE_CODE = 0;
    private Uri originalUri;
    private  Bitmap bm;
    private ImageView mImageView;
    private  Matrix mMatrix;
    private  int width;
    private  Toolbar toolbar;
    private  Note mNote;
    private  int position;
    private  int sql_code;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        position = (int) getActivity().getIntent().getSerializableExtra(WriteActivity.EXTRA_NOTE_POSITION);
        sql_code = (int) getActivity().getIntent().getSerializableExtra(WriteActivity.SQL);

        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_write,container,false);
        toolbar = (Toolbar) v.findViewById(R.id.include);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        toolbar.setNavigationIcon(R.drawable.insert);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        activity.setSupportActionBar(toolbar);

        activity.getSupportActionBar().setTitle("便签");

        editText = (EditText) v.findViewById(R.id.edittext);
        width= getActivity().getResources().getDisplayMetrics().widthPixels;
//        Button button = (Button) v.findViewById(R.id.writebutton);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_PICK, null);
//                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
//                startActivityForResult(intent, IMAGE_CODE);
//            }
//        });

        return  v;

    }

    @Override
    public void onPause() {
            if(sql_code==0)
            {
                Notelab notelab = Notelab.getNotelab(getActivity());
                Note note = new Note(position);
                note.setNote(editText.getText().toString());

                SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy-MM-dd HH:mm");
                Date   curDate   =   new   Date(System.currentTimeMillis());//获取当前时间
                note.setmDate(curDate);
                notelab.addNote(note);

            }
        super.onPause();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.writemenu,menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.insert:
                startimagespan();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void addImageSpan() {

        SpannableString spanString = new SpannableString("image");
//        Drawable d = getResources().getDrawable(R.drawable.ic_launcher);
     //   BitmapDrawable d= new BitmapDrawable(bm);

     //   d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(getActivity(),bm);
        spanString.setSpan(span, 0, spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
       //editText.append(spanString);

        int index = editText.getSelectionStart();
                // 获取光标所在位置
        Editable edit_text = editText.getEditableText();
        if (index < 0 || index >= edit_text.length()) {
            edit_text.append(spanString);
        } else {
            edit_text.insert(index, spanString);
        }
        edit_text.insert(index + spanString.length(), "\n");
        System.out.println("插入的图片：" + spanString.toString());
    }

    private  void startimagespan()
    {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
        startActivityForResult(intent, IMAGE_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub


         bm = null;
        // 外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口

        ContentResolver resolver = getActivity().getContentResolver();

        if (requestCode == IMAGE_CODE) {
           try {
                 originalUri = data.getData(); // 获得图片的uri
               bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);

               float scaleWidth = ((float) width) / bm.getWidth();


               Matrix matrix = new Matrix();
               matrix.postScale(scaleWidth/2, scaleWidth/2);
               bm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(),matrix,true);
               addImageSpan();
              // mImageView.setImageBitmap(bm);
//                // imageView.setImageBitmap(ThumbnailUtils.extractThumbnail(bm, 100, 100));  //使用系统的一个工具类，参数列表为 Bitmap Width,Height  这里使用压缩后显示，否则在华为手机上ImageView 没有显示
//                // 显得到bitmap图片
//              //  imageView.setImageBitmap(bm);
//
//                String[] proj = { MediaStore.Images.Media.DATA };
//
//                // 好像是android多媒体数据库的封装接口，具体的看Android文档
//                Cursor cursor =getActivity().managedQuery(originalUri, proj, null, null, null);
//
//                // 按我个人理解 这个是获得用户选择的图片的索引值
//                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                // 将光标移至开头 ，这个很重要，不小心很容易引起越界
//                cursor.moveToFirst();
//                // 最后根据索引值获取图片路径
//                String path = cursor.getString(column_index);
//              //  tv.setText(path);
            } catch (IOException e) {
                Log.e("TAG-->Error", e.toString());

            }

           finally {
               return;
           }
        }




        super.onActivityResult(requestCode, resultCode, data);

    }
}
