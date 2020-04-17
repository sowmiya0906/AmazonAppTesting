package com.AmazonTesting;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SelectingAustraliaAsCountry {
    AndroidDriver dr;
    CommonMethods methods=new CommonMethods();

    @BeforeTest
    public void creatingSession() throws IOException {
        DesiredCapabilities capabilities=new DesiredCapabilities();
        File app =new File(System.getProperty("user.dir")+"/App/Amazon_shopping.apk");
        capabilities.setCapability("deviceName","redmi 4");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("platformVersion","7.1.2");
        capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability("app",app.getAbsolutePath());
        capabilities.setCapability("appPackage","com.amazon.mShop.android.shopping");
        capabilities.setCapability("appActivity","com.amazon.mShop.home.HomeActivity");
        dr=new AndroidDriver(new URL(methods.gettingProperty("Url")),capabilities);


    }

    @Test
    public void settingAustraliaAsCountry() throws InterruptedException, IOException {
        System.out.println("################# Setting Cuntry as Australia #################");
        dr.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        Thread.sleep(5000);
        dr.findElementById(methods.gettingProperty("Skiplogin")).click();
        dr.findElementById(methods.gettingProperty("HamburgerMenu")).click();
        methods.scrollAndFIndElement(dr,methods.gettingProperty("MenuList"),"Your Notifications","Settings");
        methods.FindingElement(dr,methods.gettingProperty("CountryandLang"),"Country & Language");
        dr.findElementByXPath(methods.gettingProperty("SelectingCountry")).click();
        dr.findElementByClassName(methods.gettingProperty("CountryRadiobutton")).click();
        dr.findElementByXPath(methods.gettingProperty("done")).click();

    }
    @Test
    public void verifingCountryName() throws IOException, InterruptedException {
        System.out.println("################# Verifing Country is set properly #################");
        dr.findElementById(methods.gettingProperty("HamburgerMenu")).click();
        Thread.sleep(5000);
        methods.scrollAndFIndElement(dr,methods.gettingProperty("MenuList"),"See All Programs","Settings");
        methods.FindingElement(dr,methods.gettingProperty("CountryandLang"),"Country & Language");
        Assert.assertEquals(dr.findElementByXPath(methods.gettingProperty("CheckingCountryName")).getText(),"Country/Region: Australia");
    }
    @AfterTest
    public void ClosingSession(){
        dr.quit();
    }

}