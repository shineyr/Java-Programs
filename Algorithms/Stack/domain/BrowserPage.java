package com.ria.gradle.algorithms.stack.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 浏览器网页类
 */
@Data
@AllArgsConstructor
public class BrowserPage {
    String title;
    String url;

    @Override
    public String toString() {
        return "[" + title + " , " + url + "]";
    }
}
