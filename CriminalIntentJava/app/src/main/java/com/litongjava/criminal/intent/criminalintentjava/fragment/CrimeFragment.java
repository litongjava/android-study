package com.litongjava.criminal.intent.criminalintentjava.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.litongjava.android.view.inject.annotation.FindViewById;
import com.litongjava.android.view.inject.annotation.OnClick;
import com.litongjava.android.view.inject.utils.ViewInjectUtils;
import com.litongjava.criminal.intent.criminalintentjava.R;
import com.litongjava.criminal.intent.criminalintentjava.model.Crime;
import com.litongjava.criminal.intent.criminalintentjava.model.CrimeLab;
import com.litongjava.criminal.intent.criminalintentjava.utils.PictureUtils;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

//此处选择支持库版的fragment
@Slf4j
public class CrimeFragment extends Fragment {

  private static final String ARG_CRIME_ID = "crime_id";
  private static final String DIALOG_DATE = "DialogDate";
  private static final String DIALOG_IMAGE = "DialogImage";

  private File mPhotoFile;
  private static final int REQUEST_DATE = 0;
  private static final int REQUEST_PHOTO = 2;
  private static final int REQUEST_CONTACT = 1;


  private Crime mCrime;
  @FindViewById(R.id.crime_title)
  private EditText mTitleField;
  @FindViewById(R.id.crime_date)
  private Button mDateButton;
  @FindViewById(R.id.crime_solved)
  private CheckBox mSolvedCheckBox;

  @FindViewById(R.id.crime_camera)
  private ImageButton mPhotoButton;
  @FindViewById(R.id.crime_photo)
  private ImageView mPhotoView;

  @FindViewById(R.id.crime_report)
  private Button mReportButton;
  @FindViewById(R.id.crime_suspect)
  private Button mSuspectButton;


  public static CrimeFragment newInstance(UUID crimeId) {
    Bundle args = new Bundle();
    args.putSerializable(ARG_CRIME_ID, crimeId);
    CrimeFragment fragment = new CrimeFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    log.info("create");
    mCrime = new Crime();

    UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
    mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    mPhotoFile = CrimeLab.get(getActivity()).getPhotoFile(mCrime);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    //第一个参数是布局资源文件
    //第二个参数是视图的父视图，我们通常需要父视图来正确配置组件。
    //第三个参数告诉布局生成器是否将生成的视图添加给父视图。这里，传入了false参数，因为我们将以代码的方式添加视图。
    View view = inflater.inflate(R.layout.fragment_crime, container, false);
    ViewInjectUtils.inject(view, this);
    ViewInjectUtils.injectOnClick(view, this);

    mTitleField.setText(mCrime.getTitle());
    mSolvedCheckBox.setChecked(mCrime.isSolved());

    updateDate();
    //mDateButton.setEnabled(false);

    final Intent pickContact = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
    pickContact.addCategory(Intent.CATEGORY_HOME);
    mSuspectButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivityForResult(pickContact, REQUEST_CONTACT);
      }
    });
    if (mCrime.getSuspect() != null) {
      mSuspectButton.setText(mCrime.getSuspect());
    }

    PackageManager packageManager = getActivity().getPackageManager();
    if (packageManager.resolveActivity(pickContact, PackageManager.MATCH_DEFAULT_ONLY) == null) {
      mSuspectButton.setEnabled(false);
    }
    addListener();


    final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    boolean canTakePhoto = mPhotoFile != null && captureImage.resolveActivity(packageManager) != null;

    mPhotoButton.setEnabled(canTakePhoto);
    if (canTakePhoto) {
      //Uri uri = Uri.fromFile(mPhotoFile);
      Uri uri=null;
      if (Build.VERSION.SDK_INT >= 24) {
        Context applicationContext = getActivity().getApplicationContext();
        String packageName = applicationContext.getPackageName();
        //String authority="com.litongjava.criminal.intent.criminalintentjava.fileprovider";
        //uri = FileProvider.getUriForFile(applicationContext, authority, mPhotoFile);
        uri = FileProvider.getUriForFile(getActivity(), packageName + ".provider", mPhotoFile);
        log.info("uri:{}",uri);
        //7.0以后，系统要求授予临时uri读取权限，安装完毕以后，系统会自动收回权限，该过程没有用户交互
        captureImage.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
      } else {
        uri = Uri.fromFile(mPhotoFile);
      }
      captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
    }
    mPhotoButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivityForResult(captureImage, REQUEST_PHOTO);
      }
    });
    updatePhotoView();
    return view;
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (resultCode != Activity.RESULT_OK) {
      return;
    }
    if (requestCode == REQUEST_DATE) {
      Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
      mCrime.setDate(date);
      updateDate();
    }

    if (requestCode == REQUEST_DATE) {
      updateDate();
    } else if (requestCode == REQUEST_CONTACT && data != null) {
      Uri contactUri = data.getData();
      // Specify which fields you want your query to return
      // values for.
      String[] queryFields = new String[]{
        ContactsContract.Contacts.DISPLAY_NAME
      };
      // Perform your query - the contactUri is like a "where"
      // clause here
      Cursor c = getActivity().getContentResolver().query(contactUri, queryFields, null, null, null);
      try {
        // Double-check that you actually got results
        if (c.getCount() == 0) {
          return;
        }
        // Pull out the first column of the first row of data -
        // that is your suspect's name.
        c.moveToFirst();
        String suspect = c.getString(0);
        mCrime.setSuspect(suspect);
        mSuspectButton.setText(suspect);
      } finally {
        c.close();
      }
    } else if (requestCode == REQUEST_PHOTO) {
      updatePhotoView();
    }

  }

  private void updateDate() {
    mDateButton.setText(mCrime.getDate().toString());
  }

  private void addListener() {
    mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // Set the crime's solved property
        mCrime.setSolved(isChecked);
        //设置返回数据
        returnResult();
      }
    });

    mTitleField.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        //（代表用户输入）的toString()方法
        log.info("new input:{}", charSequence.toString());
        mCrime.setTitle(charSequence.toString());
        returnResult();
      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });

  }


  public void returnResult() {
    Intent data = new Intent();
    data.putExtra(ARG_CRIME_ID, mCrime.getId());
    getActivity().setResult(Activity.RESULT_OK, data);
  }

  private String getCrimeReport() {
    String solvedString = null;
    if (mCrime.isSolved()) {
      solvedString = getString(R.string.crime_report_solved);
    } else {
      solvedString = getString(R.string.crime_report_unsolved);
    }
    String dateFormat = "EEE, MMM dd";
    String dateString = DateFormat.format(dateFormat, mCrime.getDate()).toString();
    String suspect = mCrime.getSuspect();
    if (suspect == null) {
      suspect = getString(R.string.crime_report_no_suspect);
    } else {
      suspect = getString(R.string.crime_report_suspect, suspect);
    }
    String report = getString(R.string.crime_report,
      mCrime.getTitle(), dateString, solvedString, suspect);
    return report;
  }

  private void updatePhotoView() {
    if (mPhotoFile == null || !mPhotoFile.exists()) {
      mPhotoView.setImageDrawable(null);
    } else {
      Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(), getActivity());
      mPhotoView.setImageBitmap(bitmap);
    }
  }

  @OnClick(R.id.crime_report)
  public void mReportButtonOnClick() {
    Intent i = new Intent(Intent.ACTION_SEND);
    i.setType("text/plain");
    i.putExtra(Intent.EXTRA_TEXT, getCrimeReport());
    i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.crime_report_subject));
    i = Intent.createChooser(i, getString(R.string.send_report));
    startActivity(i);
  }

  @OnClick(R.id.crime_date)
  public void mDateButtonOnClick() {
    FragmentManager manager = getFragmentManager();
    DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
    dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
    dialog.show(manager, DIALOG_DATE);
  }

  @OnClick(R.id.crime_photo)
  public void mPhotoViewOnClick(){
    FragmentManager manager = getFragmentManager();
    ImageDialogFragment dialog = ImageDialogFragment.newInstance(mPhotoFile);
    dialog.show(manager,DIALOG_IMAGE);
  }
}
