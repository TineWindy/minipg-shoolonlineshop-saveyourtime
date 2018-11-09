// pages/personinfo/personinfo.js
Page({

  data: {
    disabled : true,
    focus : false,
    test : 0,
    info : {
      name : '',
      studentid : '',
      dorm : '',
      phone : ''
    }
  },



  changedisabled(event){
    // console.log(event.target);
    this.setData({
      disabled : false,
      focus : true
    });
  },

  infoSubmit : function(e){
    var thisPage = this;
    // console.log("whut");
    // console.log(e.detail.value);
    var data = e.detail.value;
    // console.log(data.name=='');
    if(data.name==''||data.studentid==''||data.dorm==''||data.phone==''){
      // console.log("whut");
      wx.showToast({
        icon : 'loading',
        title: '请填写所有信息',
      });
      return;
    }
    data['wxid'] = wx.getStorageSync('wxid');
    // console.log(data);
    var url = 'http://10.133.155.134:8080/sakura/user/update/';
    // console.log("whuuut");
    wx.request({
      url : url,
      data : data,
      header: {
        'content-type': 'application/x-www-form-urlencoded' // 默认值
      },
      method : 'POST',
      success : res => {
        var info = res.data.data;
        console.log(info);
        try{
          wx.setStorageSync('info.name', info.name);
          wx.setStorageSync('info.studentid', info.studentid);
          wx.setStorageSync('info.dorm', info.dormitory);
          wx.setStorageSync('info.phone', info.phone);
        }
        catch(e){}
        this.setData({
          info : {

            name: info.name,
            studentid: info.studentid,
            dorm: info.dormitory,
            phone: info.phone
          },
        });
        wx.showToast({
          title: '提交成功',
        })
      }
    })
  },



  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      info : {
        name: wx.getStorageSync('info.name'),
        studentid: wx.getStorageSync('info.studentid'),
        dorm: wx.getStorageSync('info.dorm'),
        phone: wx.getStorageSync('info.phone')
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    // console.log(''=='');
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})