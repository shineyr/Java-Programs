package com.ria.gradle.algorithms.stack;

import com.ria.gradle.algorithms.stack.domain.BrowserPage;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 基于栈实现浏览器的网页前进与后退
 */
public class BrowserBasedOnStack<T> {

    private Stack<T> backwardStack;
    private Stack<T> forwardStack;
    private T currentPage;

    BrowserBasedOnStack() {
        this.backwardStack = new Stack<>();
        this.forwardStack = new Stack<>();
        this.currentPage = null;
    }

    /**
     * 访问一个新页面
     * @param newPage
     */
    public void open(T newPage) {
        backwardStack.push(newPage);
        currentPage = newPage;

        // 此时当前访问的新页面则没有前进历史
        forwardStack.clear();
    }

    /**
     * 返回当前页面
     * @return
     */
    public T getCurrentPage() {
        return currentPage;
    }

    /**
     * 点击后退按钮
     * @return
     */
    public void backward() {
        if(backwardStack.isEmpty()) {
            return ;
        }

        T backPage = backwardStack.pop();
        currentPage = backwardStack.isEmpty() ? null : backwardStack.peek();

        forwardStack.push(backPage);
    }

    /**
     * 点击前几按钮
     * @return
     */
    public void forward() {
        if (forwardStack.isEmpty()) {
            return ;
        }

        T forePage = forwardStack.pop();
        backwardStack.push(forePage);

        currentPage = backwardStack.peek();
    }
}

class BrowserBasedOnStackTest {
    public static void main(String[] args) {
        BrowserBasedOnStack browser = new BrowserBasedOnStack();
        List<BrowserPage> pages = Arrays.asList(new BrowserPage("Google", "www.google.com"),
                new BrowserPage("Leetcode", "www.leetcode.com"),
                new BrowserPage("Amazon", "www.amazon.com"));

        pages.forEach(page -> browser.open(page));

        System.out.println(browser.getCurrentPage()); // [Amazon , www.amazon.com]

        // 后退一个页面
        browser.backward();
        System.out.println(browser.getCurrentPage()); // [Leetcode , www.leetcode.com]

        // 后退一个页面
        browser.backward();
        System.out.println(browser.getCurrentPage()); // [Google , www.google.com]

        // 再次后退一个页面
        browser.backward();
        System.out.println(browser.getCurrentPage()); // null

        // 再次后退一个页面
        browser.backward();
        System.out.println(browser.getCurrentPage()); // null

        // 前进一个页面
        browser.forward();
        System.out.println(browser.getCurrentPage()); // [Google , www.google.com]

        // 访问一个新页面
        browser.open(new BrowserPage("Baidu", "www.baidu.com"));

        System.out.println(browser.getCurrentPage()); // [Baidu , www.baidu.com]

        browser.forward(); // 此时当前页面没有前进历史，所以点击前进，页面不改变
        System.out.println(browser.getCurrentPage()); // [Baidu , www.baidu.com]

        browser.backward(); //  而且，当前页面的后退历史只有 [Google , www.google.com]
        System.out.println(browser.getCurrentPage()); // [Google , www.google.com]

        browser.backward(); // 此时已没有后退历史，所以再次后退，页面不改变
        System.out.println(browser.getCurrentPage()); // null

    }
}
