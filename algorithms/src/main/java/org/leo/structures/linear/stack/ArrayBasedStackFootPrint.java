package org.leo.structures.linear.stack;

/**
 * 模块名称:顺序栈（基于数组实现）
 *
 * @author xiaochuang.mao
 * @date 2021/5/25 13:55
 */
public class ArrayBasedStackFootPrint {
    private String[] ele ;
    private int count;
    private int size;

    public ArrayBasedStackFootPrint(int size){
        ele = new String[size];
        this.size = size;
        this.count = 0;
    }
    public boolean push(String data){
        if(count >= size){
            return false;
        }
        ele[count] = data;
        count++;
        return true;
    }

    public String pop(){
        if (count <= 0){
            return null;
        }
        String temp = ele[count-1];
        ele[count-1] = null;
        count--;
        return temp;
    }
    
    public void clear(){
        if (count <= 0){
            return;
        }
        for (String e : ele) {
            e = null;
        }
        size = 0;
        count = 0;
    }
    public int getCount(){
        return count;
    }
    public int getSize(){
        return size;
    }
}
/**
 * 栈实现浏览器前进后退
 */
class BrowserExample{
   ArrayBasedStackFootPrint forwardStack = new ArrayBasedStackFootPrint(10);
    ArrayBasedStackFootPrint backStack = new ArrayBasedStackFootPrint(10);

   /**
    * 浏览页面
    * @param url 页面url
    */
   public void browse(String url){
       forwardStack.push(url);
   }
   /**
    * 返回页面中打开新页面
    * @param url url
    */
    public void browseNew(String url){
        browse(url);
        //打开新的页面,清空backStack,不需要了
        backStack.clear();
    }

    /**
     *
     * 前进
     */
   public void forward(){
       String pop = backStack.pop();
       if (pop != null){
           forwardStack.push(pop);
       }
   }

   /**
    * 后退
    */
   public void back(){
       String pop = forwardStack.pop();
       if (pop != null){
           backStack.push(pop);
       }
   }

   /**
    * 是否能前进
    * @return boolean
    */
   public boolean canGoForward(){
       if(forwardStack.getCount() >= forwardStack.getSize()){
           return false;
       }
       return true;
   }

   /**
    * 能否后退
    * @return boolean
    */
    public boolean canGoBack(){
        if(backStack.getCount() >= backStack.getSize()){
            return false;
        }
        return true;
    }


}

