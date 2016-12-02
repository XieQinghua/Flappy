package com.thvc.flappy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.thvc.flappy.R;

import java.util.Random;

/**
 * 项目名称：Flappy
 * 类描述：主程序
 * 创建人：谢庆华.
 * 创建时间：2016/2/24 17:31
 * 修改人：Administrator
 * 修改时间：2016/2/24 17:31
 * 修改备注：
 */
public class MainActivity extends Activity implements View.OnClickListener {
    private Button bt_send;
    private TextView content;
    private String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        CrashReport.testJavaCrash();// Bugly测试，解除注释将产生一个测试Crash
        bt_send = (Button) findViewById(R.id.bt_send);
        content = (TextView) findViewById(R.id.tv_content);
        bt_send.setOnClickListener(this);

    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String str = data.getString("value");
            content.setText(str);
        }
    };

    /**
     * 网络操作相关的子线程
     */
    Runnable networkTask = new Runnable() {
        @Override
        public void run() {
            //发送验证码
            try {
                TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23530960", "3ea62a1b71b1b5a81f3c1085cfbdbbf2");
                AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
                req.setExtend("123456");
                req.setSmsType("normal");
                req.setSmsFreeSignName("你好");

                Log.e("Number", number);
                //{"number":"4561","name":"Flappy"}
//                req.setSmsParamString("{\"number\":\"" + number + "\",\"name\":\"Flappy\"}");
                req.setSmsParamString("{\"number\":\"4561\",\"name\":\"Flappy\"}");
                req.setRecNum("15200917596");
                req.setSmsTemplateCode("SMS_25835006");
                AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
                Log.e("Test", rsp.getBody());

                Message msg = new Message();
                Bundle data = new Bundle();
                data.putString("value", rsp.getBody());
                msg.setData(data);
                handler.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_send:
                number = (new Random().nextInt(9000) + 1000) + "";
                // 开启一个子线程，进行网络操作，等待有返回结果，使用handler通知UI
                new Thread(networkTask).start();
                break;
        }
    }

//    public void showDialog(final View view) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        switch (view.getId()) {
//            case R.id.btn_choose:
//                //日期选择对话框
//                DatePickerDialog dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//
//                        try {
//                            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
//                            String mouth = String.valueOf(monthOfYear + 1);
//                            if (monthOfYear + 1 < 10) {
//                                mouth = "0" + mouth;
//                            }
//                            String day = String.valueOf(dayOfMonth);
//                            if (dayOfMonth < 10) {
//                                day = "0" + day;
//                            }
//                            Date date = df.parse("" + year + mouth + day);
//                            Calendar cal = Calendar.getInstance();
//                            cal.setTime(date);
//                            long time = cal.getTimeInMillis();
//                            long countTime = Long.valueOf(System.currentTimeMillis() - time);
//                            long days = countTime / (1000 * 60 * 60 * 24);
//                            content.setText("你出生于" + year + "年" + (monthOfYear + 1) + "月" + dayOfMonth +
//                                    "日，你在这个世界上生活了" + days + "天！");
//                            if (days < 10000) {
//                                Toast.makeText(MainActivity.this, "小乌龟，你还很嫩，嘻嘻嘻嘻！！！", Toast.LENGTH_SHORT).show();
//                            } else if (days < 20000) {
//                                Toast.makeText(MainActivity.this, "大BOSS，你太牛逼了，呵呵呵呵！！！", Toast.LENGTH_SHORT).show();
//                            } else if (days < 30000) {
//                                Toast.makeText(MainActivity.this, "哇塞，你这只老妖兽，啊啊啊啊！！！", Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(MainActivity.this, "我X，远古大怪物！！！", Toast.LENGTH_SHORT).show();
//
//                            }
//                        } catch (ParseException e) {
//                            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
//                            e.printStackTrace();
//                        }
//                    }
//                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//                dpd.show();
//                break;
//        }
//    }
//
//    /**
//     * 获取当前应用程序的版本号
//     */
//    private String getVersion() {
//        String st = getResources().getString(R.string.Version_number_is_wrong);
//        PackageManager pm = getPackageManager();
//        try {
//            PackageInfo packinfo = pm.getPackageInfo(getPackageName(), 0);
//            String version = packinfo.versionName;
//            return version;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//            return st;
//        }
//    }
}
