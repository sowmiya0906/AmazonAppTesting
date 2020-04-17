package com.AmazonTesting;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class CommonMethods {
    public String gettingProperty(String keyValue) throws IOException {
        //Loading property file and retriving the value by KEY
        File file = new File(System.getProperty("user.dir") + "/src/com/AmazonTesting/Locator.properties");
        FileInputStream fis = new FileInputStream(file);
        Properties prop = new Properties();
        prop.load(fis);
        //getting property from property File
        String value = prop.getProperty(keyValue);
        return value;
    }

    public void scrollAndFIndElement(AndroidDriver dr, String elementlocator, String search1,String search) throws InterruptedException {
        List<WebElement> menu = dr.findElementsById(elementlocator);
        for (WebElement menuList : menu) {
            if (menuList.getText().equalsIgnoreCase(search1)) {
                int x = menuList.getLocation().getX();
                int y = menuList.getLocation().getY();
                int w=dr.manage().window().getSize().width;
                int h=dr.manage().window().getSize().height;
                TouchAction action = new TouchAction(dr);
                action.longPress(PointOption.point(x, y)).moveTo(PointOption.point(x+w*9/10, y-h*9/10)).release().perform();
                Thread.sleep(5000);
                FindingElement(dr,elementlocator,search);
            }
        }
    }


    public void FindingElement(AndroidDriver dr, String elementLocator, String search) throws InterruptedException {
        List<WebElement> settingsmenu = dr.findElementsById(elementLocator);
        for (WebElement setMenu : settingsmenu) {
            System.out.println(setMenu.getText());
            if (setMenu.getText().equalsIgnoreCase(search)) {
                Thread.sleep(5000);
                setMenu.click();
                break;
            }
        }
    }
}