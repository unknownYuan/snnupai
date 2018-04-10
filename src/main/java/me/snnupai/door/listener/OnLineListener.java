package me.snnupai.door.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author guohaodong
 * @create 2018-04-01 18:46
 **/
@WebListener
public class OnLineListener implements HttpSessionListener {
    public static int count = 0;


    @Override
    public synchronized void sessionCreated(HttpSessionEvent arg0) {
        System.out.println("【HttpSessionListener监听器】count++  增加");
//        count.getAndIncrement();
        count++;
        System.out.println("count :" + count);
        arg0.getSession().setMaxInactiveInterval(-1);
        arg0.getSession().getServletContext().setAttribute("count", count);

    }

    @Override
    public synchronized void  sessionDestroyed(HttpSessionEvent arg0) {//监听session的撤销
        System.out.println("【HttpSessionListener监听器】count--  减少");
//        count.getAndDecrement();
        count--;
        System.out.println("count :" + count);
        arg0.getSession().getServletContext().setAttribute("count", count);
    }
}
