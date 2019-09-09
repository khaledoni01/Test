package popup;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementProxy implements InvocationHandler {

	private final WebDriver driver;
	private final WebElement element;

	public ElementProxy(WebDriver driver, WebElement element) {
		this.driver = driver;
		this.element = element;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		this.checkForPopupAndKill();
		Object result = null;
		try {
			result = method.invoke(element, args);
		} catch (InvocationTargetException e) {
			e.getMessage();
		}
		return result;
	}

	private void checkForPopupAndKill() {
		System.out.println("Ele Prox");
//		try {
			if (driver.findElement(By.xpath("//span[@class='close']")).isDisplayed()) {
				System.out.println("popup is displayed and closed");
				driver.findElement(By.xpath("//span[@class='close']")).click();
			}
//		} catch (Exception e) {
//			e.getMessage();
//		}

	}

}
