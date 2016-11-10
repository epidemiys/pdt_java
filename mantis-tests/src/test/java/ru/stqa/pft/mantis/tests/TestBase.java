package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.hibernate.service.spi.ServiceException;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

/**
 * Created by aleksandr.petrov on 02.11.16.
 */
public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));


    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }


    public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException, javax.xml.rpc.ServiceException {
        MantisConnectPortType mc = app.soap().getMantisConnect();
        IssueData issue = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));
        if (!issue.getStatus().getName().equals("closed") && !issue.getStatus().getName().equals("resolved")) {
            return true;
        } else {
            return false;
        }
    }

    public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException, javax.xml.rpc.ServiceException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}

