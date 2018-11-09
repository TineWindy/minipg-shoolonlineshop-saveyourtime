// miniprogram/pages/home/home.js

const sliderWidth = 96
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tabs: ['最新发布', '最多浏览', '搜索'],
    activeIndex: 0,
    sliderOffset: 0,
    sliderLeft: 0,
    msg1: {
      title: '空空如也',
      text: '暂时没有相关数据',
    },
    msg2: {
      icon: '../../assets/images/iconfont-order.png',
      title: '您还没有相关的订单',
      text: '可以去看看有哪些想买',
      buttons: [{
        text: '随便逛逛',
      }],
    },
    msg3: {
      icon: '../../assets/images/iconfont-empty.png',
      title: '暂无待评价订单',
    },
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function() {
    this.getSystemInfo()
  },
  getSystemInfo() {
    const that = this
    wx.getSystemInfo({
      success(res) {
        that.setData({
          sliderLeft: (res.windowWidth / that.data.tabs.length - sliderWidth) / 2,
        })
      }
    })
  },
  tabClick(e) {
    const { offsetLeft, dataset } = e.currentTarget
    const { id } = dataset

    this.setData({
      sliderOffset: offsetLeft,
      activeIndex: id,
    })
  },
  buttonClicked(e) {
    console.log(e)
  },

  onChange(event) {
    console.log(event)
  },

  getdata(e){
    console.log(e.detail)
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