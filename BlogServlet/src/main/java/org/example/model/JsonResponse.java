package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Author:ZouDouble
 * Description:http响应json数据，前后端统一约定json格式
 * 响应状态码都是200,进入ajax的success来使用
 * 操作成功:{success:true data:xxx}
 * 操作失败:{success:false code:xxx message:xxx}
 */
@Getter
@Setter
@ToString
public class JsonResponse {
    //业务是否操作成功的标识
    private boolean success;
    //code标识业务操作的消息码,用来给开发人员找bug
    private String code;
    //message用来给用户看错误消息
    private String message;
    //业务数据:业务操作成功时，给ajax success方法使用，解析响应json数据，渲染网页内容
    private Object data;

}
