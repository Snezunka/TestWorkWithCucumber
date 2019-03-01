package extentions;

import base.DriverFactory;
import gherkin.formatter.model.Result;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.cucumberjvm.AllureReporter;

public class AllureReporterExt extends AllureReporter {

    @Override
    public void result(Result result) {
        String enableScreenShots = System.getProperty("enableScreenshots", "true");
        String actualResultStatus = result.getStatus();
        if ("failed".equals(actualResultStatus) || ("passed".equals(actualResultStatus) & "true".equals(enableScreenShots))) {
            attachScreenshot();
        }
        super.result(result);
    }

    @Attachment(value = "Attachment Screenshot", type = "image/png")
    public static byte[] attachScreenshot() {
        return ((TakesScreenshot) DriverFactory.driver).getScreenshotAs(OutputType.BYTES);
    }
}
