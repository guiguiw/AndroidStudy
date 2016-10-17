
<p>
&nbsp;&nbsp;&nbsp;&nbsp;一、建立一个新的项目，取名为ExperimentThree。注册一个Activity，命名为MainActivity.  </br>
&nbsp;&nbsp;&nbsp;&nbsp;二、编写主页样式：  </br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1. 新建一个drawable文件命名为circlestyle.xml，为主页显示名字首字母的圆圈样式。</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2. 新建一个layout， 命名为main_layout，在此布局中添加一个ListView。再建立一个新的layout， 命名为main_item.xml，为主页ListView中的item，设置为横向的线性布局，分别为姓氏和姓名。在显示姓氏时采用第一步建立好的drawable作为背景。</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3. 新建一个java文件，命名为MainActivity.java，引入main_layout布局， 使用SimpleAdapter为ListView添加item。</br>
</p>
![](http://ww3.sinaimg.cn/large/006y8lVagw1f8v9f1le20j30hy08340r.jpg)
&nbsp;&nbsp;&nbsp;&nbsp;三、添加主页点击事件：</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1. 将通讯录信息打表存入。</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2. 添加短按和长按ListView事件。短按时页面跳转， 传入相应ListView对应的姓名及其他信息。长按弹出对话框，在点击确认后删除联系人。</br>
![](http://ww4.sinaimg.cn/large/006y8lVagw1f8v9f1fqjvj30ij0f1n0s.jpg)
&nbsp;&nbsp;&nbsp;&nbsp;四、 添加详情页面样式：</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1.新建一个layout， 命名为detail_layout，使用relativeLayout添加详情页面顶部样式。</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.在详情页面底部添加一个ListView来显示更多操作。</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.建立一个新的layout， 命名为detail_item.xml，为ListView中的item，</br>
&nbsp;&nbsp;&nbsp;&nbsp;五、添加详情页面操作事件：</br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1.新建一个java文件，命名为DetailActivity.java，引入detail_layout布局获取页面跳转到此页后的数据并传入相应的textView。</br>
![](http://ww4.sinaimg.cn/large/006y8lVagw1f8v9f16xrtj30hf084jtf.jpg)
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.添加返回键点击和收藏键点击事件。</br>
![](http://ww3.sinaimg.cn/large/006y8lVagw1f8v9f0ygtvj30j20aygnt.jpg)
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.使用SimpleAdapter为ListView添加item。</br>
![](http://ww1.sinaimg.cn/large/006y8lVagw1f8v9f13dukj30hu051wg0.jpg)


在编写详情页面的样式时，顶部需要占据整个页面的1/3，此时需要设置list_weight来进行比例判断，但是注意要讲layout_height设置为0. 
由于在详情页面点击返回到主页后会重新加载一遍主页的ListView，所以此时不能直接跳转，需要将详情页面的活动结束即可保持主页之前的样式，不会发生删除后的联系人又被加载出来的现象。

