package sinia.com.baihangeducation.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UploadFileListener;
import sinia.com.baihangeducation.R;
import sinia.com.baihangeducation.actionsheetdialog.ActionSheetDialog;
import sinia.com.baihangeducation.actionsheetdialog.ActionSheetDialogUtils;
import sinia.com.baihangeducation.base.BaseActivity;
import sinia.com.baihangeducation.bean.JsonBean;
import sinia.com.baihangeducation.bean.PersonalBean;
import sinia.com.baihangeducation.utils.ActivityManager;
import sinia.com.baihangeducation.utils.BitmapUtilsHelp;
import sinia.com.baihangeducation.utils.CacheUtils;
import sinia.com.baihangeducation.utils.Constants;
import sinia.com.baihangeducation.utils.MyApplication;
import sinia.com.baihangeducation.utils.StringUtil;
import sinia.com.baihangeducation.utils.ValidationUtils;

/**
 * Created by 忧郁的眼神 on 2016/7/29.
 */
public class EditPersonInfoActivity extends BaseActivity {
    @Bind(R.id.img_head)
    ImageView img_head;
    @Bind(R.id.tv_changeimg)
    TextView tv_changeimg;
    @Order(3)
    @NotEmpty(message = "请选择性别")
    @Bind(R.id.tv_sex)
    TextView tv_sex;
    @Bind(R.id.tv_ok)
    TextView tv_ok;
    @Order(1)
    @NotEmpty(message = "请输入您的姓名")
    @Bind(R.id.et_name)
    EditText et_name;
    @Order(2)
    @NotEmpty(message = "请输入您的年龄")
    @Bind(R.id.et_age)
    EditText et_age;
    @Order(4)
    @NotEmpty(message = "请输入现居地地址")
    @Bind(R.id.et_city)
    EditText et_city;
    @Order(5)
    @NotEmpty(message = "请输入地区")
    @Bind(R.id.et_area)
    EditText et_area;
    @Order(6)
    @NotEmpty(message = "请输入您的手机号")
    @Bind(R.id.et_tel)
    EditText et_tel;
    @Order(7)
    @NotEmpty(message = "请输入紧急联系人")
    @Bind(R.id.et_contact)
    EditText et_contact;
    @Order(8)
    @Pattern(regex = "^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$", message = "请输入正确的紧急联系方式")
    @Bind(R.id.et_contact_tel)
    EditText et_contact_tel;
    @Bind(R.id.rl_sex)
    RelativeLayout rl_sex;
    private PersonalBean bean;
    private Validator validator;
    private AsyncHttpClient client = new AsyncHttpClient();
    private String imgPath, dateTime;
    private String imgUrl = "";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person, "个人信息");
        getDoingView().setVisibility(View.GONE);
        Bmob.initialize(this, Constants.BMOB_KEY);
        initData();
    }

    private void initData() {
        progressDialog = new ProgressDialog(this);
        bean = (PersonalBean) getIntent().getSerializableExtra("bean");
        imgUrl = bean.getImageUrl();
        BitmapUtilsHelp.getImage(this).display(img_head, bean.getImageUrl());
        et_name.setText(bean.getUserName());
        et_tel.setText(bean.getPersonPhone());
        et_age.setText(bean.getAge());
        et_area.setText(bean.getArea());
        et_city.setText(bean.getNowAddress());
        et_contact.setText(bean.getEmContact());
        et_contact_tel.setText(bean.getEmContactPhone());
        if ("1".equals(bean.getSex())) {
            tv_sex.setText("男");
        } else {
            tv_sex.setText("女");
        }

        validator = new Validator(this);
        validator.setValidationListener(new ValidationUtils.ValidationListener() {
            @Override
            public void onValidationSucceeded() {
                super.onValidationSucceeded();
                saveResume();
            }

        });
    }

    private void saveResume() {
        showLoad("保存中...");
        RequestParams params = new RequestParams();
        params.put("customerId", MyApplication.getInstance().getStringValue("userId"));
        params.put("userName", et_name.getEditableText().toString().trim());
        params.put("age", et_age.getEditableText().toString().trim());
        params.put("personPhone", et_tel.getEditableText().toString().trim());
        params.put("nowAddress", et_city.getEditableText().toString().trim());
        params.put("area", et_area.getText().toString().trim());
        params.put("emContact", et_contact.getText().toString().trim());
        params.put("emContactPhone", et_contact_tel.getText().toString().trim());
        if (StringUtil.isEmpty(imgUrl)) {
            params.put("imageUrl", "-1");
        } else {
            params.put("imageUrl", imgUrl);
        }
        if ("男".equals(tv_sex.getText().toString())) {
            params.put("sex", "1");
        } else {
            params.put("sex", "2");
        }
        Log.i("tag", Constants.BASE_URL + "changeUserInfo&" + params);
        client.post(Constants.BASE_URL + "changeUserInfo", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, String s) {
                super.onSuccess(i, s);
                dismiss();
                Gson gson = new Gson();
                if (s.contains("isSuccessful")
                        && s.contains("state")) {
                    JsonBean bean = gson.fromJson(s, JsonBean.class);
                    int state = bean.getState();
                    int isSuccessful = bean.getIsSuccessful();
                    if (0 == state && 0 == isSuccessful) {
                        showToast("信息保存成功");
                        ActivityManager.getInstance().finishCurrentActivity();
                    } else {
                        showToast("信息保存失败");
                    }
                }
            }
        });
    }

    @OnClick(R.id.rl_sex)
    void rl_sex() {
        ActionSheetDialogUtils.createSexDialog(this, tv_sex);
    }

    @OnClick(R.id.tv_changeimg)
    void tv_changeimg() {
        selectHeadImage();
    }

    private void selectHeadImage() {
        new ActionSheetDialog(this)
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("拍照选择", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                Date date1 = new Date(System
                                        .currentTimeMillis());
                                dateTime = date1.getTime() + "";
                                getAvataFromCamera();
                            }
                        })
                .addSheetItem("从手机相册选择", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intent, 2);
                            }
                        }).show();
    }

    protected void getAvataFromCamera() {
        File f = new File(CacheUtils.getCacheDirectory(this, true,
                "icon") + dateTime + "avatar.jpg");
        if (f.exists()) {
            f.delete();
        }
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri uri = Uri.fromFile(f);
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        camera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(camera, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 1:
                    String files = CacheUtils.getCacheDirectory(this,
                            true, "icon") + dateTime + "avatar.jpg";
                    File file = new File(files);
                    if (file.exists() && file.length() > 0) {
                        Uri uri = Uri.fromFile(file);
                        startPhotoZoom(uri);
                    }
                    break;
                case 2:
                    if (data == null) {
                        return;
                    }
                    startPhotoZoom(data.getData());
                    break;
                case 3:
                    if (data != null) {
                        Bundle extras = data.getExtras();
                        if (extras != null) {
                            Bitmap bitmap = extras.getParcelable("data");
                            imgPath = saveToSdCard(bitmap);
                            Log.i("lamp", "iconUrl---" + imgPath);
                            img_head.setImageBitmap(bitmap);
                            updateIcon(imgPath);
                        }
                    }
                    break;
            }
        }
    }

    private void updateIcon(String avataPath) {
        if (avataPath != null) {
            showLoad("正在上传头像");
            final BmobFile file = new BmobFile(new File(avataPath));
            file.upload(this, new UploadFileListener() {

                @Override
                public void onSuccess() {
                    dismiss();
                    imgUrl = file.getFileUrl(EditPersonInfoActivity.this);
                    // showToast("图片上传成功");
                }

                @Override
                public void onFailure(int arg0, String arg1) {
                    Log.i("tag", "图片上传失败" + arg1);
                    dismiss();
                }
            });
        }
    }

    public String saveToSdCard(Bitmap bitmap) {
        String files = CacheUtils
                .getCacheDirectory(this, true, "icon")
                + dateTime
                + "_11.jpg";
        File file = new File(files);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)) {
                fos.flush();
                fos.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }

    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 280);
        intent.putExtra("outputY", 280);
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);// 黑边
        intent.putExtra("scaleUpIfNeeded", true);// 黑边
        intent.putExtra("return-data", true);// 选择返回数据
        startActivityForResult(intent, 3);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @OnClick(R.id.tv_ok)
    void tv_ok() {
        validator.validate();
    }
}
