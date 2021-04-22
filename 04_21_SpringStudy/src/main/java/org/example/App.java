package org.example;
import org.example.config.AppConfig;
import org.example.controller.LoginController;
import org.example.model.User;
import org.example.service.LoginService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class App {
    public static void main(String[] args) {
        //根据Spring配置文件路径创建容器：应用上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //通过类名或者id获取bean对象,可以有多个
        LoginController loginController = (LoginController) context.getBean("loginController");
        System.out.println(loginController);
        //验证loginController依赖注入的属性是不是容器中的Bean对象，通过泛型类获取只能获取一个(使用的是单例模式)
        LoginService loginService = context.getBean(LoginService.class);
        System.out.println(loginController.getLoginService() == loginService);
        User user1 = (User) context.getBean("userA");
        System.out.println(user1);
        //同一个类型，注册多个Bean对象，只能通过id来获取，不能通过对象获取，会报错
        //类型是单例模式
       /* User user2 = context.getBean(User.class);
        System.out.println(user2);*/
        AppConfig appConfig = context.getBean(AppConfig.class);
        System.out.println(appConfig);
        User user = (User)context.getBean("byFactoryBean");
        System.out.println(user);
        System.out.println(loginService.getLoginRepository());
        //关闭容器
        ((ClassPathXmlApplicationContext) context).close();
    }
}