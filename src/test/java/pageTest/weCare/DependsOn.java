package pageTest.weCare;

import org.testng.annotations.Test;

public class DependsOn {
	
    @Test(priority = 0)
    public void fun1(){System.out.println("Running 4");}
//    @Test(priority = 2)
//    public void fun2(){System.out.println("Running 5");}
//    @Test(priority = 3)
//    public void fun3(){System.out.println("Running 6");}

    @Test(dependsOnMethods= {"fun1"})
    public void fun2(){System.out.println("Running 5");}    
    @Test(dependsOnMethods= {"fun1", "fun2"})
    public void fun3(){System.out.println("Running 6");}


}
