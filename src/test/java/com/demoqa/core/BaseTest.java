package com.demoqa.core;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {

    protected static Playwright playwright;
    protected static Browser browser;
    protected static BrowserContext context;
    protected static Page page;

    @BeforeAll
    public static void launchBrowser() {
        playwright = Playwright.create();
        BrowserType type = switch (Config.browser()) {
            case "firefox" -> playwright.firefox();
            case "webkit" -> playwright.webkit();
            default -> playwright.chromium();
        };
        browser = type.launch(new BrowserType.LaunchOptions()
                .setHeadless(Config.headless())
                .setSlowMo(Config.slowMo()));

    }

    @BeforeEach
    public void openPage() {
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1600, 900).
                setBaseURL(Config.BASE_URL));
        context.tracing()
                .start(new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true));
        page = context.newPage();
        page.setDefaultTimeout(10_000);

    }

    @AfterEach
    public void closePage() {
        if (context != null) context.close();
    }

    @AfterAll
    public static void closeBrowser() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();

    }
}