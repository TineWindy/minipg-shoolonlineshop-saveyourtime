//app.js
App({
  onLaunch: function () {
    // console.log("here");
    // wx.login({
    //   success: function (res) {
    //     console.log("post");
    //     // sessionCode = res.code;
    //     signIn(res.code);
    //   }
    // });

    // wx.login({
    //   success: function (res) {
    //     console.log("post");
    //     var sessionCode = res.code;
    //     signIn(sessionCode);
    //   }
    // });

    wx.checkSession({
      fail : function(){
        console.log("fail");
        wx.login({
          success : function(res){
            console.log("post");
            var sessionCode = res.code;
            signIn(sessionCode);
          }
        });
      },
      success : function(){
        console.log("suc");
        wx.getStorage({
          key: 'openid',
          success: function(res) {
            console.log("wxid: "+res.data);
          },
          fail : function(){
            console.log("no wxid");
          }
        })
      }
    })
    
    this.globalData = {}
  }
})

function signIn(sessionCode){
  var url = 'http://10.133.155.134:8080/sakura/WXLogin/';
  wx.request({
    url : url,
    method : 'POST',
    header: {
      'content-type': 'application/x-www-form-urlencoded' // 默认值
    },
    data : {'code' : sessionCode},
    success : function(res){
      console.log(res);
      wx.setStorage({
        key: 'wxid',
        data: res.data.data.wxid,
        success : function(){
          console.log(res.data.data.wxid);
        }
      });
      wx.setStorage({
        key: 'openid',
        data: res.data.data.openid,
      });
      wx.setStorage({
        key: 'session',
        data: res.data.data.session,
        success: function () {
          console.log(res.data.data.session);
        }
      });
    },
    fail : function(){
      console.log("exo me;")
    }
  })
}
