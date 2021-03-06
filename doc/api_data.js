define({ "api": [
  {
    "type": "post",
    "url": "/account/login",
    "title": "账号登录",
    "group": "Account",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>手机号【必填】</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>密码【必填】</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Request:",
          "content": "{\n    phone:18320444515\npassword:abc123\n}",
          "type": "json"
        },
        {
          "title": "Success-Response:",
          "content": "{\n    \"resultCode\": 200,\n    \"resultMsg\": \"成功\",\n    \"data\": {\n        \"id\": 2,\n        \"name\": \"曾天臆\",\n        \"phone\": \"18320444515\",\n        \"headPic\": null,\n        \"sex\": 0,\n        \"email\": null,\n        \"province\": null,\n        \"city\": null,\n        \"country\": null,\n        \"accountKind\": 1,\n        \"openid\": null,\n        \"roleId\": null,\n        \"age\": null,\n        \"job\": null,\n        \"organization\": null,\n        \"createTime\": \"2020-05-02T04:54:33.000+0000\",\n        \"updateTime\": \"2020-05-02T04:54:33.000+0000\"\n    },\n    \"count\": 0\n}",
          "type": "json"
        }
      ],
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "resultCode",
            "description": "<p>响应结果</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "resultMsg",
            "description": "<p>结果描述</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>数据主体</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "count",
            "description": "<p>总数据量</p>"
          }
        ]
      }
    },
    "error": {
      "examples": [
        {
          "title": "Error-Respinse-有空:",
          "content": "{\n    \"resultCode\": 403,\n    \"resultMsg\": \"登录密码不能为空\",\n    \"data\": null,\n    \"count\": 0\n}",
          "type": "json"
        },
        {
          "title": "Error-Respinse-错误:",
          "content": "{\n    \"resultCode\": 444,\n    \"resultMsg\": \"手机号或密码错误\",\n    \"data\": null,\n    \"count\": 0\n}",
          "type": "json"
        },
        {
          "title": "Error-Respinse-异常:",
          "content": "{\n    \"resultCode\": 500,\n    \"resultMsg\": \"密码加密时出错，请联系管理员\",\n    \"data\": null,\n    \"count\": 0\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./account/account-web/src/main/java/com/zty/web/controller/AccountController.java",
    "groupTitle": "账号",
    "name": "PostAccountLogin"
  },
  {
    "type": "post",
    "url": "/account/register",
    "title": "注册平台账号",
    "group": "Account",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>姓名/昵称【必填】</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>手机号【必填】</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>密码【必填】</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Request:",
          "content": "{\n    name:zty\nphone:18XXXXXXXX5\npassword:abc123\n}",
          "type": "json"
        },
        {
          "title": "Success-Response:",
          "content": "{\n    \"resultCode\": 200,\n    \"resultMsg\": \"成功\",\n    \"data\": 1,\n    \"count\": 0\n}",
          "type": "json"
        }
      ],
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "resultCode",
            "description": "<p>响应结果</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "resultMsg",
            "description": "<p>结果描述</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>数据主体</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "count",
            "description": "<p>总数据量</p>"
          }
        ]
      }
    },
    "error": {
      "examples": [
        {
          "title": "Error-Respinse-有空值:",
          "content": "{\n    \"resultCode\": 403,\n    \"resultMsg\": \"登录手机号不能为空\",\n    \"data\": null,\n    \"count\": 0\n}",
          "type": "json"
        },
        {
          "title": "Error-Respinse-已存在:",
          "content": "{\n    \"resultCode\": 444,\n    \"resultMsg\": \"注册失败，手机号已存在\",\n    \"data\": null,\n    \"count\": 0\n}",
          "type": "json"
        },
        {
          "title": "Error-Respinse-未知原因:",
          "content": "{\n    \"resultCode\": 500,\n    \"resultMsg\": \"注册失败，未知原因\",\n    \"data\": null,\n    \"count\": 0\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./account/account-web/src/main/java/com/zty/web/controller/AccountController.java",
    "groupTitle": "账号",
    "name": "PostAccountRegister"
  },
  {
    "type": "post",
    "url": "/account/update",
    "title": "修改账号信息",
    "group": "Account",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "name",
            "description": "<p>姓名/昵称【可选】</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phone",
            "description": "<p>手机号【可选】</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>密码【可选】</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Request:",
          "content": "{\n    id:1\n    password:112233ab\n}",
          "type": "json"
        },
        {
          "title": "Success-Response:",
          "content": "{\n    \"resultCode\": 200,\n    \"resultMsg\": \"成功\",\n    \"data\": 1,\n    \"count\": 0\n}",
          "type": "json"
        }
      ],
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "resultCode",
            "description": "<p>响应结果</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "resultMsg",
            "description": "<p>结果描述</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>数据主体</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "count",
            "description": "<p>总数据量</p>"
          }
        ]
      }
    },
    "error": {
      "examples": [
        {
          "title": "Error-Respinse-有空:",
          "content": "{\n    \"resultCode\": 403,\n    \"resultMsg\": \"用户id不可为空或为0\",\n     \"data\": null,\n     \"count\": 0\n}",
          "type": "json"
        },
        {
          "title": "Error-Respinse:",
          "content": "{\n    \"resultCode\": 500,\n    \"resultMsg\": \"修改失败，密码加密时出错，请联系管理员\",\n    \"data\": null,\n    \"count\": 0\n}",
          "type": "json"
        },
        {
          "title": "Error-Respinse:",
          "content": "{\n    \"resultCode\": 500,\n    \"resultMsg\": \"修改失败，未知原因\",\n    \"data\": null,\n    \"count\": 0\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "./account/account-web/src/main/java/com/zty/web/controller/AccountController.java",
    "groupTitle": "账号",
    "name": "PostAccountUpdate"
  },
  {
    "type": "post",
    "url": "/file/upload",
    "title": "上传文件",
    "group": "File",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "File",
            "optional": false,
            "field": "uploadFile",
            "description": "<p>文件【必传】</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Request:",
          "content": "{\n    uploadFile: 二维码.jpg\n}",
          "type": "json"
        },
        {
          "title": "Success-Response:",
          "content": "{\n    \"resultCode\": 200,\n    \"resultMsg\": \"成功\",\n    \"data\": {\n        \"id\": 5,\n        \"name\": \"wxQRC.jpg\",\n        \"fileKind\": null,\n        \"publicUrl\": \"/zhihao/map/download?filename=1590148804080.jpg\",\n        \"createBy\": 2,\n        \"createTime\": \"2020-05-22T12:00:04.000+0000\"\n    },\n    \"count\": 0\n}",
          "type": "json"
        }
      ],
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "resultCode",
            "description": "<p>响应结果</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "resultMsg",
            "description": "<p>结果描述</p>"
          },
          {
            "group": "Success 200",
            "type": "Object",
            "optional": false,
            "field": "data",
            "description": "<p>数据主体</p>"
          },
          {
            "group": "Success 200",
            "type": "Integer",
            "optional": false,
            "field": "count",
            "description": "<p>总数据量</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "./file/file-web/src/main/java/com/zty/web/controller/FileController.java",
    "groupTitle": "文件",
    "name": "PostFileUpload"
  }
] });
