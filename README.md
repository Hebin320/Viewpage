            return imageViews;
        }
    }
}
```

 - 在Activity中添加代码，设置Viewpager的第一个加载页面为列表的第二项，因为列表第一项，是额外增加的最后一个Fragment。
 

```
vpMain.setCurrentItem(1);
```


 **4. 给Viewpager添加自动切换**
 

 - 用一个Handler来控制Viewpager的自动切换，在VpListener里面添加如下代码：
 

```
private int PHOTO_CHANGE_TIME = 1000;//定时变量
    private int index = 1;
    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            if (index > imageViews.length - 2) {
                index = 1;
            }
            viewPager.setCurrentItem(index);
            index++;
            mHandler.sendEmptyMessageDelayed(1, PHOTO_CHANGE_TIME);
        }
    };

    public VpListener(ImageView[] imageViews, ViewPager viewPager,int PHOTO_CHANGE_TIME ) {
        this.imageViews = imageViews;
        this.viewPager = viewPager;
        this.PHOTO_CHANGE_TIME = PHOTO_CHANGE_TIME;
        if (imageViews.length == 3) {
            viewPager.addOnPageChangeListener(null);
            mCount = imageViews.length - 2;
        } else if (imageViews.length > 3) {
            mCount = imageViews.length - 2;
        } else {
            mCount = 1;
        }
        mHandler.sendEmptyMessageAtTime(1, PHOTO_CHANGE_TIME);
    }

```


 - 最后在Activity中，添加多一个切换时间变量:
 

```
vpMain.addOnPageChangeListener(new VpListener(VpListener.getImageViews(this,llPoint,list),vpMain,800));
```
