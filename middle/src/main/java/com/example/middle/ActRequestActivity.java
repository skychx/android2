package com.example.middle;

import com.example.middle.util.DateUtil;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by ouyangshen on 2017/9/24.
 */
public class ActRequestActivity extends AppCompatActivity implements OnClickListener {
    private EditText et_request; // 声明一个编辑框对象
    private TextView tv_request; // 声明一个文本视图对象

    // android 新的数据通信 API：Activity Result API
    // https://developer.android.com/training/basics/intents/result
    // Activity Result API 会提供 registerForActivityResult() API，用于注册结果回调。
    // registerForActivityResult() 接受 ActivityResultContract 和 ActivityResultCallback 作为参数:
    //  - ActivityResultContract 定义生成结果所需的输入类型以及结果的输出类型
    //  - ActivityResultCallback 是单一方法接口，带有 onActivityResult() 方法，可接受 ActivityResultContract 中定义的输出类型的对象
    // 并返回 ActivityResultLauncher，供您用来启动另一个 activity
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(
            new StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        if (intent != null) {
                            // 从意图中取出名叫response_time的字符串
                            String response_time = intent.getStringExtra("response_time");
                            // 从意图中取出名叫response_content的字符串
                            String response_content = intent.getStringExtra("response_content");
                            String desc = String.format("收到返回消息：\n应答时间为%s\n应答内容为%s",
                                    response_time, response_content);
                            // 把返回消息的详情显示在文本视图上
                            tv_request.setText(desc);
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_request);
        findViewById(R.id.btn_act_request).setOnClickListener(this);
        // 从布局文件中获取名叫et_request的编辑框
        et_request = findViewById(R.id.et_request);
        // 从布局文件中获取名叫tv_request的文本视图
        tv_request = findViewById(R.id.tv_request);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_act_request) {
            // 创建一个新意图
            Intent intent = new Intent();
            // 设置意图要跳转的活动类
            intent.setClass(this, ActResponseActivity.class);
            // 往意图存入名叫request_time的字符串
            intent.putExtra("request_time", DateUtil.getNowTime());
            // 往意图存入名叫request_content的字符串
            intent.putExtra("request_content", et_request.getText().toString());
            // 期望接收下个页面的返回数据
            mStartForResult.launch(intent);
            //  startActivityForResult(intent, 0); 已经不建议使用了
        }
    }


    // 从后一个页面携带参数返回当前页面时触发
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) { // 接收返回数据
//        super.onActivityResult(requestCode, resultCode, data);
//        if (data != null) {
//            // 从意图中取出名叫response_time的字符串
//            String response_time = data.getStringExtra("response_time");
//            // 从意图中取出名叫response_content的字符串
//            String response_content = data.getStringExtra("response_content");
//            String desc = String.format("收到返回消息：\n应答时间为%s\n应答内容为%s",
//                    response_time, response_content);
//            // 把返回消息的详情显示在文本视图上
//            tv_request.setText(desc);
//        }
//    }
}
